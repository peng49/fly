package fly.admin.service;

import fly.admin.entity.model.SystemConfig;
import fly.admin.entity.vo.ResultVO;

import java.util.Map;

public interface SystemConfigService {
    SystemConfig add(SystemConfig config);

    void  delete(SystemConfig config);

    SystemConfig update(SystemConfig config);

    SystemConfig get(int id);

    ResultVO search(int page, int pageSize, Map<String,Object> query);
}
