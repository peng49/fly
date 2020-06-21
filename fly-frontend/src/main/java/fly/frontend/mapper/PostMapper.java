package fly.frontend.mapper;

import fly.frontend.entity.Post;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostMapper {
    public List<Post> findAll();

    public Post findById(int id);

    public void create(Post post);

    public void update(Post post);
}
