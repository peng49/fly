package fly.admin.service.auth.impl;

import fly.admin.entity.model.AdminPermission;
import fly.admin.entity.model.AdminRole;
import fly.admin.entity.model.AdminRolePermission;
import fly.admin.entity.request.EditAdminRoleRequest;
import fly.admin.entity.vo.AdminRoleVO;
import fly.admin.repository.AdminPermissionRepository;
import fly.admin.repository.AdminRolePermissionRepository;
import fly.admin.repository.AdminRoleRepository;
import fly.admin.service.auth.AdminRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Service
public class AdminRoleServiceImpl implements AdminRoleService {

    @Resource
    private AdminRoleRepository adminRoleRepository;

    @Resource
    private AdminRolePermissionRepository adminRolePermissionRepository;

    @Resource
    private AdminPermissionRepository adminPermissionRepository;

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
    public void delete(Integer roleId) {
        adminRoleRepository.deleteById(roleId);
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
    public AdminRole findOne(int id) {
        return adminRoleRepository.getOne(id);
    }

    @Override
    public AdminRoleVO get(int id) {
        AdminRole role = adminRoleRepository.getOne(id);
        List<AdminRolePermission> list = adminRolePermissionRepository.findByRoleId(role.getId());

        List<AdminPermission> permissions = new ArrayList<>();
        for (AdminRolePermission rolePermission : list) {
            permissions.add(adminPermissionRepository.getOne(rolePermission.getPermissionId()));
        }

        return AdminRoleVO.builder()
                .id(role.getId())
                .name(role.getName())
                .slug(role.getSlug())
                .permissions(permissions)
                .build();
    }

    @Override
    public List<AdminRoleVO> search() {
        List<AdminRole> roles = adminRoleRepository.findAll();

        List<Integer> roleIds = new ArrayList<>();
        for (AdminRole role : roles) {
            roleIds.add(role.getId());
        }

        List<AdminRolePermission> allRolePermissions = adminRolePermissionRepository.findByRoleIdIn(roleIds);

        List<Integer> permissionIds = new ArrayList<>();
        HashMap<Integer, List<Integer>> permissionMap = new HashMap<>();
        for (AdminRolePermission rolePermission : allRolePermissions) {
            permissionIds.add(rolePermission.getPermissionId());
            if(permissionMap.get(rolePermission.getRoleId()) == null){
                permissionMap.put(rolePermission.getRoleId(),new ArrayList<Integer>(){{add(rolePermission.getPermissionId());}});
//                permissionMap.put(rolePermission.getRoleId(), Arrays.asList(rolePermission.getPermissionId()));
            }else{
                permissionMap.get(rolePermission.getRoleId()).add(rolePermission.getPermissionId());
            }
        }

        List<AdminPermission> allPermission = adminPermissionRepository.findByIdIn(permissionIds);

        HashMap<Integer, AdminPermission> map = new HashMap<>();
        for (AdminPermission permission : allPermission) {
            map.put(permission.getId(),permission);
        }

        List<AdminRoleVO> result = new ArrayList<>();

        for (AdminRole role : roles) {
            List<Integer> rolePermissionIds = permissionMap.get(role.getId());
            List<AdminPermission> rolePermissions = new ArrayList<>();

            if(rolePermissionIds != null){
                for (Integer permissionId : rolePermissionIds) {
                    rolePermissions.add(map.get(permissionId));
                }
            }
            result.add(AdminRoleVO.builder()
                    .id(role.getId())
                    .name(role.getName())
                    .slug(role.getSlug())
                    .permissions(rolePermissions)
                    .createdAt(role.getCreatedAt())
                    .updatedAt(role.getUpdatedAt())
                    .build());
        }
        return result;
    }
}
