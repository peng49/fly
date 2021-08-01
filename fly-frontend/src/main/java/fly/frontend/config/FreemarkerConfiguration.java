package fly.frontend.config;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jagregory.shiro.freemarker.ShiroTags;
import fly.frontend.entity.model.Column;
import fly.frontend.entity.model.Navigation;
import fly.frontend.entity.model.SystemConfig;
import fly.frontend.service.ColumnService;
import fly.frontend.service.NavigationService;
import fly.frontend.service.SystemConfigService;
import freemarker.template.Configuration;
import freemarker.template.TemplateModelException;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

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
