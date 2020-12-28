package fly.frontend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import fly.frontend.entity.model.SystemConfig;

public interface SystemConfigService extends IService<SystemConfig> {
    String getValue(String attribute);
}
