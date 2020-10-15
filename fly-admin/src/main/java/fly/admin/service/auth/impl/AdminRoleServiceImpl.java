package fly.admin.service.auth.impl;

import fly.admin.entity.model.AdminRole;
import fly.admin.repository.AdminRoleRepository;
import fly.admin.service.auth.AdminRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AdminRoleServiceImpl implements AdminRoleService {

    @Resource
    private AdminRoleRepository adminRoleRepository;

    @Override
    public AdminRole add(AdminRole role) {
        return adminRoleRepository.save(role);
    }

    @Override
    public void delete(AdminRole role) {
        adminRoleRepository.delete(role);
    }

    @Override
    public AdminRole update(AdminRole role) {
        return adminRoleRepository.save(role);
    }

    @Override
    public AdminRole get(int id) {
        return adminRoleRepository.getOne(id);
    }
}
