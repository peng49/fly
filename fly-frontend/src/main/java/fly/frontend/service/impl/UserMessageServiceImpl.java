package fly.frontend.service.impl;

import fly.frontend.entity.model.User;
import fly.frontend.entity.model.UserMessage;
import fly.frontend.dao.UserMessageMapper;
import fly.frontend.service.UserMessageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserMessageServiceImpl implements UserMessageService {
    @Resource
    private UserMessageMapper userMessageMapper;

    @Override
    public int create(UserMessage message) {
        return userMessageMapper.create(message);
    }


    @Override
    public List<UserMessage> getMessagesForUser(User user) {
        return userMessageMapper.getMessagesForUser(user);
    }

    @Override
    public UserMessage get(int id) {
        return userMessageMapper.get(id);
    }

    @Override
    public void delete(UserMessage message) {
        userMessageMapper.delete(message);
    }

    @Override
    public void deleteByUser(User user) {
        userMessageMapper.deleteByUser(user);
    }
}
