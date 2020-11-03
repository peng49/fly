package fly.frontend.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import fly.frontend.entity.from.PostFilterCondition;
import fly.frontend.entity.model.Post;
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
    public ModelAndView index(HttpServletRequest request, ModelAndView view, @RequestParam(defaultValue = "all") String list,
                              @RequestParam(value = "orderBy", defaultValue = "") String orderBy) {
        PostFilterCondition condition = new PostFilterCondition();
        condition.setList(list);
        condition.setStatus(PostService.PUBLISH_STATUS);//已发布的

        if (!"".equals(orderBy) && PostService.ALLOW_ORDER_FIELD.contains(orderBy)) {
            condition.setOrderBy(orderBy + " desc");
        } else {
            condition.setOrderBy("heat desc");
        }
        System.out.println(condition);

        view.addObject("topPosts", postService.findTop(4));

        Page<Post> page = new Page<>();
        view.addObject("posts", postService.getByCondition(page, condition));

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

        view.addObject("posts", postService.findAllPublishByAuthorId(id));

        view.addObject("comments", postCommentService.getByUserId(id));


        if (HttpUtils.isMobile(request)) {
            view.setViewName("wap/user/home");
        } else {
            view.setViewName("user/home");
        }
        return view;
    }
}
