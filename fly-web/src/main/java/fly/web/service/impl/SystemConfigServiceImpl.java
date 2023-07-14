package fly.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fly.web.dao.SystemConfigMapper;
import fly.web.entity.model.SystemConfig;
import fly.web.service.SystemConfigService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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
