package fly.web.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fly.web.dao.PostMapper;
import fly.web.entity.from.PostEditFrom;
import fly.web.entity.from.PostFilterCondition;
import fly.web.entity.model.Post;
import fly.web.entity.model.PostAgree;
import fly.web.entity.model.User;
import fly.web.entity.model.UserCollection;
import fly.web.entity.vo.PostVO;
import fly.web.enums.PostStatus;
import fly.web.event.PostPublishEvent;
import fly.web.service.*;
import org.apache.shiro.SecurityUtils;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.function.Function;

@Service
public class PostServiceImpl extends ServiceImpl<PostMapper, Post> implements PostService {
    @Resource
    private ColumnService columnService;

    @Resource
    private UserCollectionService userCollectionService;

    @Resource
    private UserService userService;

    @Resource
    private PostAgreeService postAgreeService;

    @Resource
    private DateTimeFormatter dateTimeFormatter;

    @Resource
    private PostMapper postMapper;

    @Resource
    private ApplicationEventPublisher publisher;


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

        return items.convert(post2VO());
    }

    protected Function<Post, PostVO> post2VO() {
        return post -> PostVO.builder()
                .id(post.getId())
                .top(post.getTop())
                .status(post.getStatus())
                .essence(post.getEssence())
                .column(columnService.getById(post.getColumnId()))
                .author(userService.getById(post.getAuthorId()))
                .publishAt(post.getPublishAt() != null ? dateTimeFormatter.format(post.getPublishAt()) : "")
                .title(post.getTitle())
                .viewCount(post.getViewCount())
                .replyCount(post.getReplyCount())
                .build();
    }

    @Override
    public IPage<PostVO> findAllByAuthorId(IPage<Post> page, Long id) {
        IPage<Post> items = lambdaQuery().eq(Post::getAuthorId, id).orderByDesc(Post::getId).page(page);

        return items.convert(post2VO());
    }

    @Override
    public IPage<PostVO> findUserPost(IPage page, Long userId) {
        IPage<Post> items = postMapper.findForUserCollection(userId, page);
        return items.convert(post2VO());
    }

    public IPage<Post> findPublishByAuthorId(Long id, IPage<Post> page) {
        return lambdaQuery()
                .eq(Post::getAuthorId, id)
                .eq(Post::getStatus, PostStatus.PUBLISHED.getStatus())
                .orderByDesc(Post::getPublishAt)
                .page(page);
    }

    public PostVO get(Long id) {
        Post post = getById(id);

        if (post == null) {
            throw new RuntimeException("文章不存在");
        }
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        boolean collected = false;
        boolean agree = false;
        if (user != null) {
            Long userId = user.getId();
            collected = userCollectionService.exists(user,post.getId());

            agree = postAgreeService.exists(post.getId(),userId);
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
                .publishAt(post.getPublishAt() != null ? dateTimeFormatter.format(post.getPublishAt()) : "")
                .title(post.getTitle())
                .originalContent(post.getOriginalContent())
                .content(post.getContent())
                .collected(collected)
                .collectedCount(userCollectionService.lambdaQuery().eq(UserCollection::getPostId, post.getId()).count())
                .agree(agree)
                .agreeCount(postAgreeService.lambdaQuery().eq(PostAgree::getPostId,post.getId()).count())
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
            builder.status(PostStatus.PUBLISHED.getStatus())
                    .publishAt(LocalDateTime.now())
                    .heat(PostService.DEFAULT_HEAD);
        } else {
            builder.status(PostStatus.DRAFT.getStatus());
        }

        Post post = builder.build();
        save(post);

        if ("publish".equals(postEditFrom.getAction())) {
            publisher.publishEvent(new PostPublishEvent(post));
        }

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
        post.setHeat(calculationHeat(post));
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
        post.setUpdateAt(LocalDateTime.now());

        //发布
        if ("publish".equals(postEditFrom.getAction())) {
            post.setStatus(PostStatus.PUBLISHED.getStatus());
            post.setPublishAt(LocalDateTime.now());
            post.setHeat(calculationHeat(post));
            publisher.publishEvent(new PostPublishEvent(post));
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
        if (Integer.valueOf(1).equals(post.getTop())) {
            return PostService.DEFAULT_HEAD + 1.00;
        }
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
        post.setStatus(PostStatus.DELETE.getStatus());
        updateById(post);
    }
}
