package fly.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fly.web.dao.UserCategoryMapper;
import fly.web.entity.model.UserCategory;
import fly.web.service.UserCategoryService;
import org.springframework.stereotype.Service;

@Service
public class UserCategoryServiceImpl extends ServiceImpl<UserCategoryMapper, UserCategory> implements UserCategoryService {
}
