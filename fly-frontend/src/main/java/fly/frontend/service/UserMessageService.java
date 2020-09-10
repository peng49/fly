package fly.frontend.service;

import fly.frontend.entity.User;
import fly.frontend.entity.UserMessage;

import java.util.List;

public interface UserMessageService {
    int create(UserMessage message);

    List<UserMessage> getMessagesForUser(User user);

    UserMessage get(int id);

    void delete(UserMessage message);

    void deleteByUser(User user);
}
