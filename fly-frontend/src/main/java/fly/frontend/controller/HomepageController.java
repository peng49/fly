package fly.frontend.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import fly.frontend.entity.from.PostFilterCondition;
import fly.frontend.entity.model.Post;
import fly.frontend.service.PostCommentService;
import fly.frontend.service.PostService;
import fly.frontend.service.UserService;
import fly.frontend.utils.HttpUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

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
    public ModelAndView index(HttpServletRequest request,
                              ModelAndView view,
                              @RequestParam(value = "list", defaultValue = "all") String list,
                              @RequestParam(value = "orderBy", defaultValue = "") String orderBy) {
        PostFilterCondition condition = new PostFilterCondition();
        condition.setList(list);
        condition.setStatus(PostService.PUBLISH_STATUS);//已发布的

        if (!"".equals(orderBy) && PostService.ALLOW_ORDER_FIELD.contains(orderBy)) {
            condition.setOrderBy(orderBy);
        } else {
            condition.setOrderBy("heat");
        }

        Page<Post> page = new Page<>();
        page.setCurrent(1).setSize(20);
        view.addObject("posts", postService.getByCondition(page, condition).getRecords());

        if (HttpUtils.isMobile(request)) {
            view.setViewName("wap/index");
        } else {
            view.setViewName("index");
        }
        return view;
    }


    @GetMapping("/u/{id}")
    public ModelAndView index(@PathVariable("id") Long id, ModelAndView view, HttpServletRequest request) {
        view.addObject("user", userService.get(id));

        Collection<Object> keys = SecurityUtils.getSubject().getSession().getAttributeKeys();
        System.out.println(keys);

        view.addObject("posts", postService.findAllPublishByAuthorId(id));

        view.addObject("comments", postCommentService.getByUserId(new Page<>(1, 8), id).getRecords());

        if (HttpUtils.isMobile(request)) {
            view.setViewName("wap/user/home");
        } else {
            view.setViewName("user/home");
        }
        return view;
    }
}
