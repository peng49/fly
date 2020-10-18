package fly.admin.service;

import fly.admin.entity.model.FriendLink;

import java.util.List;

public interface FriendLinkService {
    FriendLink add(FriendLink link);

    void delete(FriendLink link);

    FriendLink update(FriendLink link);

    FriendLink get(int id);

    List<FriendLink> search();
}
