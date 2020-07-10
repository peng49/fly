package fly.frontend.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import fly.frontend.pojo.PostFilter;
import fly.frontend.pojo.PostFilterCondition;
import fly.frontend.service.PostService;
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

    @RequestMapping("/{id}")
    public ModelAndView show(@PathVariable("id") int id, PostFilter filter, ModelAndView view, HttpServletRequest request) {
        PageHelper.startPage(filter.getPage(),filter.getPageSize());

        PostFilterCondition condition = new PostFilterCondition();
        condition.setColumnId(id);
        condition.setList(filter.getList());

        view.addObject("posts",(Page)postService.getByCondition(condition));
        view.setViewName("post/list");
        return view;
    }
}
