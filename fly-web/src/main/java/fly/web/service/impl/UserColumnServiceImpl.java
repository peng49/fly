package fly.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fly.web.dao.UserColumnMapper;
import fly.web.entity.model.UserColumn;
import fly.web.service.UserColumnService;
import org.springframework.stereotype.Service;

@Service
public class UserColumnServiceImpl extends ServiceImpl<UserColumnMapper, UserColumn> implements UserColumnService {
}
