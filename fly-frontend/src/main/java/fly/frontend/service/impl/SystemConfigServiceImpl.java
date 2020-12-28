package fly.frontend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fly.frontend.dao.SystemConfigMapper;
import fly.frontend.entity.model.SystemConfig;
import fly.frontend.service.SystemConfigService;

import java.util.List;

public class SystemConfigServiceImpl extends ServiceImpl<SystemConfigMapper, SystemConfig> implements SystemConfigService {
    @Override
    public String getValue(String attribute) {
        List<SystemConfig> list = lambdaQuery().eq(SystemConfig::getAttribute, attribute).list();
        if (list.size() == 0) {
            return "";
        }
        return list.get(0).getValue();
    }
}
