package fly.frontend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fly.frontend.dao.UserCategoryMapper;
import fly.frontend.entity.model.UserCategory;
import fly.frontend.service.UserCategoryService;
import org.springframework.stereotype.Service;

@Service
public class UserCategoryServiceImpl extends ServiceImpl<UserCategoryMapper, UserCategory> implements UserCategoryService {
}
