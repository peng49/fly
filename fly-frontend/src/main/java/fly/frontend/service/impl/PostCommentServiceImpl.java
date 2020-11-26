package fly.frontend.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fly.frontend.dao.PostCommentMapper;
import fly.frontend.entity.dto.PostDTO;
import fly.frontend.entity.from.PostCommentAddFrom;
import fly.frontend.entity.model.Post;
import fly.frontend.entity.model.PostComment;
import fly.frontend.entity.model.User;
import fly.frontend.entity.vo.PostCommentVO;
import fly.frontend.event.CommentEvent;
import fly.frontend.service.PostCommentService;
import fly.frontend.service.PostService;
import fly.frontend.service.UserService;
import fly.frontend.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PostCommentServiceImpl extends ServiceImpl<PostCommentMapper, PostComment> implements PostCommentService {
    @Resource
    private PostService postService;

    @Resource
    private UserService userService;

    @Resource
    private ApplicationEventPublisher publisher;

    private String parseCommentContent(String content) {
        //获取所有有效的用户
        List<User> users = getUsersByContent(content);

        //将所有的@用户替换为a标签
        for (User user : users) {
            content = content.replaceAll(
                    "@" + user.getUsername(),
                    "@<a href='/u/" + user.getId() + "'>" + user.getUsername() + "</a> "
            );
        }
        return content;
    }

    public PostComment create(Long userId, PostCommentAddFrom postCommentAddFrom) {
        Post post = postService.getById(postCommentAddFrom.getPostId());

        if (post == null || post.getStatus() != PostService.PUBLISH_STATUS) {
            throw new RuntimeException("文章还未发布，不能进行评论");
        }

        PostComment.PostCommentBuilder commentBuilder = PostComment.builder()
                .createdAt(LocalDateTime.now())
                .content(parseCommentContent(postCommentAddFrom.getContent()))
                .postId(post.getId())
                .level(post.getReplyCount() + 1)
                .userId(userId);

        if (postCommentAddFrom.getParentId() != 0) {
            commentBuilder.parentId(postCommentAddFrom.getParentId());
        }

        //文章的评论数加1
        postService.replyCountInc(post.getId());

        PostComment comment = commentBuilder.build();

        save(comment);

        publisher.publishEvent(new CommentEvent(comment));
        return comment;
    }

    private Function<PostComment, PostCommentVO> getCovertFunction(Map<Long, Post> posts) {
        return comment -> {
            Post post = Optional.ofNullable(posts.get(comment.getPostId())).orElse(new Post());
            return PostCommentVO.builder()
                    .id(comment.getId())
                    .post(PostDTO.builder().id(post.getId()).title(post.getTitle()).build())
                    .content(comment.getContent())
                    .createdAt(comment.getCreatedAt())
                    .build();
        };
    }

    public IPage<PostCommentVO> getByUserId(IPage<PostComment> pageObj, Long userId) {
        IPage<PostComment> comments = lambdaQuery()
                .eq(PostComment::getUserId, userId)
                .orderByDesc(PostComment::getId)
                .page(pageObj);

        List<Long> postIds = comments.getRecords().stream().map(PostComment::getPostId).collect(Collectors.toList());

        Map<Long, Post> posts = new HashMap<>();
        if (postIds.size() > 0) {
            posts = postService.lambdaQuery()
                    .in(Post::getId, postIds)
                    .list()
                    .stream()
                    .collect(Collectors.toMap(Post::getId, Function.identity()));
        }



        return comments.convert(getCovertFunction(posts));
    }


    @Override
    public void commentAgreeInc(Long commentId) {
        PostComment postComment = getById(commentId);
        updateById(PostComment.builder()
                .id(postComment.getId())
                .agreeCount(postComment.getAgreeCount() + 1)
                .build());
    }

    @Override
    public void commentAgreeDec(Long commentId) {
        PostComment postComment = getById(commentId);
        updateById(PostComment.builder()
                .id(postComment.getId())
                .agreeCount(postComment.getAgreeCount() - 1)
                .build());
    }

    @Override
    public List<User> getUsersByContent(String content) {
        String regex = "@(\\w+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(StringUtils.html2text(content));

        List<User> users = new ArrayList<>();
        while (matcher.find()) {
            String username = matcher.group(1).trim();
            User user = userService.getByUsername(username);
            if (user != null) {
                users.add(user);
            }
        }
        return users;
    }

    @Override
    public IPage<PostCommentVO> getByPostId(Page<PostComment> page, Long postId) {
        Page<PostComment> comments = lambdaQuery().eq(PostComment::getPostId, postId).page(page);
        return comments.convert(comment -> PostCommentVO.builder()
                .id(comment.getId())
                .createdAt(comment.getCreatedAt())
                .content(comment.getContent())
                .post(PostDTO.builder().build())
                .user(userService.getById(comment.getUserId()))
                .level(comment.getLevel())
                .build());
    }
}
