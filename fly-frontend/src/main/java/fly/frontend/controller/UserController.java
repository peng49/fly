package fly.frontend.controller;

import fly.frontend.entity.Post;
import fly.frontend.entity.User;
import fly.frontend.pojo.UpdateUserInfo;
import fly.frontend.pojo.UserLogin;
import fly.frontend.pojo.UserRegister;
import fly.frontend.service.PostService;
import fly.frontend.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
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
    public Map<Object, Object> login(@RequestBody UserLogin login, HttpSession httpSession) throws Exception {
//        System.out.println(login);
        User user = userService.login(login);
        Map<Object, Object> map = new HashMap<>();
        map.put("code", "success");
        map.put("message", "OK");
        map.put("user", user);

        httpSession.setAttribute(UserService.LOGIN_KEY, user);
        return map;
    }

    @GetMapping("/logout")
    public void logout(HttpServletResponse response, HttpSession httpSession) throws IOException {
        httpSession.removeAttribute(UserService.LOGIN_KEY);
        response.sendRedirect("/");//重定向到首页
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

    @PostMapping("/updateInfo")
    @ResponseBody
    public Map<Object, Object> updateInfo(@RequestBody UpdateUserInfo userInfo, HttpSession session) {
        User user = (User) session.getAttribute(UserService.LOGIN_KEY);
        User res = userService.updateInfo(user, userInfo);

        Map<Object, Object> map = new HashMap<>();
        map.put("code", "success");
        map.put("message", "OK");
        map.put("user", res);
        return map;
    }

    @PostMapping("/uploadAvatar")
    @ResponseBody
    public Map<Object, Object> uploadAvatar(HttpServletRequest request, HttpSession session) throws IOException {
//        User user = (User) session.getAttribute(UserService.LOGIN_KEY);
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        if (multipartResolver.isMultipart(request)) {
            //将request变成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            //获取multiRequest 中所有的文件名
            Iterator iter = multiRequest.getFileNames();
            while (iter.hasNext()) {
                //一次遍历所有文件
                MultipartFile file = multiRequest.getFile(iter.next().toString());
                if (file != null) {
                    System.out.println(file.getOriginalFilename());
                    String path = "C:/springUpload/" + file.getOriginalFilename();
                    //上传
                    file.transferTo(new File(path));
                }
            }
        }

//        System.out.println(userInfo);
//        User res = userService.updateInfo(user, userInfo);
//        System.out.println(userInfo);
        Map<Object, Object> map = new HashMap<>();
        map.put("code", "success");
        map.put("message", "OK");
//        map.put("user", res);
        return map;
    }
}
