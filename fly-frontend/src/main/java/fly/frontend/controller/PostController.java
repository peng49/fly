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
import org.springframework.validation.annotation.Validated;
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
    public Map add(@RequestBody @Validated PostAdd post, HttpSession httpSession) throws Exception {
        User user = (User) httpSession.getAttribute("login-user");
        if (user == null) {
            throw new Exception("请先登录");
        }

        Post po = postService.create(post, user);

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
    public ModelAndView detail(@PathVariable("id") int id, ModelAndView view, HttpSession httpSession) {
        Post post = postService.findById(id);
        view.addObject("post", post);
        view.addObject("user", (User) httpSession.getAttribute(UserService.LOGIN_KEY));
        view.addObject("comments", postService.getComments(id));
        view.setViewName("/post/detail");
        return view;
    }

    @PostMapping("/addComment")
    @ResponseBody
    public Map<Object, Object> addComment(@RequestBody @Validated PostCommentAdd postCommentAdd, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute(UserService.LOGIN_KEY);
        PostComment comment = postService.addComment(user, postCommentAdd);
        Map<Object, Object> map = new HashMap<>();
        map.put("code", "success");
        map.put("message", "OK");
        map.put("comment", comment);
        return map;
    }

    /**
     * 置顶/取消置顶
     *
     * @return
     */
    @PostMapping("/top")
    @ResponseBody
    public Map<Object, Object> top(@RequestParam(value = "postId") int postId, HttpSession httpSession) throws Exception {
        User user = (User) httpSession.getAttribute(UserService.LOGIN_KEY);
        if (user.getIsAdmin() != 1) {
            throw new Exception("您不是管理员，不能进行此操作");
        }

        Post post = postService.findById(postId);
        postService.top(post);

        Map<Object, Object> map = new HashMap<>();
        map.put("code", "success");
        map.put("message", "OK");
        return map;
    }

    /**
     * 加精
     *
     * @return
     */
    @PostMapping("/essence")
    @ResponseBody
    public Map<Object, Object> essence(@RequestParam("postId") int postId, HttpSession httpSession) throws Exception {
        User user = (User) httpSession.getAttribute(UserService.LOGIN_KEY);
        if (user.getIsAdmin() != 1) {
            throw new Exception("您不是管理员，不能进行此操作");
        }

        Post post = postService.findById(postId);
        postService.essence(post);

        Map<Object, Object> map = new HashMap<>();
        map.put("code", "success");
        map.put("message", "OK");
        return map;
    }
}
