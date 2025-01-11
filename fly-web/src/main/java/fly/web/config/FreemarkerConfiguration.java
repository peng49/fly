package fly.web.config;


import fly.web.entity.model.Navigation;
import fly.web.entity.model.SystemConfig;
import fly.web.service.NavigationService;
import fly.web.service.SystemConfigService;

import freemarker.template.TemplateModelException;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class FreemarkerConfiguration {

    private final freemarker.template.Configuration configuration;

    private final NavigationService navigationService;

    private final SystemConfigService systemConfigService;

    public FreemarkerConfiguration(
            freemarker.template.Configuration configuration,
            NavigationService navigationService,
            SystemConfigService systemConfigService) {
        this.configuration = configuration;
        this.navigationService = navigationService;
        this.systemConfigService = systemConfigService;
    }

    /**
     * 设置 freemarker 共享变量
     */
    @PostConstruct
    public void setFreeMarkerShareVariables() throws TemplateModelException {
        List<Navigation> navigations = navigationService.lambdaQuery()
                .eq(Navigation::getStatus, 1)
                .orderByAsc(Navigation::getSort)
                .list();
        configuration.setSharedVariable("__nav__", navigations);

        List<SystemConfig> configs = systemConfigService.lambdaQuery().list();
        Map<String, String> __setting__ = new HashMap<>();
        configs.forEach(config -> {
            __setting__.put(config.getAttribute(), config.getValue());
        });
        configuration.setSharedVariable("__setting__", __setting__);
    }
}
