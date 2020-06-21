package fly.frontend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/post")
public class PostController {

    @GetMapping("/add")
    public ModelAndView add(ModelAndView view){
        view.setViewName("/post/add");
        return view;
    }

    @GetMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable("id") int id, ModelAndView view){
        view.setViewName("/post/detail");
        return view;
    }
}
