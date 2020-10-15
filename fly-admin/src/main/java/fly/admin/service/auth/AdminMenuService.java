package fly.admin.service.auth;

import fly.admin.entity.model.AdminMenu;

public interface AdminMenuService {

    AdminMenu add(AdminMenu menu);

    void delete(AdminMenu menu);

    AdminMenu update(AdminMenu menu);

    AdminMenu get(int id);
}
