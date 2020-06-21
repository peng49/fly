package fly.frontend.controller;

import fly.frontend.entity.Post;
import fly.frontend.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/")
public class HomepageController {
    @Resource
    private PostService postService;

    @RequestMapping("/")
    public ModelAndView index(ModelAndView view) {
        List<Post> posts = postService.findAll();
        view.addObject("posts",posts);
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
