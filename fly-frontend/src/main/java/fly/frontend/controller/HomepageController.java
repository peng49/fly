package fly.frontend.controller;

import fly.frontend.pojo.PostFilterCondition;
import fly.frontend.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.regex.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
public class HomepageController {
    @Resource
    private PostService postService;

    protected boolean isMobile(HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent");
        return Pattern.matches(".*(phone|pad|pod|iPhone|iPod|ios|iPad|Android|Mobile|BlackBerry|IEMobile|MQQBrowser|JUC|Fennec|wOSBrowser|BrowserNG|WebOS|Symbian|Windows Phone).*", userAgent);
    }

    @RequestMapping("/")
    public ModelAndView index(HttpServletRequest request, ModelAndView view, @RequestParam(defaultValue = "all") String list) {
        PostFilterCondition condition = new PostFilterCondition();
        condition.setList(list);

        view.addObject("topPosts", postService.findTop(4));
        view.addObject("posts", postService.getByCondition(condition));

        if (this.isMobile(request)) {
            view.setViewName("wap/index");
        } else {
            view.setViewName("index");
        }
        return view;
    }
}
