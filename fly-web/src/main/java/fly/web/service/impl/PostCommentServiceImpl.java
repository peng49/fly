package fly.web.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fly.web.dao.PostCommentMapper;
import fly.web.entity.dto.PostDTO;
import fly.web.entity.from.PostCommentAddFrom;
import fly.web.entity.model.Post;
import fly.web.entity.model.PostComment;
import fly.web.entity.model.User;
import fly.web.entity.vo.PostCommentVO;
import fly.web.enums.PostStatus;
import fly.web.event.CommentEvent;
import fly.web.service.PostCommentService;
import fly.web.service.PostService;
import fly.web.service.UserService;
import fly.web.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    @Resource
    private DateTimeFormatter dateTimeFormatter;

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

        if (post == null || post.getStatus() != PostStatus.PUBLISHED.getStatus()) {
            throw new RuntimeException("文章还未发布，不能进行评论");
        }

        PostComment.PostCommentBuilder commentBuilder = PostComment.builder()
                .createdAt(LocalDateTime.now())
                .content(parseCommentContent(postCommentAddFrom.getContent()))
                .postId(post.getId())
                .level(post.getReplyCount() + 1)
                .userId(userId);

        if (postCommentAddFrom.getParentId() != null) {
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
                    .createdAt(comment.getCreatedAt() != null?dateTimeFormatter.format(comment.getCreatedAt()):"")
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
                .createdAt(comment.getCreatedAt() != null?dateTimeFormatter.format(comment.getCreatedAt()):"")
                .content(comment.getContent())
                .post(PostDTO.builder().build())
                .user(userService.getById(comment.getUserId()))
                .agreeCount(comment.getAgreeCount())
                .level(comment.getLevel())
                .build());
    }
}
