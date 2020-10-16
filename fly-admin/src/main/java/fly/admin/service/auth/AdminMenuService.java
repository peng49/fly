package fly.admin.service.auth;

import fly.admin.entity.model.AdminMenu;

import java.util.List;

public interface AdminMenuService {

    AdminMenu add(AdminMenu menu);

    void delete(AdminMenu menu);

    AdminMenu update(AdminMenu menu);

    AdminMenu get(int id);

    List<AdminMenu> findAll();
}
