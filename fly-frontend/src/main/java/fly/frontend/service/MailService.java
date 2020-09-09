package fly.frontend.service;

import java.util.List;

public interface MailService {
    boolean send(String subject, String content, List<String> emails);
}
