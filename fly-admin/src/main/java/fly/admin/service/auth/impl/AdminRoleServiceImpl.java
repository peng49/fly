package fly.admin.service.auth.impl;

import fly.admin.entity.model.AdminRole;
import fly.admin.entity.model.AdminRolePermission;
import fly.admin.entity.request.EditAdminRoleRequest;
import fly.admin.repository.AdminRolePermissionRepository;
import fly.admin.repository.AdminRoleRepository;
import fly.admin.service.auth.AdminRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdminRoleServiceImpl implements AdminRoleService {

    @Resource
    private AdminRoleRepository adminRoleRepository;

    @Resource
    private AdminRolePermissionRepository adminRolePermissionRepository;

    @Override
    public AdminRole add(EditAdminRoleRequest request) {
        AdminRole role = AdminRole.builder()
                .name(request.getName())
                .slug(request.getSlug())
                .build();

        adminRoleRepository.save(role);
        if(request.getPermissionIds() != null){
            saveAdminRolePermission(role,request.getPermissionIds());
        }

        return role;
    }

    private void saveAdminRolePermission(AdminRole role,int[] permissionIds){
        List<AdminRolePermission> rolePermissions = new ArrayList<>();
        for (int permissionId : permissionIds) {
            rolePermissions.add(
                    AdminRolePermission.builder()
                            .roleId(role.getId())
                            .permissionId(permissionId)
                            .build()
            );
        }
        adminRolePermissionRepository.saveAll(rolePermissions);
    }

    @Override
    public void delete(AdminRole role) {
        adminRoleRepository.delete(role);
    }

    @Override
    @Transactional
    public AdminRole update(AdminRole role, EditAdminRoleRequest request) {
        role.setName(request.getName());
        role.setSlug(request.getSlug());

        adminRolePermissionRepository.deleteByRoleId(role.getId());

        int[] permissionIds = request.getPermissionIds();
        if(permissionIds != null){
            saveAdminRolePermission(role,permissionIds);
        }
        return adminRoleRepository.save(role);
    }

    @Override
    public AdminRole get(int id) {
        return adminRoleRepository.getOne(id);
    }

    @Override
    public List<AdminRole> search() {
        return adminRoleRepository.findAll();
    }
}
