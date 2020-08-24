package fly.frontend.controller;

import com.github.pagehelper.PageHelper;
import fly.frontend.pojo.PostFilterCondition;
import fly.frontend.service.PostCommentService;
import fly.frontend.service.PostService;
import fly.frontend.service.UserService;
import fly.frontend.utils.HttpUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @Resource
    private UserService userService;

    @Resource
    private PostCommentService postCommentService;

    @RequestMapping("/")
    public ModelAndView index(HttpServletRequest request, ModelAndView view, @RequestParam(defaultValue = "all") String list) {
        PostFilterCondition condition = new PostFilterCondition();
        condition.setList(list);
        condition.setStatus(1);//已发布的

        view.addObject("topPosts", postService.findTop(4));

        view.addObject("posts", postService.getByCondition(condition));

        if (HttpUtils.isMobile(request)) {
            view.setViewName("wap/index");
        } else {
            view.setViewName("index");
        }
        return view;
    }


    @GetMapping("/u/{id}")
    public ModelAndView index(@PathVariable("id") int id, ModelAndView view, HttpServletRequest request) {
        view.addObject("user", userService.getById(id));
        PageHelper.startPage(1, 10);
        view.addObject("posts", postService.findAllPublishByAuthorId(id));
        PageHelper.startPage(1, 5);
        view.addObject("comments", postCommentService.getByUserId(id));
        PageHelper.clearPage();

        if (HttpUtils.isMobile(request)) {
            view.setViewName("wap/user/home");
        } else {
            view.setViewName("user/home");
        }


        return view;
    }
}
