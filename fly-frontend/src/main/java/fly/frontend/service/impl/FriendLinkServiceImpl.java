package fly.frontend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fly.frontend.dao.FriendLinkMapper;
import fly.frontend.entity.model.FriendLink;
import fly.frontend.service.FriendLinkService;
import org.springframework.stereotype.Service;

@Service
public class FriendLinkServiceImpl extends ServiceImpl<FriendLinkMapper, FriendLink> implements FriendLinkService {

}
