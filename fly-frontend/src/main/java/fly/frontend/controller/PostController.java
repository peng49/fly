package fly.frontend.controller;

import fly.frontend.entity.Post;
import fly.frontend.entity.PostComment;
import fly.frontend.entity.User;
import fly.frontend.pojo.PostEdit;
import fly.frontend.pojo.PostCommentAdd;
import fly.frontend.service.ColumnService;
import fly.frontend.service.PostService;
import fly.frontend.service.UserService;
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

    @GetMapping("/add")
    public ModelAndView add(ModelAndView view) {
        view.addObject("columns", columnService.getAll());
        view.setViewName("/post/edit");
        return view;
    }

    @PostMapping("/add")
    @ResponseBody
    public Map add(@RequestBody @Validated PostEdit post, HttpSession httpSession) throws Exception {
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
        Post post = postService.get(id);
        view.addObject("columns", columnService.getAll());
        view.addObject("post", post);
        view.setViewName("/post/edit");
        return view;
    }

    @PostMapping("/edit/{id}")
    @ResponseBody
    public Map edit(@PathVariable("id") int id, @RequestBody @Validated PostEdit postEdit) {
        Post post = postService.get(id);
        System.out.println(post);
        System.out.println(postEdit);
        postService.edit(post, postEdit);

        Map<Object, Object> map = new HashMap<>();
        map.put("code", "success");
        map.put("message", "OK");
        return map;
    }

    @GetMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable("id") int id, ModelAndView view, HttpSession httpSession) {
        Post post = postService.get(id);
        boolean allowEdit = false;
        User user = (User) httpSession.getAttribute(UserService.LOGIN_KEY);
        if (user != null && user.getId() == post.getAuthor().getId()) {
            allowEdit = true;
        }
        System.out.println(allowEdit);
        view.addObject("post", post);
        view.addObject("user", user);
        view.addObject("comments", postService.getComments(id));
        view.addObject("allowEdit", allowEdit);
        view.setViewName("/post/detail");
        postService.viewCountInc(id);
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

        Post post = postService.get(postId);
        postService.top(post);

        Map<Object, Object> map = new HashMap<>();
        map.put("code", "success");
        map.put("message", "OK");
        return map;
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
    public Map<Object, Object> essence(@RequestParam("postId") int postId, HttpSession httpSession) throws Exception {
        User user = (User) httpSession.getAttribute(UserService.LOGIN_KEY);
        if (user.getIsAdmin() != 1) {
            throw new Exception("您不是管理员，不能进行此操作");
        }

        Post post = postService.get(postId);
        postService.essence(post);

        Map<Object, Object> map = new HashMap<>();
        map.put("code", "success");
        map.put("message", "OK");
        return map;
    }


    @PostMapping("/upload")
    @ResponseBody
    public Map<Object, Object> upload(HttpServletRequest request, HttpSession session) throws IOException {
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
        
        Map<Object, Object> map = new HashMap<>();
        map.put("code", "success");
        map.put("message", "OK");
        map.put("url", url);
        return map;
    }
}
