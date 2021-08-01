package fly.frontend.config;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jagregory.shiro.freemarker.ShiroTags;
import fly.frontend.entity.model.Column;
import fly.frontend.entity.model.Navigation;
import fly.frontend.service.ColumnService;
import fly.frontend.service.NavigationService;
import freemarker.template.Configuration;
import freemarker.template.TemplateModelException;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

@org.springframework.context.annotation.Configuration
public class FreemarkerConfiguration {

    @Resource
    private Configuration configuration;

    @Resource
    private NavigationService navigationService;

    /**
     * 设置 freemarker 共享变量
     * @throws TemplateModelException
     */
    @PostConstruct
    public void setFreeMarkerShareVariables() throws TemplateModelException {
        Page<Navigation> page = new Page<>();
        page.setCurrent(1).setSize(10);

        Page<Navigation> navigationPage = navigationService.lambdaQuery()
                .eq(Navigation::getStatus, 1)
                .orderByAsc(Navigation::getSort)
                .page(page);
        configuration.setSharedVariable("__nav__",navigationPage.getRecords());
        configuration.setSharedVariable("shiro",new ShiroTags());
    }
}
