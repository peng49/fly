package fly.web.service.impl;

import fly.web.service.MailService;

import java.util.List;

public class MailServiceImpl implements MailService {
    @Override
    public boolean send(String subject, String content, List<String> emails) {
        return false;
    }
}
