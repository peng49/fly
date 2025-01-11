package fly.web.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import fly.web.entity.from.UpdatePasswordFrom;
import fly.web.entity.from.UpdateUserInfoFrom;
import fly.web.entity.from.UserRegisterFrom;
import fly.web.entity.model.User;
import fly.web.entity.vo.PostVO;
import fly.web.entity.vo.UserVO;
import fly.web.service.PostCommentAgreeService;
import fly.web.service.PostCommentService;
import fly.web.service.PostService;
import fly.web.service.UserService;
import fly.web.utils.HttpUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import java.util.UUID;

import java.io.File;
import java.io.IOException;

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
    private PostCommentAgreeService postCommentAgreeService;

    @Resource
    private PostCommentService postCommentService;


    @GetMapping("/login")
    public ModelAndView login(ModelAndView view, @RequestParam(value = "redirect", defaultValue = "") String redirect, @RequestHeader("User-Agent") String userAgent) {
        /*if (httpSession.getAttribute("login-user") != null) {
            //todo redirect
        }*/
        view.addObject("redirect", redirect);

        HttpUtils.selectViewName("user/login", userAgent, view);
        return view;
    }

    @GetMapping("/register")
    public ModelAndView register(ModelAndView view, @RequestHeader("User-Agent") String userAgent) {
        HttpUtils.selectViewName("user/register", userAgent, view);
        return view;
    }

    @GetMapping("/forget")
    public ModelAndView forget(ModelAndView view, @RequestHeader("User-Agent") String userAgent) {
        HttpUtils.selectViewName("user/forget", userAgent, view);
        return view;
    }

    @PostMapping("/register")
    @ResponseBody
    public Object register(@RequestBody @Validated UserRegisterFrom register) throws Exception {
        User user = userService.register(register);
        return HttpUtils.success(user);
    }

    @GetMapping("/center")
    public ModelAndView home(ModelAndView view, @RequestHeader("User-Agent") String userAgent) {
        UserVO user = HttpUtils.getCurrentUser();
        System.out.println(user);
        view.addObject("user", user);

        HttpUtils.selectViewName("user/center", userAgent, view);
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
            list = postService.findUserPost(new Page<>(page, pageSize), user.getId());
        }
        return HttpUtils.success(list);
    }

    @GetMapping("/info")
    @ResponseBody
    public Object info() {
        UserVO user = HttpUtils.getCurrentUser();
        user = userService.get(user.getId());
        return HttpUtils.success(user);
    }

    @PostMapping("/updateInfo")
    @ResponseBody
    public Object updateInfo(@RequestBody UpdateUserInfoFrom userInfo) {
        UserVO userVO = HttpUtils.getCurrentUser();
        User res = userService.updateInfo(userService.getById(userVO.getId()), userInfo);
        return HttpUtils.success(res);
    }


    @PostMapping("/updatePassword")
    @ResponseBody
    public Object updatePassword(@RequestBody @Validated UpdatePasswordFrom updatePassword,
                                 HttpSession httpSession) throws Exception {
        UserVO userVO = HttpUtils.getCurrentUser();
        userService.updatePassword(userService.getById(userVO.getId()), updatePassword);

        //退出重新登录
        httpSession.removeAttribute(UserService.LOGIN_KEY);
        return HttpUtils.success();
    }

    @PostMapping("/uploadAvatar")
    @ResponseBody
    public Object uploadAvatar(@RequestParam("avatar") MultipartFile file) {
        UserVO user = HttpUtils.getCurrentUser();
        File uploadFile = new File(userDir);
        if (!uploadFile.exists()) {
            boolean b = uploadFile.mkdirs();
        }
        String filename = UUID.randomUUID() + file.getOriginalFilename().substring(file.getOriginalFilename().indexOf('.')).toLowerCase();
        File dest = new File(userDir + filename);
        try {
            // 保存文件到指定目录
            file.transferTo(dest);
            userService.updateAvatar(userService.getById(user.getId()), "/static/" + filename);
            user.setAvatar("/static/" + filename);
            return HttpUtils.success(user);
        } catch (IOException e) {
            return HttpUtils.fail("上传失败！！,请重新操作");
        }
    }
}
