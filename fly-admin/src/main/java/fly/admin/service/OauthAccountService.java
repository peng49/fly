package fly.admin.service;

import fly.admin.entity.vo.ResultVO;

import java.util.Map;

public interface OauthAccountService {
    ResultVO search(int page, int pageSize, Map<String,Object> query);
}
