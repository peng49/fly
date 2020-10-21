package fly.admin.service.auth.impl;

import fly.admin.entity.model.AdminUser;
import fly.admin.entity.vo.AdminUserVO;
import fly.admin.entity.vo.UserLoginVO;
import fly.admin.repository.AdminUserRepository;
import fly.admin.service.auth.AdminUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdminUserServiceImpl implements AdminUserService {

    @Resource
    private AdminUserRepository adminUserRepository;

    @Resource
    private SimpleDateFormat simpleDateFormat;

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
    public List<AdminUserVO> search() {
        List<AdminUser> users = adminUserRepository.findAll();

        List<AdminUserVO> result = new ArrayList<>();

        users.forEach(user -> {
            result.add(
                    AdminUserVO.builder()
                            .id(user.getId())
                            .username(user.getUsername())
                            .avatar(user.getAvatar())
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
