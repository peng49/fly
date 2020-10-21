package fly.admin.service.impl;

import fly.admin.entity.model.OauthAccount;
import fly.admin.entity.vo.ListResultVO;
import fly.admin.entity.vo.ResultVO;
import fly.admin.repository.OauthAccountRepository;
import fly.admin.service.OauthAccountService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service
public class OauthAccountServiceImpl implements OauthAccountService {

    @Resource
    private OauthAccountRepository oauthAccountRepository;

    @Override
    public ResultVO search(int page, int pageSize, Map<String, Object> query) {
        Page<OauthAccount> accounts = oauthAccountRepository.findAll(PageRequest.of(page - 1, pageSize));

        return ResultVO.builder()
                .code("success")
                .message("Success")
                .data(ListResultVO.builder()
                        .page(page)
                        .pageSize(pageSize)
                        .total(accounts.getTotalElements())
                        .items(accounts.getContent())
                        .build())
                .build();
    }
}
