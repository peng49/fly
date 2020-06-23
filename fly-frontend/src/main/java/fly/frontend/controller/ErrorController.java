package fly.frontend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/error")
public class ErrorController {

    @GetMapping("/{code}")
    public ModelAndView error(@PathVariable("code") int code, ModelAndView view)
    {
        String pager = "/page/tips";
        switch (code){
            case 404:
                pager = "/page/404";
                break;
        }
        view.setViewName(pager);
        return view;
    }
}
