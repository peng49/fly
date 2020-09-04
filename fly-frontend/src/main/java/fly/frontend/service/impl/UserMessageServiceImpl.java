package fly.frontend.service.impl;

import fly.frontend.entity.UserMessage;
import fly.frontend.mapper.UserMessageMapper;
import fly.frontend.service.UserMessageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserMessageServiceImpl implements UserMessageService {
    @Resource
    private UserMessageMapper userMessageMapper;

    @Override
    public int create(UserMessage message) {
        return userMessageMapper.create(message);
    }
}
