package fly.frontend.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import fly.frontend.entity.from.PostFilterCondition;
import fly.frontend.entity.vo.PostVO;
import fly.frontend.service.PostCommentService;
import fly.frontend.service.PostService;
import fly.frontend.service.UserService;
import fly.frontend.utils.HttpUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
public class HomepageController {
    @Resource
    private PostService postService;

    @Resource
    private UserService userService;

    @Resource
    private PostCommentService postCommentService;

    @RequestMapping("/")
    public ModelAndView index(HttpServletRequest request,
                              ModelAndView view,
                              @RequestParam(value = "list", defaultValue = "all") String list,
                              @RequestParam(value = "orderBy", defaultValue = "") String orderBy,
                              @RequestParam(value = "page",defaultValue = "1") Integer page,
                              @RequestParam(value = "pageSize",defaultValue = "20") Integer pageSize) {
        PostFilterCondition condition = new PostFilterCondition();
        condition.setList(list);
        condition.setStatus(PostService.PUBLISH_STATUS);//已发布的

        if (!"".equals(orderBy) && PostService.ALLOW_ORDER_FIELD.contains(orderBy)) {
            condition.setOrderBy(orderBy);
        } else {
            condition.setOrderBy("heat");
        }

        IPage<PostVO> posts = postService.getByCondition(new Page<>(page, pageSize), condition);

        view.addObject("posts", posts.getRecords());

        view.addObject("listTotal", posts.getTotal());
        view.addObject("currentPage", posts.getCurrent());
        view.addObject("pageSize", posts.getSize() > 0 ? posts.getSize() : 10);

        HttpUtils.selectViewName("index", request, view);

        return view;
    }


    @GetMapping("/u/{id}")
    public ModelAndView index(@PathVariable("id") Long id, ModelAndView view, HttpServletRequest request) {
        view.addObject("user", userService.get(id));

        view.addObject("posts", postService.findPublishByAuthorId(id,new Page<>(1, 10)).getRecords());

        view.addObject("comments", postCommentService.getByUserId(new Page<>(1, 5), id).getRecords());

        HttpUtils.selectViewName("user/home", request, view);

        return view;
    }
}
