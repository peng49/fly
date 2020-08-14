package fly.frontend.controller;

import fly.frontend.pojo.PostFilterCondition;
import fly.frontend.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@Controller
@RequestMapping("/")
public class HomepageController {
    @Resource
    private PostService postService;

    @RequestMapping("/")
    public ModelAndView index(ModelAndView view, @RequestParam(defaultValue = "all") String list) {
        PostFilterCondition condition = new PostFilterCondition();
        condition.setList(list);

        view.addObject("topPosts",postService.findTop(4));
        view.addObject("posts",postService.getByCondition(condition));
        view.setViewName("index");
        return view;
    }

    @RequestMapping("/test")
    public ModelAndView test() {
        ModelAndView view = new ModelAndView("test");
        view.addObject("word", "test");
        return view;
    }
}
