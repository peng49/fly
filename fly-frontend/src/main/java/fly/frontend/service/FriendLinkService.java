package fly.frontend.service;

import fly.frontend.entity.FriendLink;
import fly.frontend.mapper.FriendLinkMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FriendLinkService {

    @Resource
    private FriendLinkMapper friendLinkMapper;


    public List<FriendLink> getAll() {
        return friendLinkMapper.getAll();
    }
}
