package fly.frontend.service;

import fly.frontend.entity.model.Post;
import fly.frontend.entity.model.PostAutoDraft;
import fly.frontend.entity.model.User;

import java.util.List;

public interface PostAutoDraftService{
    PostAutoDraft add(PostAutoDraft draft);

    List<PostAutoDraft> getForUser(User user);

    PostAutoDraft getForPost(Post post);
}
