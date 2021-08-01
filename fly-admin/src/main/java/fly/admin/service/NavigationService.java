package fly.admin.service;

import fly.admin.entity.model.Navigation;
import fly.admin.entity.vo.ResultVO;

import java.util.Map;

public  interface NavigationService {
    Navigation add(Navigation navigation);

    void delete(Navigation navigation);

    Navigation update(Navigation navigation);

    Navigation get(int id);

    ResultVO search(int page, int pageSize, Map<String,Object> query);
}
