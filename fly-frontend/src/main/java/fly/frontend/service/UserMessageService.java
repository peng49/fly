package fly.frontend.service;

import fly.frontend.entity.model.User;
import fly.frontend.entity.model.UserMessage;

import java.util.List;

public interface UserMessageService {
    int create(UserMessage message);

    List<UserMessage> getMessagesForUser(User user);

    UserMessage get(int id);

    void delete(UserMessage message);

    void deleteByUser(User user);
}
