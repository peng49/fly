package fly.frontend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {
    @GetMapping("/error")
    public ModelAndView error(ModelAndView view) {
        view.setViewName("/page/404");
        return view;
    }


    @GetMapping("/error/{code}")
    public ModelAndView error(@PathVariable("code") String code, ModelAndView view) {
        String pager = "/page/tips";
        switch (code) {
            case "404":
                pager = "/page/404";
                break;
        }
        view.setViewName(pager);
        return view;
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
