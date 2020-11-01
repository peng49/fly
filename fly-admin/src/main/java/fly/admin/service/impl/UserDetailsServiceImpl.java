package fly.admin.service.impl;

import fly.admin.entity.model.AdminUser;
import fly.admin.repository.AdminUserRepository;
import fly.admin.service.auth.AdminUserService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private AdminUserRepository adminUserRepository;

    @Resource
    private AdminUserService adminUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AdminUser user = adminUserRepository.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException(" not fount user by "+username);
        }
        return new User(user.getUsername(), user.getPassword(), adminUserService.getAuthorities(user));
//        return new UserDetails(user,adminUserService.getAuthorities(user));
    }
}
