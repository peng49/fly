package fly.frontend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fly.frontend.dao.UserMessageMapper;
import fly.frontend.entity.model.UserMessage;
import fly.frontend.service.UserMessageService;
import org.springframework.stereotype.Service;

@Service
public class UserMessageServiceImpl extends ServiceImpl<UserMessageMapper, UserMessage> implements UserMessageService<UserMessage> {

}
