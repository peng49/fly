package fly.frontend.controller;

import fly.frontend.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@Controller
@RequestMapping("/column")
public class ColumnController {

    @Resource
    private PostService postService;

    @RequestMapping("/{id}")
    public ModelAndView show(@PathVariable("id") int id, ModelAndView view) {
        view.addObject("posts",postService.findByColumnId(id));
        view.setViewName("post/list");
        return view;
    }
}
