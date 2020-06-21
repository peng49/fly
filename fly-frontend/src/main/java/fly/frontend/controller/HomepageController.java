package fly.frontend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/homepage")
public class HomepageController {

    @RequestMapping("/index")
    public ModelAndView index(ModelAndView view) {
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
