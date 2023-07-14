package fly.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import fly.web.entity.model.SystemConfig;

public interface SystemConfigService extends IService<SystemConfig> {
    String getValue(String attribute);
}
