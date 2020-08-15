package fly.frontend.controller.Mobile;

import fly.frontend.entity.Post;
import fly.frontend.entity.PostComment;
import fly.frontend.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller("Mobile.PostController")
@RequestMapping("/m/p")
public class PostController {

    @Autowired
    private PostService postService;

    @RequestMapping("/{id}")
    public ModelAndView detail(@PathVariable("id") int id, ModelAndView view) {
        System.out.println("loading detail function");
        Post post = postService.get(id);
        view.addObject("post",post);

        List<PostComment> comments = postService.getComments(post.getId());
        view.addObject("comments",comments);

        view.setViewName("m/post/detail");
        return view;
    }
}
