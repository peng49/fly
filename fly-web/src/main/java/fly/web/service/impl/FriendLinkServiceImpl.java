package fly.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fly.web.dao.FriendLinkMapper;
import fly.web.entity.model.FriendLink;
import fly.web.service.FriendLinkService;
import org.springframework.stereotype.Service;

@Service
public class FriendLinkServiceImpl extends ServiceImpl<FriendLinkMapper, FriendLink> implements FriendLinkService {

}
