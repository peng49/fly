package fly.frontend.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import fly.frontend.dao.ColumnMapper;
import fly.frontend.dao.UserMapper;
import fly.frontend.entity.model.Column;
import fly.frontend.entity.model.Post;
import fly.frontend.entity.model.PostComment;
import fly.frontend.entity.model.User;
import fly.frontend.dao.PostMapper;
import fly.frontend.entity.from.PostEditFrom;
import fly.frontend.entity.from.PostFilterCondition;
import fly.frontend.entity.vo.PostVO;
import fly.frontend.service.PostCommentService;
import fly.frontend.service.PostService;
import fly.frontend.service.UserService;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.*;
import java.util.function.Function;

@Service
public class PostServiceImpl implements PostService {
    @Resource
    private PostMapper postMapper;

    @Resource
    private ColumnMapper columnMapper;

    @Resource
    private UserMapper userMapper;


    public IPage<PostVO> getByCondition(Page<Post> page, PostFilterCondition query) {
        Wrapper<Post> queryWrapper = Wrappers.<Post>lambdaQuery()
                .eq(Post::getStatus, 1)
                .eq(query.getColumnId() != 0, Post::getColumnId, query.getColumnId())
                .eq("essence".equals(query.getList()),Post::getEssence,1);

        Page<Post> items = postMapper.selectPage(page, queryWrapper);

        return items.convert(post -> PostVO.builder()
                .id(post.getId())
                .status(post.getStatus())
                .essence(post.getEssence())
                .column(columnMapper.get(post.getColumnId()))
                .author(userMapper.selectById(post.getAuthorId()))
                .publishAt(post.getPublishAt())
                .title(post.getTitle())
                .viewCount(post.getViewCount())
                .replyCount(post.getReplyCount())
                .build());
    }

    public List<Post> findAllByAuthorId(int id) {
        return postMapper.selectList(Wrappers.lambdaQuery(Post.class).eq(Post::getAuthorId, id));
    }

    public List<Post> findAllPublishByAuthorId(int id) {
        return postMapper.selectList(Wrappers.lambdaQuery(Post.class).eq(Post::getAuthorId,id).eq(Post::getStatus,PostService.PUBLISH_STATUS));
    }

    public PostVO get(int id) {
        Post post = postMapper.selectById(id);

        return PostVO.builder()
                .id(post.getId())
                .status(post.getStatus())
                .essence(post.getEssence())
                .column(columnMapper.selectById(post.getColumnId()))
                .author(userMapper.selectById(post.getAuthorId()))
                .publishAt(post.getPublishAt())
                .title(post.getTitle())
                .originalContent(post.getOriginalContent())
                .content(post.getContent())
                .build();
    }

    public Post create(PostEditFrom postEditFrom, User user) {

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Post.PostBuilder builder = Post.builder()
                .authorId(user.getId())
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


    public void replyCountInc(int postId) {
        Post post = postMapper.selectById(postId);
        postMapper.updateById(Post.builder().id(postId).replyCount(post.getReplyCount() + 1).build());
    }

    public void viewCountInc(int postId) {
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
