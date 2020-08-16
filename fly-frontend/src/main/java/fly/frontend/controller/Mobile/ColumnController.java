package fly.frontend.controller.Mobile;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller("Mobile.ColumnController")
@RequestMapping("/m/c")
public class ColumnController {

    @GetMapping("/{id}")
    public ModelAndView list(ModelAndView view)
    {
        view.setViewName("m/post/list");
        return view;
    }

}
