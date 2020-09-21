package fly.frontend.service;

import fly.frontend.entity.model.Post;
import fly.frontend.entity.model.PostAutoDraft;
import fly.frontend.entity.model.User;

public interface PostAutoDraftService{
    PostAutoDraft add(PostAutoDraft draft);

    PostAutoDraft getForUser(User user);

    PostAutoDraft getForPost(Post post);

    PostAutoDraft update(PostAutoDraft draft);

    void delete(int id);
}
