package fly.frontend.controller;

import fly.frontend.pojo.PostAdd;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("/post")
public class PostController {
    @GetMapping("/add")
    public ModelAndView add(ModelAndView view) {
        view.setViewName("/post/add");
        return view;
    }

    @PostMapping("/add")
    @ResponseBody
    public Map add(@RequestBody PostAdd post) {
        return null;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") int id, ModelAndView view) {
        view.setViewName("/post/add");
        return view;
    }

    @GetMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable("id") int id, ModelAndView view) {
        view.setViewName("/post/detail");
        return view;
    }
}
