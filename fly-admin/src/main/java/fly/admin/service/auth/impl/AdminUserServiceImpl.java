package fly.admin.service.auth.impl;

import fly.admin.entity.model.AdminUser;
import fly.admin.repository.AdminUserRepository;
import fly.admin.service.auth.AdminUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AdminUserServiceImpl implements AdminUserService {

    @Resource
    private AdminUserRepository adminUserRepository;

    @Override
    public AdminUser add(AdminUser user) {
        return adminUserRepository.save(user);
    }

    @Override
    public void delete(AdminUser user) {
        adminUserRepository.delete(user);
    }

    @Override
    public AdminUser update(AdminUser user) {
        return adminUserRepository.save(user);
    }

    @Override
    public AdminUser get(int id) {
        return adminUserRepository.getOne(id);
    }
}
