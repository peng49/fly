package fly.admin.repository;

import fly.admin.entity.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer>, JpaSpecificationExecutor<Post> {
    List<Post> findPostsByIdIn(List<Integer> postIds);

    int countByAuthorId(int authorId);
}
