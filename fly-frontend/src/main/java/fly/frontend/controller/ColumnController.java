package fly.frontend.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import fly.frontend.entity.Column;
import fly.frontend.pojo.PostFilter;
import fly.frontend.pojo.PostFilterCondition;
import fly.frontend.service.ColumnService;
import fly.frontend.service.PostService;
import fly.frontend.utils.HttpUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/column")
public class ColumnController {

    @Resource
    private PostService postService;

    @Resource
    private ColumnService columnService;

    @RequestMapping("/{id}")
    public ModelAndView show(@PathVariable("id") int id, PostFilter filter, ModelAndView view, HttpServletRequest request) {
        PostFilterCondition condition = new PostFilterCondition();
        condition.setColumnId(id);
        condition.setList(filter.getList());
        condition.setStatus(1);//已发布的

        if (filter.getOrderBy() != null && PostService.ALLOW_ORDER_FIELD.contains(filter.getOrderBy())) {
            condition.setOrderBy(filter.getOrderBy() + " desc");
        } else {
            condition.setOrderBy("heat desc");
        }

        PageHelper.startPage(filter.getPage(), filter.getPageSize());
        Page posts = (Page) postService.getByCondition(condition);
        view.addObject("posts", posts);

        Column column = columnService.get(id);
        view.addObject("column", column);

        view.addObject("list_total", posts.getTotal());
        view.addObject("current_page", posts.getPageNum());

        view.addObject("page_size", posts.getPageSize() > 0 ? posts.getPageSize() : 10);

        view.addObject("next_url", HttpUtils.setUrlParam(HttpUtils.getCurrentUrl(request), "page", String.valueOf(posts.getPageNum() + 1)));

        if (HttpUtils.isMobile(request)) {
            view.setViewName("wap/post/list");
        } else {
            view.setViewName("post/list");
        }
        return view;
    }
}
