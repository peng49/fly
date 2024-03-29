package fly.web.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import fly.web.FlyWebApplication;
import fly.web.entity.model.UserMessage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {FlyWebApplication.class})
public class UserMessageServiceTest {
    @Resource
    private UserMessageService userMessageService;

    @Test
    public void searchTest()
    {
        Page<UserMessage> page = new Page<>();
        page.setCurrent(1).setSize(5);
        Page<UserMessage> res = userMessageService.lambdaQuery().eq(UserMessage::getReceiverId, 1).page(page);

        Page<UserMessage> result = userMessageService.page(page, Wrappers.lambdaQuery(UserMessage.class).eq(UserMessage::getReceiverId,1));

        System.out.println(result.getCurrent());
        System.out.println(result.getTotal());
        System.out.println(res.getRecords());
    }

}
