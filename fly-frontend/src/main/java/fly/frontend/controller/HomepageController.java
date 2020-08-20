package fly.frontend.controller;

import fly.frontend.pojo.PostFilterCondition;
import fly.frontend.service.PostService;
import fly.frontend.utils.HttpUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
public class HomepageController {
    @Resource
    private PostService postService;

    @RequestMapping("/")
    public ModelAndView index(HttpServletRequest request, ModelAndView view, @RequestParam(defaultValue = "all") String list) {
        PostFilterCondition condition = new PostFilterCondition();
        condition.setList(list);

        view.addObject("topPosts", postService.findTop(4));
        view.addObject("posts", postService.getByCondition(condition));

        if (HttpUtils.isMobile(request)) {
            view.setViewName("wap/index");
        } else {
            view.setViewName("index");
        }
        return view;
    }
}
