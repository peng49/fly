package fly.frontend.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fly.frontend.dao.PostMapper;
import fly.frontend.entity.from.PostEditFrom;
import fly.frontend.entity.from.PostFilterCondition;
import fly.frontend.entity.model.Post;
import fly.frontend.entity.model.User;
import fly.frontend.entity.model.UserPost;
import fly.frontend.entity.vo.PostVO;
import fly.frontend.service.ColumnService;
import fly.frontend.service.PostService;
import fly.frontend.service.UserPostService;
import fly.frontend.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl extends ServiceImpl<PostMapper, Post> implements PostService {
    @Resource
    private ColumnService columnService;

    @Resource
    private UserPostService userPostService;

    @Resource
    private UserService userService;


    public IPage<PostVO> getByCondition(Page<Post> page, PostFilterCondition query) {
        LambdaQueryChainWrapper<Post> queryChainWrapper = lambdaQuery()
                .eq(Post::getStatus, 1)
                .eq(query.getColumnId() != 0, Post::getColumnId, query.getColumnId())
                .eq("essence".equals(query.getList()), Post::getEssence, 1);


        if (query.getOrderBy() == null || "heat".equals(query.getOrderBy())) {
            queryChainWrapper.orderByDesc(Post::getHeat);
        }
        if ("publish_at".equals(query.getOrderBy())) {
            queryChainWrapper.orderByDesc(Post::getPublishAt);
        }
        if ("reply_count".equals(query.getOrderBy())) {
            queryChainWrapper.orderByDesc(Post::getReplyCount);
        }

        Page<Post> items = queryChainWrapper.page(page);

        return items.convert(post -> PostVO.builder()
                .id(post.getId())
                .top(post.getTop())
                .status(post.getStatus())
                .essence(post.getEssence())
                .column(columnService.getById(post.getColumnId()))
                .author(userService.getById(post.getAuthorId()))
                .publishAt(post.getPublishAt())
                .title(post.getTitle())
                .viewCount(post.getViewCount())
                .replyCount(post.getReplyCount())
                .build());
    }

    @Override
    public IPage<PostVO> findAllByAuthorId(IPage<Post> page, Long id) {
        IPage<Post> items = lambdaQuery().eq(Post::getAuthorId, id).page(page);

        return items.convert(post -> PostVO.builder()
                .id(post.getId())
                .top(post.getTop())
                .status(post.getStatus())
                .essence(post.getEssence())
                .column(columnService.getById(post.getColumnId()))
                .author(userService.getById(post.getAuthorId()))
                .publishAt(post.getPublishAt())
                .title(post.getTitle())
                .viewCount(post.getViewCount())
                .replyCount(post.getReplyCount())
                .build());
    }

    @Override
    public IPage<PostVO> findUserPost(IPage<Object> page, Long userId) {
        Page<UserPost> list = userPostService.page(new Page<>(page.getCurrent(), page.getSize()), Wrappers.lambdaQuery(UserPost.class).eq(UserPost::getUserId, userId));
        List<Long> postIds = list.getRecords().stream().map(UserPost::getPostId).collect(Collectors.toList());

        List<Post> posts = lambdaQuery().in(Post::getId, postIds).list();


        Page<PostVO> item = new Page<>(list.getCurrent(), list.getSize());
        item.setTotal(list.getTotal());

        ArrayList<PostVO> records = new ArrayList<>();
        posts.forEach(post -> {
            records.add(PostVO.builder()
                    .id(post.getId())
                    .status(post.getStatus())
                    .top(post.getTop())
                    .essence(post.getEssence())
                    .column(columnService.getById(post.getColumnId()))
                    .author(userService.getById(post.getAuthorId()))
                    .publishAt(post.getPublishAt())
                    .title(post.getTitle())
                    .viewCount(post.getViewCount())
                    .replyCount(post.getReplyCount())
                    .build());
        });

        item.setRecords(records);
        return item;
    }

    public List<Post> findAllByAuthorId(Long id) {
        return lambdaQuery().eq(Post::getAuthorId, id).list();
    }

    public List<Post> findAllPublishByAuthorId(Long id) {
        return lambdaQuery().eq(Post::getAuthorId, id).eq(Post::getStatus, PostService.PUBLISH_STATUS).list();
    }

    public PostVO get(Long id) {
        Post post = getById(id);

        User user = (User) SecurityUtils.getSubject().getPrincipal();
        boolean collected = false;
        boolean recommended = false;
        if (user != null) {
            Long userId = user.getId();
            collected = userPostService.lambdaQuery()
                    .eq(UserPost::getUserId, userId)
                    .eq(UserPost::getPostId, post.getId())
                    .list().size() > 0;
        }

        return PostVO.builder()
                .id(post.getId())
                .status(post.getStatus())
                .top(post.getTop())
                .essence(post.getEssence())
                .viewCount(post.getViewCount())
                .replyCount(post.getReplyCount())
                .column(columnService.getById(post.getColumnId()))
                .author(userService.getById(post.getAuthorId()))
                .publishAt(post.getPublishAt())
                .title(post.getTitle())
                .originalContent(post.getOriginalContent())
                .content(post.getContent())
                .collected(collected)
                .collectedCount(userPostService.lambdaQuery().eq(UserPost::getPostId, post.getId()).count())
                .recommended(recommended)
                .build();
    }

    public Post create(PostEditFrom postEditFrom, Long userId) {
        Post.PostBuilder builder = Post.builder()
                .authorId(userId)
                .columnId(postEditFrom.getColumnId())
                .title(postEditFrom.getTitle())
                .originalContent(postEditFrom.getOriginalContent())
                .content(postEditFrom.getContent())
                .createdAt(LocalDateTime.now())
                .updateAt(LocalDateTime.now());

        //发布
        if ("publish".equals(postEditFrom.getAction())) {
            builder.status(PostService.PUBLISH_STATUS)
                    .publishAt(LocalDateTime.now())
                    .heat(PostService.DEFAULT_HEAD);
        }

        Post post = builder.build();
        save(post);
        return post;
    }

    public void update(Post post) {
        updateById(post);
    }

    public void updateHeat(Post post) {
        updateById(post);
    }


    public void replyCountInc(Long postId) {
        Post post = getById(postId);
        updateById(Post.builder().id(postId).replyCount(post.getReplyCount() + 1).build());
    }

    public void viewCountInc(Long postId) {
        Post post = getById(postId);
        post.setViewCount(post.getViewCount() + 1);
        updateById(post);
    }

    public void top(Post post) {
        if (post.getTop() == 1) {
            post.setTop(0);
        } else {
            post.setTop(1);
        }
        updateById(post);
    }

    public void essence(Post post) {
        if (post.getEssence() == 1) {
            post.setEssence(0);
        } else {
            post.setEssence(1);
        }
        updateById(post);
    }

    public void edit(Post post, PostEditFrom postEditFrom) {
        post.setOriginalContent(postEditFrom.getOriginalContent());
        post.setContent(postEditFrom.getContent());
        post.setTitle(postEditFrom.getTitle());
        post.setColumnId(postEditFrom.getColumnId());

        //发布
        if ("publish".equals(postEditFrom.getAction())) {
            post.setStatus(PostService.PUBLISH_STATUS);
            post.setPublishAt(LocalDateTime.now());
            post.setUpdateAt(LocalDateTime.now());
        }
        updateById(post);
    }

    /**
     * 计算文章热度
     *
     * @param post 文章
     * @return 热度值
     */
    public double calculationHeat(Post post) {

        // 1*click + 5*favor + 10*comment + 20*share

        //100个查看等于一个回复
        double user = post.getViewCount() * 0.01 + post.getReplyCount();

        if (post.getPublishAt() == null) {
            return 0.00;
        }

        double time = (System.currentTimeMillis() - post.getPublishAt()
                .atZone(ZoneId.systemDefault())
                .toInstant()
                .toEpochMilli()) / 1000.00 / 3600.00;

        if (time <= 1) {
            time = 1.00;
        }

        return (PostService.DEFAULT_HEAD + user) / time;
    }

    @Override
    public void move2delete(Post post) {
        post.setStatus(PostService.DELETE_STATUS);
        updateById(post);
    }
}
