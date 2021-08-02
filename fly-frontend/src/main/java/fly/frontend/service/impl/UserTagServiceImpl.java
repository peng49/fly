package fly.frontend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fly.frontend.dao.UserTagMapper;
import fly.frontend.entity.model.UserTag;
import fly.frontend.service.UserTagService;
import org.springframework.stereotype.Service;

@Service
public class UserTagServiceImpl extends ServiceImpl<UserTagMapper, UserTag> implements UserTagService {
}
