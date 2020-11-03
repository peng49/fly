package fly.frontend.controller;

import fly.frontend.entity.model.User;
import fly.frontend.entity.model.UserMessage;
import fly.frontend.service.UserMessageService;
import fly.frontend.service.UserService;
import fly.frontend.utils.HttpUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/userMessage")
public class UserMessageController {
    @Resource
    private UserMessageService userMessageService;

    @GetMapping
    public Object get(@RequestParam(name = "page",defaultValue = "1") int page,
                      @RequestParam(name="pageSize",defaultValue = "10") int pageSize,
                      HttpSession httpSession) {
        User user = (User) httpSession.getAttribute(UserService.LOGIN_KEY);


        List<UserMessage> messages = userMessageService.getMessagesForUser(user);

        return HttpUtils.success(messages);
    }

    @DeleteMapping("/{id}")
    public Object delete(@PathVariable("id") int messageId, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute(UserService.LOGIN_KEY);
        UserMessage message = userMessageService.get(messageId);
        if (message == null || message.getReceiver().getId() != user.getId()) {
            throw new RuntimeException("不能进行当前操作");
        }

        userMessageService.delete(message);
        return HttpUtils.success();
    }

    @DeleteMapping("/all")
    public Object delete(HttpSession httpSession) {
        User user = (User) httpSession.getAttribute(UserService.LOGIN_KEY);
        userMessageService.deleteByUser(user);
        return HttpUtils.success();
    }
}
