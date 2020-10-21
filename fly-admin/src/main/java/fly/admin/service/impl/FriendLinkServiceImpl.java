package fly.admin.service.impl;

import fly.admin.entity.model.FriendLink;
import fly.admin.entity.vo.ListResultVO;
import fly.admin.entity.vo.ResultVO;
import fly.admin.repository.FriendLinkRepository;
import fly.admin.service.FriendLinkService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Map;

@Service
public class FriendLinkServiceImpl implements FriendLinkService {

    @Resource
    private FriendLinkRepository friendLinkRepository;

    @Override
    public FriendLink add(FriendLink link) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        link.setCreatedAt(timestamp);
        link.setUpdatedAt(timestamp);
        return friendLinkRepository.save(link);
    }

    @Override
    public void delete(FriendLink link) {
        friendLinkRepository.delete(link);
    }

    @Override
    public FriendLink update(FriendLink link) {
        link.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        return friendLinkRepository.save(link);
    }

    @Override
    public FriendLink get(int id) {
        return friendLinkRepository.getOne(id);
    }

    @Override
    public ResultVO search(int page, int pageSize, Map<String, Object> query) {
        Page<FriendLink> links = friendLinkRepository.findAll(PageRequest.of(page - 1, pageSize));

        return ResultVO.builder()
                .code("success")
                .message("Success")
                .data(ListResultVO.builder()
                        .items(links.getContent())
                        .page(page)
                        .pageSize(links.getSize())
                        .total(links.getTotalElements())
                        .build()
                ).build();
    }
}
