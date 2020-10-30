package fly.admin.service.impl;

import fly.admin.entity.model.AdminUser;
import fly.admin.repository.AdminRoleRepository;
import fly.admin.repository.AdminUserRepository;
import fly.admin.repository.AdminUserRoleRepository;
import fly.admin.service.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private AdminUserRepository adminUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AdminUser user = adminUserRepository.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException(" not fount user by "+username);
        }
        return new UserDetails(user);
    }
}
