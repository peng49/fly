package fly.web.config;

import com.jagregory.shiro.freemarker.ShiroTags;
import fly.web.entity.model.Navigation;
import fly.web.entity.model.SystemConfig;
import fly.web.service.NavigationService;
import fly.web.service.SystemConfigService;
import freemarker.template.Configuration;
import freemarker.template.TemplateModelException;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@org.springframework.context.annotation.Configuration
public class FreemarkerConfiguration {

    @Resource
    private Configuration configuration;

    @Resource
    private NavigationService navigationService;

    @Resource
    private SystemConfigService systemConfigService;

    /**
     * 设置 freemarker 共享变量
     * @throws TemplateModelException
     */
    @PostConstruct
    public void setFreeMarkerShareVariables() throws TemplateModelException {
        List<Navigation> navigations = navigationService.lambdaQuery()
                .eq(Navigation::getStatus, 1)
                .orderByAsc(Navigation::getSort)
                .list();
        configuration.setSharedVariable("__nav__",navigations);

        List<SystemConfig> configs = systemConfigService.lambdaQuery().list();
        Map<String, String> __setting__ = new HashMap<>();
        configs.forEach(config -> {
            __setting__.put(config.getAttribute(), config.getValue());
        });
        configuration.setSharedVariable("__setting__", __setting__);

        configuration.setSharedVariable("shiro",new ShiroTags());
    }
}
