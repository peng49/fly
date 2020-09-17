package fly.frontend.service.impl;

import fly.frontend.entity.model.FriendLink;
import fly.frontend.mapper.FriendLinkMapper;
import fly.frontend.service.FriendLinkService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FriendLinkServiceImpl implements FriendLinkService {
    @Resource
    private FriendLinkMapper friendLinkMapper;


    public List<FriendLink> getAll() {
        return friendLinkMapper.getAll();
    }
}
