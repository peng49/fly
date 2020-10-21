package fly.admin.repository;

import fly.admin.entity.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post> findPostsByIdIn(List<Integer> postIds);
}
