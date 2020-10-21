package fly.admin.service;

import fly.admin.entity.model.FriendLink;
import fly.admin.entity.vo.ResultVO;

import java.util.Map;

public interface FriendLinkService {
    FriendLink add(FriendLink link);

    void delete(FriendLink link);

    FriendLink update(FriendLink link);

    FriendLink get(int id);

    ResultVO search(int page, int pageSize, Map<String,Object> query);
}
