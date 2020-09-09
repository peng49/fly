package fly.frontend.service.impl;

import fly.frontend.service.MailService;

import java.util.List;

public class MailServiceImpl implements MailService {
    @Override
    public boolean send(String subject, String content, List<String> emails) {
        return false;
    }
}
