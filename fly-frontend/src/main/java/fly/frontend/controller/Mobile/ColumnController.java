package fly.frontend.controller.Mobile;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller("Mobile.ColumnController")
@RequestMapping("/m/c")
public class ColumnController {

    @GetMapping("/{id}")
    public ModelAndView list(@PathVariable("id") int id, ModelAndView view)
    {
        view.setViewName("wap/post/list");
        return view;
    }

}
