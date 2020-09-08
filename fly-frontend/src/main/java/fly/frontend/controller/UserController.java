package fly.frontend.controller;

import fly.frontend.entity.*;
import fly.frontend.pojo.*;
import fly.frontend.service.*;
import fly.frontend.utils.HttpUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotBlank;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/user")
public class UserController {
    @Value("${user.avatar-dir}")
    private String userDir;

    @Resource
    private UserService userService;

    @Resource
    private PostService postService;

    @Resource
    private UserMessageService userMessageService;

    @Resource
    private UserPostService userPostService;

    @Resource
    private PostCommentAgreeService postCommentAgreeService;

    @Resource
    private PostCommentService postCommentService;


    @GetMapping("/login")
    public ModelAndView login(ModelAndView view, HttpSession httpSession, @RequestParam(value = "redirect", defaultValue = "") String redirect, HttpServletRequest request) {
        if (httpSession.getAttribute("login-user") != null) {
            //todo redirect
        }
        view.addObject("redirect", redirect);

        if (HttpUtils.isMobile(request)) {
            view.setViewName("wap/user/login");
        } else {
            view.setViewName("user/login");
        }

        return view;
    }

    @PostMapping("/login")
    @ResponseBody
    public Object login(@RequestBody @Validated UserLogin login, HttpSession httpSession) throws Exception {
        System.out.println(login);
        User user = userService.login(login);
        httpSession.setAttribute(UserService.LOGIN_KEY, user);
        return HttpUtils.success(user);
    }

    @GetMapping("/logout")
    public void logout(HttpServletResponse response, HttpSession httpSession) throws IOException {
        httpSession.removeAttribute(UserService.LOGIN_KEY);
        response.sendRedirect("/");//重定向到首页
    }

    @GetMapping("/register")
    public ModelAndView register(ModelAndView view, HttpServletRequest request) {
        if (HttpUtils.isMobile(request)) {
            view.setViewName("wap/user/register");
        } else {
            view.setViewName("user/register");
        }
        return view;
    }

    @PostMapping("/register")
    @ResponseBody
    public Object register(@RequestBody @Validated UserRegister register) throws Exception {
        User user = userService.register(register);
        return HttpUtils.success(user);
    }

    @GetMapping("/center")
    public ModelAndView home(ModelAndView view, HttpSession httpSession, HttpServletRequest request) {
        User user = (User) httpSession.getAttribute(UserService.LOGIN_KEY);
        view.addObject("user", user);

        if (HttpUtils.isMobile(request)) {
            view.setViewName("wap/user/center");
        } else {
            view.setViewName("user/center");
        }
        return view;
    }

    @GetMapping("/posts")
    @ResponseBody
    public Object posts(@RequestParam("type") String type, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute(UserService.LOGIN_KEY);

        List<Post> posts = null;
        if ("my".equals(type)) {
            posts = postService.findAllByAuthorId(user.getId());
        } else {
            posts = userService.findCollectionPosts(user.getId());
        }
        return HttpUtils.success(posts);
    }

    @GetMapping("/info")
    @ResponseBody
    public Object info(HttpSession session) {
        User user = (User) session.getAttribute(UserService.LOGIN_KEY);
        user = userService.getById(user.getId());
        return HttpUtils.success(user);
    }

    @PostMapping("/updateInfo")
    @ResponseBody
    public Object updateInfo(@RequestBody UpdateUserInfo userInfo, HttpSession session) {
        User user = (User) session.getAttribute(UserService.LOGIN_KEY);
        User res = userService.updateInfo(user, userInfo);
        return HttpUtils.success(res);
    }


    @PostMapping("/updatePassword")
    @ResponseBody
    public Object updatePassword(@RequestBody UpdatePassword updatePassword,
                                 HttpSession httpSession) throws Exception {
        User user = (User) httpSession.getAttribute(UserService.LOGIN_KEY);
        userService.updatePassword(user, updatePassword);

        //退出重新登录
        httpSession.removeAttribute(UserService.LOGIN_KEY);
        return HttpUtils.success();
    }

    @PostMapping("/collection")
    @ResponseBody
    public Object collection(@RequestBody @Validated Map<String,@NotBlank(message = "值不能为空") Integer> request, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute(UserService.LOGIN_KEY);
        Integer postId = request.get("postId");
        if (userPostService.isExisted(user.getId(), postId)) {
           userPostService.delete(user, postId);
        } else {
            userPostService.create(user, postId);
        }
        return HttpUtils.success();
    }

    @GetMapping("/messages")
    @ResponseBody
    public Object messages(HttpSession httpSession) {
        User user = (User) httpSession.getAttribute(UserService.LOGIN_KEY);
        List<UserMessage> messages = userMessageService.getMessagesForUser(user);

        return HttpUtils.success(messages);
    }

    @PostMapping("/commentAgree")
    @ResponseBody
    public Object commentAgree(@RequestBody Map<String,Integer> request,HttpSession httpSession)
    {
        User user = (User) httpSession.getAttribute(UserService.LOGIN_KEY);
        Integer commentId = request.get("commentId");
        if(postCommentAgreeService.isExisted(user,commentId)){
            postCommentAgreeService.delete(user,commentId);
            postCommentService.commentAgreeDec(commentId);
        }else{
            PostComment comment = new PostComment();
            comment.setId(commentId);
            PostCommentAgree postCommentAgree = new PostCommentAgree();
            postCommentAgree.setUser(user);
            postCommentAgree.setPostComment(comment);
            postCommentAgreeService.create(postCommentAgree);
            postCommentService.commentAgreeInc(commentId);
        }

        return HttpUtils.success();
    }

    @PostMapping("/uploadAvatar")
    @ResponseBody
    public Object uploadAvatar(HttpServletRequest request, HttpSession session) throws IOException {
        User user = (User) session.getAttribute(UserService.LOGIN_KEY);
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        if (multipartResolver.isMultipart(request)) {
            //将request变成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            //获取multiRequest 中所有的文件名
            Iterator<String> iter = multiRequest.getFileNames();
            while (iter.hasNext()) {
                //一次遍历所有文件
                MultipartFile file = multiRequest.getFile(iter.next());
                if (file != null) {
                    String filename = UUID.randomUUID() + Objects.requireNonNull(file.getOriginalFilename()).substring(file.getOriginalFilename().indexOf('.')).toLowerCase();

                    String path = userDir + filename;
                    //上传
                    file.transferTo(new File(path));
                    userService.updateAvatar(user, "/static/" + filename);
                }
            }
        }
        return HttpUtils.success(user);
    }
}
