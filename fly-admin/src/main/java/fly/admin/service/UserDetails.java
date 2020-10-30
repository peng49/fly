package fly.admin.service;

import fly.admin.entity.model.AdminRole;
import fly.admin.entity.model.AdminUser;
import fly.admin.entity.model.AdminUserRole;
import fly.admin.repository.AdminRoleRepository;
import fly.admin.repository.AdminUserRoleRepository;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserDetails implements org.springframework.security.core.userdetails.UserDetails {
    private AdminUser user;

    @Resource
    private AdminUserRoleRepository adminUserRoleRepository;

    @Resource
    private AdminRoleRepository adminRoleRepository;

    public  UserDetails(AdminUser adminUser) {
        this.user = adminUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<AdminUserRole> roleList = adminUserRoleRepository.findByUserId(user.getId());
        List<AdminRole> roles = adminRoleRepository.findAllById(
                roleList.stream()
                        .map(AdminUserRole::getRoleId)
                        .collect(Collectors.toList())
        );

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        roles.forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getSlug()));
        });
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
