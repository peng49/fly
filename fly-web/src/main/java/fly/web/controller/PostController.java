package fly.web.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import fly.web.dao.PostMapper;
import fly.web.entity.from.PostCommentAddFrom;
import fly.web.entity.from.PostEditFrom;
import fly.web.entity.model.Post;
import fly.web.entity.model.PostAutoDraft;
import fly.web.entity.model.PostComment;
import fly.web.entity.vo.PostCommentVO;
import fly.web.entity.vo.PostVO;
import fly.web.entity.vo.UserVO;
import fly.web.enums.PostStatus;
import fly.web.service.*;
import fly.web.utils.HttpUtils;
import lombok.extern.slf4j.Slf4j;
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
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Objects;
import java.util.UUID;

@Controller
@RequestMapping("/post")
@Slf4j
public class PostController {
    @Resource
    private PostService postService;

    @Resource
    private UserService userService;

    @Value("${user.avatar-dir}")
    private String userDir;

    @Resource
    private ColumnService columnService;

    @Resource
    private PostMapper postMapper;

    @Resource
    private PostCommentService postCommentService;

    @Resource
    private PostAutoDraftService postAutoDraftService;

    @GetMapping("/add")
    public ModelAndView add(ModelAndView view, HttpServletRequest request) {
        view.addObject("columns", columnService.list());

        HttpUtils.selectViewName("/post/edit", request, view);
        return view;
    }

    @PostMapping("/add")
    @ResponseBody
    public Object add(@RequestBody @Validated PostEditFrom postEditFrom) {
        UserVO user = HttpUtils.getCurrentUser();
        Post post = postService.create(postEditFrom, user.getId());

        PostAutoDraft draft = postAutoDraftService.getForUserId(post.getAuthorId());
        if (draft != null) {
            postAutoDraftService.removeById(draft.getId());
        }
        return HttpUtils.success(post);
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id, ModelAndView view, HttpServletRequest request) {
        Post post = postMapper.selectById(id);
        view.addObject("columns", columnService.list());
        view.addObject("post", post);

        HttpUtils.selectViewName("/post/edit", request, view);

        return view;
    }

    @PostMapping("/draft")
    @ResponseBody
    public Object draft(@Valid @RequestBody PostEditFrom postEditFrom) {
        UserVO user = HttpUtils.getCurrentUser();
        PostAutoDraft.PostAutoDraftBuilder draftBuilder = PostAutoDraft.builder()
                .userId(user.getId())
                .title(postEditFrom.getTitle())
                .content(postEditFrom.getOriginalContent());

        PostAutoDraft postAutoDraft;
        Long postId = postEditFrom.getPostId();
        if (postId > 0) {
            draftBuilder.postId(postId);
            Post post = postService.getById(postId);
            postAutoDraft = postAutoDraftService.getForPost(post);
        } else {
            postAutoDraft = postAutoDraftService.getForUserId(user.getId());
        }

        if (postAutoDraft != null) {
            draftBuilder.id(postAutoDraft.getId());
            postAutoDraftService.saveOrUpdate(draftBuilder.build());
        } else {
            postAutoDraftService.saveOrUpdate(draftBuilder.build());
        }
        return HttpUtils.success();
    }

    @PostMapping("/edit/{id}")
    @ResponseBody
    public Object edit(@PathVariable("id") Long id, @RequestBody @Validated PostEditFrom postEditFrom) {
        Post post = postMapper.selectById(id);
        postService.edit(post, postEditFrom);
        PostAutoDraft draft = postAutoDraftService.getForPost(post);
        if (draft != null) {
            postAutoDraftService.removeById(draft.getId());
        }
        return HttpUtils.success();
    }

    @GetMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable("id") Long id, ModelAndView view, HttpSession httpSession, HttpServletRequest request, HttpServletResponse response) {
        PostVO post = postService.get(id);
        boolean allowEdit = false;
        UserVO user = null;
        try {
            user = HttpUtils.getCurrentUser();
            if (user != null && user.getId().equals(post.getAuthor().getId())) {
                allowEdit = true;
            }
        } catch (RuntimeException ex) {

        }


        if (!Integer.valueOf(1).equals(post.getStatus()) && (user == null || !user.getId().equals(post.getAuthor().getId()))) {
            //不是作者不能看未发布的文章
            response.setStatus(404);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        view.addObject("post", post);
        view.addObject("user", user);
        Page<PostComment> page = new Page<>();
        page.setSize(50).setCurrent(1);
        IPage<PostCommentVO> comments = postCommentService.getByPostId(page, post.getId());
        view.addObject("comments", comments.getRecords());
        view.addObject("allowEdit", allowEdit);

        HttpUtils.selectViewName("/post/detail", request, view);

        Thread thread = new Thread(() -> {
            postService.viewCountInc(id);
        });
        thread.start();

        return view;
    }

    @PostMapping("/addComment")
    @ResponseBody
    public Object addComment(@RequestBody @Validated PostCommentAddFrom postCommentAddFrom) {
        UserVO user = HttpUtils.getCurrentUser();
        PostComment comment = postCommentService.create(user.getId(), postCommentAddFrom);
        return HttpUtils.success(comment);
    }


    /**
     * 置顶/取消置顶
     *
     * @return
     */
    @PostMapping("/top")
    @ResponseBody
    public Object top(@RequestParam(value = "postId") Long postId, HttpSession httpSession) throws Exception {
        adminCheck();
        Post post = postMapper.selectById(postId);
        postService.top(post);
        return HttpUtils.success(post);
    }

    private void adminCheck() throws Exception {
        UserVO user = HttpUtils.getCurrentUser();
        if (userService.getById(user.getId()).getIsAdmin() != 1) {
            throw new Exception("您不是管理员，不能进行此操作");
        }
    }

    /**
     * 加精
     *
     * @param postId      文章Id
     * @return map response json
     * @throws Exception
     */
    @PostMapping("/essence")
    @ResponseBody
    public Object essence(@RequestParam("postId") Long postId) throws Exception {
        adminCheck();
        Post post = postMapper.selectById(postId);
        postService.essence(post);
        return HttpUtils.success(post);
    }

    @GetMapping("/delete/{id}")
    @ResponseBody
    public Object delete(@PathVariable("id") Long postId) throws Exception {
        adminCheck();
        Post post = postMapper.selectById(postId);
        post.setStatus(PostStatus.DELETE.getStatus());
        postService.move2delete(post);
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
                MultipartFile file = multiRequest.getFile(iter.next());
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
