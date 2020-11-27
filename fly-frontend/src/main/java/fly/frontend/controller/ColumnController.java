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
    public ModelAndView show(
            @PathVariable("id") int id,
            PostFilter filter,
            ModelAndView view,
            HttpServletRequest request) {
        PostFilterCondition condition = new PostFilterCondition();
        condition.setColumnId(id);
        condition.setList(filter.getList());
        condition.setStatus(PostService.PUBLISH_STATUS);//已发布的

        if (filter.getOrderBy() != null && PostService.ALLOW_ORDER_FIELD.contains(filter.getOrderBy())) {
            condition.setOrderBy(filter.getOrderBy());
        } else {
            condition.setOrderBy("heat");
        }


        Page<Post> page = new Page<>();
        page.setCurrent(filter.getPage()).setSize(filter.getPageSize());
        IPage<PostVO> posts = postService.getByCondition(page, condition);
        view.addObject("posts", posts.getRecords());

        Column column = columnService.getById(id);
        view.addObject("column", column);

        view.addObject("listTotal", posts.getTotal());
        view.addObject("currentPage", posts.getCurrent());

        view.addObject("pageSize", posts.getSize() > 0 ? posts.getSize() : 10);

        view.addObject("nextUrl", HttpUtils.setUrlParam(HttpUtils.getCurrentUrl(request), "page", String.valueOf(posts.getCurrent() + 1)));

        HttpUtils.selectViewName("post/list", request, view);

        return view;
    }
}
