package fly.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fly.web.dao.UserTagMapper;
import fly.web.entity.model.UserTag;
import fly.web.service.UserTagService;
import org.springframework.stereotype.Service;

@Service
public class UserTagServiceImpl extends ServiceImpl<UserTagMapper, UserTag> implements UserTagService {
}
