package fly.frontend.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fly.frontend.dao.PostMapper;
import fly.frontend.entity.from.PostEditFrom;
import fly.frontend.entity.from.PostFilterCondition;
import fly.frontend.entity.model.Post;
import fly.frontend.entity.model.UserPost;
import fly.frontend.entity.vo.PostVO;
import fly.frontend.service.ColumnService;
import fly.frontend.service.PostService;
import fly.frontend.service.UserPostService;
import fly.frontend.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl extends ServiceImpl<PostMapper,Post> implements PostService {
    @Resource
    private PostMapper postMapper;

    @Resource
    private ColumnService columnService;

    @Resource
    private UserPostService userPostService;

    @Resource
    private UserService userService;


    public IPage<PostVO> getByCondition(Page<Post> page, PostFilterCondition query) {
        Wrapper<Post> queryWrapper = Wrappers.<Post>lambdaQuery()
                .eq(Post::getStatus, 1)
                .eq(query.getColumnId() != 0, Post::getColumnId, query.getColumnId())
                .eq("essence".equals(query.getList()),Post::getEssence,1)
                .orderByDesc(Post::getHeat);

        Page<Post> items = page(page, queryWrapper);

        return items.convert(post -> PostVO.builder()
                .id(post.getId())
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
    public IPage<PostVO> findUserPost(IPage page, Long userId) {
        Page<UserPost> list = userPostService.page(new Page<>(page.getCurrent(), page.getSize()), Wrappers.lambdaQuery(UserPost.class).eq(UserPost::getUserId, userId));
        List<Long> postIds = list.getRecords().stream().map(UserPost::getPostId).collect(Collectors.toList());

        List<Post> posts = lambdaQuery().in(Post::getId, postIds).list();


        Page<PostVO> item = new Page<>(list.getCurrent(),list.getSize());
        item.setTotal(list.getTotal());

        ArrayList<PostVO> records = new ArrayList<>();
        posts.forEach(post -> {
            records.add(PostVO.builder()
                    .id(post.getId())
                    .status(post.getStatus())
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
        return postMapper.selectList(Wrappers.lambdaQuery(Post.class).eq(Post::getAuthorId, id));
    }

    public List<Post> findAllPublishByAuthorId(Long id) {
        return postMapper.selectList(Wrappers.lambdaQuery(Post.class).eq(Post::getAuthorId,id).eq(Post::getStatus,PostService.PUBLISH_STATUS));
    }

    public PostVO get(Long id) {
        Post post = postMapper.selectById(id);

        return PostVO.builder()
                .id(post.getId())
                .status(post.getStatus())
                .essence(post.getEssence())
                .viewCount(post.getViewCount())
                .replyCount(post.getReplyCount())
                .column(columnService.getById(post.getColumnId()))
                .author(userService.getById(post.getAuthorId()))
                .publishAt(post.getPublishAt())
                .title(post.getTitle())
                .originalContent(post.getOriginalContent())
                .content(post.getContent())
                .build();
    }

    public Post create(PostEditFrom postEditFrom, Long userId) {

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Post.PostBuilder builder = Post.builder()
                .authorId(userId)
                .columnId(postEditFrom.getColumnId())
                .title(postEditFrom.getTitle())
                .originalContent(postEditFrom.getOriginalContent())
                .content(postEditFrom.getContent())
                .createdAt(timestamp)
                .updateAt(timestamp);

        //发布
        if ("publish".equals(postEditFrom.getAction())) {
            builder.status(PostService.PUBLISH_STATUS)
                    .publishAt(timestamp)
                    .heat(PostService.DEFAULT_HEAD);
        }

        Post post = builder.build();
        postMapper.insert(post);
        return post;
    }

    public void update(Post post) {
        postMapper.updateById(post);
    }

    public void updateHeat(Post post) {
        postMapper.updateById(post);
    }


    public void replyCountInc(Long postId) {
        Post post = postMapper.selectById(postId);
        postMapper.updateById(Post.builder().id(postId).replyCount(post.getReplyCount() + 1).build());
    }

    public void viewCountInc(Long postId) {
        Post post = postMapper.selectById(postId);
        post.setViewCount(post.getViewCount() + 1);
        postMapper.updateById(post);
    }

    public void top(Post post) {
        if (post.getTop() == 1) {
            post.setTop(0);
        } else {
            post.setTop(1);
        }
        postMapper.updateById(post);
    }

    public void essence(Post post) {
        if (post.getEssence() == 1) {
            post.setEssence(0);
        } else {
            post.setEssence(1);
        }
        postMapper.updateById(post);
    }

    public void edit(Post post, PostEditFrom postEditFrom) {
        post.setOriginalContent(postEditFrom.getOriginalContent());
        post.setContent(postEditFrom.getContent());
        post.setTitle(postEditFrom.getTitle());
        post.setColumnId(postEditFrom.getColumnId());

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        //发布
        if ("publish".equals(postEditFrom.getAction())) {
            post.setStatus(PostService.PUBLISH_STATUS);
            post.setPublishAt(timestamp);
            post.setUpdateAt(timestamp);
        }

        postMapper.updateById(post);
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

        double time = (System.currentTimeMillis() - post.getPublishAt().getTime()) / 1000.00 / 3600.00;

        if (time <= 1) {
            time = 1.00;
        }

        return (PostService.DEFAULT_HEAD + user) / time;
    }

    @Override
    public void move2delete(Post post) {
        post.setStatus(PostService.DELETE_STATUS);
        postMapper.updateById(post);
    }
}
