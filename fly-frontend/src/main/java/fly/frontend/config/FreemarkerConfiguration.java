package fly.frontend.config;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jagregory.shiro.freemarker.ShiroTags;
import fly.frontend.entity.model.Column;
import fly.frontend.service.ColumnService;
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
    private ColumnService columnService;

    /**
     * 设置 freemarker 共享变量
     * @throws TemplateModelException
     */
    @PostConstruct
    public void setFreeMarkerShareVariables() throws TemplateModelException {
        Page<Column> page = new Page<>(1,6);
        List<Column> columns = columnService.page(page).getRecords();
        configuration.setSharedVariable("columns",columns);
        configuration.setSharedVariable("shiro",new ShiroTags());
    }
}
