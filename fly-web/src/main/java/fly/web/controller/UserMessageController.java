package fly.web.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import fly.web.entity.model.UserMessage;
import fly.web.entity.vo.UserVO;
import fly.web.service.UserMessageService;
import fly.web.utils.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/userMessage")
@Slf4j
public class UserMessageController {
    @Resource
    private UserMessageService userMessageService;

    @GetMapping
    public Object get(@RequestParam(name = "page", defaultValue = "1") int page,
                      @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                      HttpSession httpSession) {
        UserVO user = HttpUtils.getCurrentUser();

        Page<UserMessage> pageObject = new Page<>();
        pageObject.setCurrent(page).setSize(pageSize);

        Page<UserMessage> result = userMessageService.lambdaQuery()
                .eq(UserMessage::getReceiverId, user.getId())
                .page(pageObject);

        log.info(result.getRecords().toString());

        return HttpUtils.success(result);
    }

    @DeleteMapping("/{id}")
    public Object delete(@PathVariable("id") Long messageId, HttpSession httpSession) {
        UserVO user = HttpUtils.getCurrentUser();
        UserMessage message = userMessageService.getById(messageId);
        log.info(message != null ? message.toString() : "null");
        log.info(messageId.toString());
        log.info(user.getId().toString());
        if (message == null || !message.getReceiverId().equals(user.getId())) {
            throw new RuntimeException("不能进行当前操作");
        }
        userMessageService.removeById(message.getId());
        return HttpUtils.success();
    }

    @DeleteMapping("/all")
    public Object delete(HttpSession httpSession) {
        UserVO user = HttpUtils.getCurrentUser();
        userMessageService.lambdaUpdate()
                .eq(UserMessage::getReceiverId, user.getId())
                .remove();
        return HttpUtils.success();
    }
}
