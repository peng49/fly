package fly.frontend.configuration;

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
        List<Column> columns = columnService.getAll();
        configuration.setSharedVariable("columns",columns);
    }
}
