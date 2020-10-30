package fly.admin.repository;

import fly.admin.entity.model.PostComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostCommentRepository extends JpaRepository<PostComment,Integer> {
    int countByUserId(int userId);
}
