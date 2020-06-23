package fly.frontend.controller;

import fly.frontend.entity.Post;
import fly.frontend.entity.User;
import fly.frontend.pojo.UserLogin;
import fly.frontend.pojo.UserRegister;
import fly.frontend.service.PostService;
import fly.frontend.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private PostService postService;

    @GetMapping("/index/{id}")
    public ModelAndView index(@PathVariable("id") int id, ModelAndView view) {
        view.addObject("user", userService.getById(id));
        view.addObject("posts", postService.findByAuthorId(id));
        view.setViewName("user/index");
        return view;
    }

    @GetMapping("/login")
    public ModelAndView login(ModelAndView view, HttpSession httpSession) {
//        System.out.println(httpSession.getAttribute("login-user"));
        view.setViewName("user/login");
        return view;
    }

    @PostMapping("/login")
    @ResponseBody
    public Map login(@RequestBody UserLogin login, HttpSession httpSession) throws Exception {
//        System.out.println(login);
        User user = userService.login(login);
        Map<Object, Object> map = new HashMap<>();
        map.put("code", "success");
        map.put("message", "OK");
        map.put("user", user);

        httpSession.setAttribute(UserService.LOGIN_KEY, user);
        return map;
    }

    @GetMapping("/register")
    public ModelAndView register(ModelAndView view) {
        view.setViewName("user/register");
        return view;
    }

    @PostMapping("/register")
    @ResponseBody
    public Map<Object, Object> register(@RequestBody UserRegister register) {
        User user = userService.register(register);
        System.out.println(register);

        Map<Object, Object> map = new HashMap<>();
        map.put("code", "success");
        map.put("message", "OK");
        map.put("user", user);
        return map;
    }

    @GetMapping("/home")
    public ModelAndView home(ModelAndView view) {
        view.setViewName("user/home");
        return view;
    }

    @GetMapping("/posts")
    @ResponseBody
    public Map<Object, Object> posts(@RequestParam("type") String type, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute(UserService.LOGIN_KEY);

        List<Post> posts = null;
        if ("my".equals(type)) {
            posts = postService.findByAuthorId(user.getId());
        } else {
            posts = userService.findCollectionPosts(user.getId());
        }
        Map<Object, Object> map = new HashMap<>();
        map.put("code", "success");
        map.put("message", "OK");
        map.put("posts", posts);
        return map;
    }

    @GetMapping("/info")
    @ResponseBody
    public Map<Object, Object> info(HttpSession session) {
        User user = (User) session.getAttribute(UserService.LOGIN_KEY);
        user = userService.getById(user.getId());

        Map<Object, Object> map = new HashMap<>();
        map.put("code", "success");
        map.put("message", "OK");
        map.put("user", user);
        return map;
    }
}
