package fly.admin.service.auth.impl;

import fly.admin.entity.model.AdminMenu;
import fly.admin.repository.AdminMenuRepository;
import fly.admin.service.auth.AdminMenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AdminMenuServiceImpl implements AdminMenuService {
    @Resource
    private AdminMenuRepository adminMenuRepository;


    @Override
    public AdminMenu add(AdminMenu menu) {
        return adminMenuRepository.save(menu);
    }

    @Override
    public void delete(AdminMenu menu) {
        adminMenuRepository.delete(menu);
    }

    @Override
    public AdminMenu update(AdminMenu menu) {
        return adminMenuRepository.save(menu);
    }

    @Override
    public AdminMenu get(int id) {
        return adminMenuRepository.getOne(id);
    }

    @Override
    public List<AdminMenu> findAll() {
        return adminMenuRepository.findAll();
    }
}
