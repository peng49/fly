package fly.frontend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/column")
public class ColumnController {

    @RequestMapping("/{id}")
    public ModelAndView show(@PathVariable("id") int id, ModelAndView view) {
        view.setViewName("post/list");
        return view;
    }
}
