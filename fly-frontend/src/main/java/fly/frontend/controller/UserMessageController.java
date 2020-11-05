package fly.frontend.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import fly.frontend.entity.model.UserMessage;
import fly.frontend.entity.vo.UserVO;
import fly.frontend.service.UserMessageService;
import fly.frontend.service.UserService;
import fly.frontend.utils.HttpUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/userMessage")
public class UserMessageController {
    @Resource
    private UserMessageService<UserMessage> userMessageService;

    @GetMapping
    public Object get(@RequestParam(name = "page", defaultValue = "1") int page,
                      @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                      HttpSession httpSession) {
        UserVO user = (UserVO) httpSession.getAttribute(UserService.LOGIN_KEY);

        Page<UserMessage> pageObject = new Page<>();
        pageObject.setCurrent(page).setSize(pageSize);

        Page<UserMessage> result = userMessageService.lambdaQuery()
                .eq(UserMessage::getReceiverId, user.getId())
                .page(pageObject);

        return HttpUtils.success(result);
    }

    @DeleteMapping("/{id}")
    public Object delete(@PathVariable("id") int messageId, HttpSession httpSession) {
        UserVO user = (UserVO) httpSession.getAttribute(UserService.LOGIN_KEY);
        UserMessage message = userMessageService.getById(messageId);
        if (message == null || message.getReceiverId() != user.getId()) {
            throw new RuntimeException("不能进行当前操作");
        }
        userMessageService.removeById(message.getId());
        return HttpUtils.success();
    }

    @DeleteMapping("/all")
    public Object delete(HttpSession httpSession) {
        UserVO user = (UserVO) httpSession.getAttribute(UserService.LOGIN_KEY);
        userMessageService.lambdaUpdate()
                .eq(UserMessage::getReceiverId, user.getId())
                .remove();
        return HttpUtils.success();
    }
}
