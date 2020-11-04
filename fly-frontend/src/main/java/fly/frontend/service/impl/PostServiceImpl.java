package fly.frontend.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import fly.frontend.dao.ColumnMapper;
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
    private PostCommentService postCommentService;

    public IPage<PostVO> getByCondition(Page<Post> page, PostFilterCondition condition) {
        Wrapper<Post> queryWrapper = Wrappers.<Post>lambdaQuery().eq(Post::getStatus,1);
        Page<Post> items = postMapper.selectPage(page, queryWrapper);

        return items.convert(post -> PostVO.builder()
                .id(post.getId())
                .column(columnMapper.get(post.getColumnId()))
                .author(new User())
                .publishAt(post.getPublishAt())
                .title(post.getTitle())
                .build());
    }

    public List<Post> findTop(int limit) {
        return postMapper.findTop(limit);
    }

    public List<Post> findByColumnId(int columnId) {
        return postMapper.findByColumnId(columnId);
    }

    public List<Post> findAllByAuthorId(int id) {
        return postMapper.findAllByAuthorId(id);
    }

    public List<Post> findAllPublishByAuthorId(int id) {
        return postMapper.findAllPublishByAuthorId(id);
    }

    public Post get(int id) {
        return postMapper.get(id);
    }

    public Post create(PostEditFrom postEditFrom, User user) {

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Post.PostBuilder builder = Post.builder()
//                .author(user)
                .title(postEditFrom.getTitle())
                .originalContent(postEditFrom.getOriginalContent())
                .content(postEditFrom.getContent())
//                .column(Column.builder().id(postEditFrom.getColumnId()).build())
                .createdAt(timestamp)
                .updateAt(timestamp);

        //发布
        if ("publish".equals(postEditFrom.getAction())) {
            builder.status(PostService.PUBLISH_STATUS)
                    .publishAt(timestamp)
                    .heat(PostService.DEFAULT_HEAD);
        }

        Post post = builder.build();
        postMapper.create(post);
        return post;
    }

    public void update(Post post) {
        postMapper.update(post);
    }

    public void updateHeat(Post post) {
        postMapper.updateHeat(post);
    }

    public List<Post> getEveryWeekCommentMax(int limit) {
        return postMapper.getEveryWeekCommentMax(limit);
    }

    public void replyCountInc(int postId) {
        postMapper.replyCountInc(postId);
    }

    public void viewCountInc(int postId) {
        postMapper.viewCountInc(postId);
    }

    public List<PostComment> getComments(int postId) {
        List<PostComment> comments = postMapper.getComments(postId);

        ArrayList<Integer> parentIds = new ArrayList<>();
        for (PostComment comment : comments) {
            if (comment.getParent() != null) {
                parentIds.add(comment.getParent().getId());
            }
        }

        //去重
        ArrayList<Integer> ids = new ArrayList<>(new HashSet<>(parentIds));
        if (ids.size() > 0) {
            List<PostComment> parentComments = postCommentService.getCommentsByCommentIds(parentIds);
            HashMap<Integer, PostComment> hashComments = new HashMap<>();
            for (PostComment parentComment : parentComments) {
                hashComments.put(parentComment.getId(), parentComment);
            }

            for (PostComment comment : comments) {
                if (comment.getParent() != null) {
                    comment.setParent(hashComments.get(comment.getParent().getId()));
                }
            }
        }
        return comments;
    }

    public void top(Post post) {
        if (post.getTop() == 1) {
            post.setTop(0);
        } else {
            post.setTop(1);
        }
        postMapper.top(post);
    }

    public void essence(Post post) {
        if (post.getEssence() == 1) {
            post.setEssence(0);
        } else {
            post.setEssence(1);
        }
        postMapper.essence(post);
    }

    public void edit(Post post, PostEditFrom postEditFrom) {
        post.setOriginalContent(postEditFrom.getOriginalContent());
        post.setContent(postEditFrom.getContent());
        post.setTitle(postEditFrom.getTitle());
//        post.setColumn(Column.builder().id(postEditFrom.getColumnId()).build());

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        //发布
        if ("publish".equals(postEditFrom.getAction())) {
            post.setStatus(PostService.PUBLISH_STATUS);
            post.setPublishAt(timestamp);
            post.setUpdateAt(timestamp);
        }

        postMapper.edit(post);
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
        postMapper.setStatus(post);
    }
}
