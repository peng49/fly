package fly.frontend.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import fly.frontend.entity.from.PostFilterCondition;
import fly.frontend.entity.model.Column;
import fly.frontend.entity.model.Post;
import fly.frontend.entity.vo.PostVO;
import fly.frontend.pojo.PostFilter;
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
        condition.setStatus(PostService.PUBLISH_STATUS);//已发布的

        if (filter.getOrderBy() != null && PostService.ALLOW_ORDER_FIELD.contains(filter.getOrderBy())) {
            condition.setOrderBy(filter.getOrderBy() + " desc");
        } else {
            condition.setOrderBy("heat desc");
        }


        Page<Post> page = new Page<>();
        page.setCurrent(filter.getPage()).setSize(filter.getPageSize());
        IPage<PostVO> posts = postService.getByCondition(page, condition);
        view.addObject("posts", posts.getRecords());

        Column column = columnService.get(id);
        view.addObject("column", column);

        view.addObject("list_total", posts.getTotal());
        view.addObject("current_page", posts.getCurrent());

        view.addObject("page_size", posts.getPages() > 0 ? posts.getPages() : 10);

        view.addObject("next_url", HttpUtils.setUrlParam(HttpUtils.getCurrentUrl(request), "page", String.valueOf(posts.getCurrent() + 1)));

        if (HttpUtils.isMobile(request)) {
            view.setViewName("wap/post/list");
        } else {
            view.setViewName("post/list");
        }
        return view;
    }
}
