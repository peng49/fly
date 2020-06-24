package fly.frontend.controller;

import fly.frontend.entity.Post;
import fly.frontend.entity.PostComment;
import fly.frontend.entity.User;
import fly.frontend.pojo.PostAdd;
import fly.frontend.pojo.PostCommentAdd;
import fly.frontend.service.ColumnService;
import fly.frontend.service.PostService;
import fly.frontend.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/post")
public class PostController {
    @Resource
    private PostService postService;

    @Resource
    private ColumnService columnService;

    @GetMapping("/add")
    public ModelAndView add(ModelAndView view) {
        view.addObject("columns", columnService.getAll());
        view.setViewName("/post/add");
        return view;
    }

    @PostMapping("/add")
    @ResponseBody
    public Map add(@RequestBody PostAdd post, HttpSession httpSession) throws Exception {
        User user = (User) httpSession.getAttribute("login-user");
        if (user == null) {
            throw new Exception("请先登录");
        }

        Post po = postService.create(post,user);

        Map<Object, Object> map = new HashMap<>();
        map.put("code", "success");
        map.put("message", "OK");
        map.put("post", po);
        return map;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") int id, ModelAndView view) {
        view.setViewName("/post/add");
        return view;
    }

    @GetMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable("id") int id, ModelAndView view) {
        Post post = postService.findById(id);
        view.addObject("post", post);
        view.addObject("comments",postService.getComments(id));
        view.setViewName("/post/detail");
        return view;
    }

    @PostMapping("/addComment")
    @ResponseBody
    public Map<Object, Object> addComment(@RequestBody PostCommentAdd postCommentAdd, HttpSession httpSession)
    {
        User user = (User) httpSession.getAttribute(UserService.LOGIN_KEY);
        PostComment comment = postService.addComment(user,postCommentAdd);
        Map<Object, Object> map = new HashMap<>();
        map.put("code", "success");
        map.put("message", "OK");
        map.put("comment", comment);
        return map;
    }
}
