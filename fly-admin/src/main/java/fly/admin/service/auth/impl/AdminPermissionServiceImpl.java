package fly.admin.service.auth.impl;

import fly.admin.entity.model.AdminPermission;
import fly.admin.repository.AdminPermissionRepository;
import fly.admin.service.auth.AdminPermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AdminPermissionServiceImpl implements AdminPermissionService {

    @Resource
    private AdminPermissionRepository adminPermissionRepository;

    @Override
    public AdminPermission add(AdminPermission permission) {
        return adminPermissionRepository.save(permission);
    }

    @Override
    public void delete(AdminPermission permission) {
        adminPermissionRepository.delete(permission);
    }

    @Override
    public AdminPermission update(AdminPermission permission) {
        return adminPermissionRepository.save(permission);
    }

    @Override
    public AdminPermission get(int id) {
        return adminPermissionRepository.getOne(id);
    }

    @Override
    public List<AdminPermission> search() {
        return adminPermissionRepository.findAll();
    }
}
