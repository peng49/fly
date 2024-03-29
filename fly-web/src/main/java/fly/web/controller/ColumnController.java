package fly.web.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import fly.web.entity.from.PostFilterCondition;
import fly.web.entity.model.Column;
import fly.web.entity.model.Post;
import fly.web.entity.vo.PostVO;
import fly.web.entity.dto.PostFilterDTO;
import fly.web.enums.PostStatus;
import fly.web.service.ColumnService;
import fly.web.service.PostService;
import fly.web.utils.HttpUtils;
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
            PostFilterDTO filter,
            ModelAndView view,
            HttpServletRequest request) {
        PostFilterCondition condition = new PostFilterCondition();
        condition.setColumnId(id);
        condition.setList(filter.getList());
        condition.setStatus(PostStatus.PUBLISHED.getStatus());//已发布的

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
