package fly.frontend.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import fly.frontend.entity.from.UpdatePasswordFrom;
import fly.frontend.entity.from.UpdateUserInfoFrom;
import fly.frontend.entity.from.UserLoginFrom;
import fly.frontend.entity.from.UserRegisterFrom;
import fly.frontend.entity.model.Post;
import fly.frontend.entity.model.PostCommentAgree;
import fly.frontend.entity.model.User;
import fly.frontend.entity.vo.PostVO;
import fly.frontend.entity.vo.UserVO;
import fly.frontend.service.*;
import fly.frontend.utils.HttpUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
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

        HttpUtils.selectViewName("user/login",request,view);
        return view;
    }

    @PostMapping("/login")
    @ResponseBody
    public Object login(@RequestBody @Validated UserLoginFrom from) {
        // 从SecurityUtils里边创建一个 subject
        Subject subject = SecurityUtils.getSubject();
        // 在认证提交前准备 token（令牌）
        UsernamePasswordToken token = new UsernamePasswordToken(from.getUsername(), from.getPassword());
        // 执行认证登陆
        try {
            subject.login(token);
            if (subject.isAuthenticated()) {
                return HttpUtils.success();
            } else {
                token.clear();
                return HttpUtils.fail("登录失败！");
            }
        } catch (AuthenticationException ae) {
            return HttpUtils.fail("用户名或密码不正确！");
        }
    }

    @GetMapping("/logout")
    public void logout(HttpServletResponse response, HttpSession httpSession) throws IOException {
        httpSession.removeAttribute(UserService.LOGIN_KEY);
        response.sendRedirect("/");//重定向到首页
    }

    @GetMapping("/register")
    public ModelAndView register(ModelAndView view, HttpServletRequest request) {
        HttpUtils.selectViewName("user/register",request,view);
        return view;
    }

    @GetMapping("/forget")
    public ModelAndView forget(ModelAndView view, HttpServletRequest request) {
        HttpUtils.selectViewName("user/forget",request,view);
        return view;
    }

    @PostMapping("/register")
    @ResponseBody
    public Object register(@RequestBody @Validated UserRegisterFrom register) throws Exception {
        User user = userService.register(register);
        return HttpUtils.success(user);
    }

    @GetMapping("/center")
    public ModelAndView home(ModelAndView view, HttpSession httpSession, HttpServletRequest request) {
        UserVO user = HttpUtils.getCurrentUser();
        System.out.println(user);
        view.addObject("user", user);

        HttpUtils.selectViewName("user/center", request, view);
        return view;
    }

    @GetMapping("/posts")
    @ResponseBody
    public Object posts(@RequestParam("type") String type,
                        @RequestParam(name = "page", defaultValue = "1") int page,
                        @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                        HttpSession httpSession) {
        UserVO user = HttpUtils.getCurrentUser();

        IPage<PostVO> list;
        if ("my".equals(type)) {
            list = postService.findAllByAuthorId(new Page<>(page, pageSize), user.getId());
        } else {
            list = postService.findUserPost(new Page<>(page, pageSize),user.getId());
        }
        return HttpUtils.success(list);
    }

    @GetMapping("/info")
    @ResponseBody
    public Object info(HttpSession session) {
        UserVO user = HttpUtils.getCurrentUser();
        user = userService.get(user.getId());
        return HttpUtils.success(user);
    }

    @PostMapping("/updateInfo")
    @ResponseBody
    public Object updateInfo(@RequestBody UpdateUserInfoFrom userInfo, HttpSession session) {
        User user = (User) session.getAttribute(UserService.LOGIN_KEY);
        User res = userService.updateInfo(user, userInfo);
        return HttpUtils.success(res);
    }


    @PostMapping("/updatePassword")
    @ResponseBody
    public Object updatePassword(@RequestBody @Validated UpdatePasswordFrom updatePassword,
                                 HttpSession httpSession) throws Exception {
        User user = (User) httpSession.getAttribute(UserService.LOGIN_KEY);
        userService.updatePassword(user, updatePassword);

        //退出重新登录
        httpSession.removeAttribute(UserService.LOGIN_KEY);
        return HttpUtils.success();
    }

    @PostMapping("/collection")
    @ResponseBody
    public Object collection(@RequestBody @Validated Map<String, @NotBlank(message = "值不能为空") Long> request) {
        UserVO user = HttpUtils.getCurrentUser();
        Long postId = request.get("postId");
        if (userPostService.isExisted(user.getId(), postId)) {
            userPostService.delete(user.getId(), postId);
        } else {
            userPostService.create(user.getId(), postId);
        }
        return HttpUtils.success();
    }

    @PostMapping("/commentAgree")
    @ResponseBody
    public Object commentAgree(@RequestBody Map<String, Long> request, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute(UserService.LOGIN_KEY);
        Long commentId = request.get("commentId");
        if (postCommentAgreeService.isExisted(user, commentId)) {
            postCommentAgreeService.delete(user, commentId);
            postCommentService.commentAgreeDec(commentId);
        } else {
            PostCommentAgree postCommentAgree = new PostCommentAgree();
            postCommentAgree.setUserId(user.getId());
            postCommentAgree.setPostCommentId(commentId);
            postCommentAgreeService.save(postCommentAgree);
            postCommentService.commentAgreeInc(commentId);
        }

        return HttpUtils.success();
    }

    @PostMapping("/uploadAvatar")
    @ResponseBody
    public Object uploadAvatar(HttpServletRequest request, HttpSession session) throws IOException {
        UserVO user = (UserVO) session.getAttribute(UserService.LOGIN_KEY);
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
                    userService.updateAvatar(userService.getById(user.getId()), "/static/" + filename);
                }
            }
        }
        return HttpUtils.success(user);
    }
}
