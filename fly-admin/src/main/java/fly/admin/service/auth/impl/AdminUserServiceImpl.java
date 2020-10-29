package fly.admin.service.auth.impl;

import fly.admin.entity.model.AdminUser;
import fly.admin.entity.model.AdminUserPermission;
import fly.admin.entity.model.AdminUserRole;
import fly.admin.entity.model.User;
import fly.admin.entity.request.EditAdminUserRequest;
import fly.admin.entity.vo.AdminUserVO;
import fly.admin.entity.vo.UserLoginVO;
import fly.admin.repository.AdminUserPermissionRepository;
import fly.admin.repository.AdminUserRepository;
import fly.admin.repository.AdminUserRoleRepository;
import fly.admin.service.auth.AdminUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AdminUserServiceImpl implements AdminUserService {

    @Resource
    private AdminUserRepository adminUserRepository;

    @Resource
    private SimpleDateFormat simpleDateFormat;

    @Resource
    private AdminUserRoleRepository adminUserRoleRepository;

    @Resource
    private AdminUserPermissionRepository adminUserPermissionRepository;


    @Override
    @Transactional
    public AdminUser add(AdminUser user, EditAdminUserRequest request) {
        AdminUser adminUser = adminUserRepository.save(user);
        saveUserPermissions(adminUser, request.getPermissionIds());
        saveUserRoles(adminUser, request.getRoleIds());

        return adminUser;
    }

    @Override
    public void delete(AdminUser user) {
        adminUserRepository.delete(user);
    }

    @Override
    @Transactional
    public AdminUser update(AdminUser user, EditAdminUserRequest request) {
        user.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

        saveUserPermissions(user, request.getPermissionIds());
        saveUserRoles(user, request.getRoleIds());

        return adminUserRepository.save(user);
    }

    private void saveUserRoles(AdminUser user, int[] roleIds) {
        adminUserRoleRepository.deleteByUserId(user.getId());
        List<AdminUserRole> items = new ArrayList<>();
        for (int roleId : roleIds) {
            items.add(AdminUserRole.builder().userId(user.getId()).roleId(roleId).build());
        }
        if (items.size() > 0) {
            adminUserRoleRepository.saveAll(items);
        }
    }

    private void saveUserPermissions(AdminUser user, int[] permissionIds) {
        adminUserPermissionRepository.deleteByUserId(user.getId());
        List<AdminUserPermission> items = new ArrayList<>();
        for (int permissionId : permissionIds) {
            items.add(AdminUserPermission.builder().userId(user.getId()).permissionId(permissionId).build());
        }
        if (items.size() > 0) {
            adminUserPermissionRepository.saveAll(items);
        }
    }

    @Override
    public List<AdminUserVO> search() {
        List<AdminUser> users = adminUserRepository.findAll();

        List<AdminUserVO> result = new ArrayList<>();

        List<Integer> userIds = users.stream().map(AdminUser::getId).collect(Collectors.toList());

        List<AdminUserRole> roleList = adminUserRoleRepository.findByUserIdIn(userIds);

        List<AdminUserPermission> permissionList = adminUserPermissionRepository.findByUserIdIn(userIds);

        Map<Integer, List<AdminUserRole>> roleMap = roleList.stream().collect(Collectors.groupingBy(AdminUserRole::getUserId));

        Map<Integer, List<AdminUserPermission>> permissionMap = permissionList.stream().collect(Collectors.groupingBy(AdminUserPermission::getUserId));

        users.forEach(user -> {
            result.add(
                    AdminUserVO.builder()
                            .id(user.getId())
                            .username(user.getUsername())
                            .name(user.getName())
                            .avatar(user.getAvatar())
                            .roleIds(
                                    Optional.ofNullable(roleMap.get(user.getId())).orElse(new ArrayList<>())
                                            .stream()
                                            .map(AdminUserRole::getRoleId)
                                            .collect(Collectors.toList())
                            )
                            .permissionIds(
                                    Optional.ofNullable(permissionMap.get(user.getId()))
                                            .orElse(new ArrayList<>())
                                            .stream()
                                            .map(AdminUserPermission::getPermissionId)
                                            .collect(Collectors.toList())
                            )
                            .updatedAt(user.getUpdatedAt() == null ? null : simpleDateFormat.format(user.getUpdatedAt()))
                            .createdAt(user.getCreatedAt() == null ? null : simpleDateFormat.format(user.getCreatedAt()))
                            .build()
            );
        });
        return result;
    }

    @Override
    public AdminUser get(int id) {
        return adminUserRepository.getOne(id);
    }

    @Override
    public UserLoginVO login(String username, String password) {
        AdminUser user = adminUserRepository.findByUsername(username);
        return UserLoginVO.builder()
                .username(user.getUsername())
                .avatar(user.getAvatar())
                .token("admin-token")
                .build();
    }
}
