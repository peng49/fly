package fly.frontend.controller;

import fly.frontend.entity.po.Post;
import fly.frontend.entity.po.PostComment;
import fly.frontend.entity.po.User;
import fly.frontend.pojo.PostEdit;
import fly.frontend.pojo.PostCommentAdd;
import fly.frontend.service.ColumnService;
import fly.frontend.service.PostCommentService;
import fly.frontend.service.PostService;
import fly.frontend.service.UserService;
import fly.frontend.utils.HttpUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/post")
public class PostController {
    @Resource
    private PostService postService;

    @Value("${user.avatar-dir}")
    private String userDir;

    @Resource
    private ColumnService columnService;

    @Resource
    private PostCommentService postCommentService;

    @GetMapping("/add")
    public ModelAndView add(ModelAndView view, HttpServletRequest request) {
        view.addObject("columns", columnService.getAll());

        if (HttpUtils.isMobile(request)) {
            view.setViewName("wap/post/edit");
        } else {
            view.setViewName("/post/edit");
        }
        return view;
    }

    @PostMapping("/add")
    @ResponseBody
    public Object add(@RequestBody @Validated PostEdit post, HttpSession httpSession) throws Exception {
        User user = (User) httpSession.getAttribute("login-user");
        if (user == null) {
            throw new Exception("请先登录");
        }
        Post po = postService.create(post, user);
        return HttpUtils.success(po);
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") int id, ModelAndView view, HttpServletRequest request) {
        Post post = postService.get(id);
        view.addObject("columns", columnService.getAll());
        view.addObject("post", post);
        if (HttpUtils.isMobile(request)) {
            view.setViewName("/wap/post/edit");
        } else {
            view.setViewName("/post/edit");
        }
        return view;
    }

    @PostMapping("/edit/{id}")
    @ResponseBody
    public Object edit(@PathVariable("id") int id, @RequestBody @Validated PostEdit postEdit) {
        Post post = postService.get(id);
        postService.edit(post, postEdit);
        return HttpUtils.success();
    }

    @GetMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable("id") int id, ModelAndView view, HttpSession httpSession, HttpServletRequest request,HttpServletResponse response) {
        Post post = postService.get(id);
        boolean allowEdit = false;
        User user = (User) httpSession.getAttribute(UserService.LOGIN_KEY);
        if (user != null && user.getId() == post.getAuthor().getId()) {
            allowEdit = true;
        }

        if (post.getStatus() != 1 && (user == null || user.getId() != post.getAuthor().getId())) {
            //不是作者不能看未发布的文章
            response.setStatus(404);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        view.addObject("post", post);
        view.addObject("user", user);
        view.addObject("comments", postService.getComments(id));
        view.addObject("allowEdit", allowEdit);
        if (HttpUtils.isMobile(request)) {
            view.setViewName("wap/post/detail");
        } else {
            view.setViewName("/post/detail");
        }
        postService.viewCountInc(id);
        return view;
    }

    @PostMapping("/addComment")
    @ResponseBody
    public Object addComment(@RequestBody @Validated PostCommentAdd postCommentAdd, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute(UserService.LOGIN_KEY);
        PostComment comment = postCommentService.create(user, postCommentAdd);
        return HttpUtils.success(comment);
    }


    /**
     * 置顶/取消置顶
     *
     * @return
     */
    @PostMapping("/top")
    @ResponseBody
    public Object top(@RequestParam(value = "postId") int postId, HttpSession httpSession) throws Exception {
        adminCheck(httpSession);
        Post post = postService.get(postId);
        postService.top(post);
        return HttpUtils.success();
    }

    private void adminCheck(HttpSession httpSession) throws Exception {
        User user = (User) httpSession.getAttribute(UserService.LOGIN_KEY);
        if (user.getIsAdmin() != 1) {
            throw new Exception("您不是管理员，不能进行此操作");
        }
    }

    /**
     * 加精
     *
     * @param postId      文章Id
     * @param httpSession session
     * @return map response json
     * @throws Exception
     */
    @PostMapping("/essence")
    @ResponseBody
    public Object essence(@RequestParam("postId") int postId, HttpSession httpSession) throws Exception {
        adminCheck(httpSession);

        Post post = postService.get(postId);
        postService.essence(post);
        return HttpUtils.success();
    }


    @PostMapping("/upload")
    @ResponseBody
    public Object upload(HttpServletRequest request, HttpSession session) throws IOException {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        String url = "";
        if (multipartResolver.isMultipart(request)) {
            //将request变成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            //获取multiRequest 中所有的文件名
            Iterator<String> iter = multiRequest.getFileNames();
            while (iter.hasNext()) {
                //一次遍历所有文件
                MultipartFile file = multiRequest.getFile(iter.next().toString());
                if (file != null) {
                    String filename = UUID.randomUUID() + Objects.requireNonNull(file.getOriginalFilename()).substring(file.getOriginalFilename().indexOf('.')).toLowerCase();
                    String path = userDir + filename;
                    //上传
                    file.transferTo(new File(path));
                    url = "/static/" + filename;
                }
            }
        }
        return HttpUtils.success(url);
    }
}
