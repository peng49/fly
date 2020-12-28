package fly.admin.service.impl;

import fly.admin.entity.model.SystemConfig;
import fly.admin.entity.vo.ListResultVO;
import fly.admin.entity.vo.ResultVO;
import fly.admin.repository.SystemConfigRepository;
import fly.admin.service.SystemConfigService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SystemConfigServiceImpl implements SystemConfigService {
    @Resource
    private SystemConfigRepository systemConfigRepository;

    @Override
    public SystemConfig add(SystemConfig config) {
        return systemConfigRepository.save(config);
    }

    @Override
    public void delete(SystemConfig config) {
        systemConfigRepository.delete(config);
    }

    @Override
    public SystemConfig update(SystemConfig config) {
        return systemConfigRepository.save(config);
    }

    @Override
    public SystemConfig get(int id) {
        return systemConfigRepository.getOne(id);
    }

    @Override
    public ResultVO search(int page, int pageSize, Map<String, Object> query) {
        //构造查询条件
        Specification<SystemConfig> specification = (Specification<SystemConfig>) (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (query.get("keyword") != null) {
                Path<String> path = root.get("attribute");
                predicates.add(criteriaBuilder.like(path, "%" + query.get("keyword") + "%"));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

        Page<SystemConfig> columns = systemConfigRepository.findAll(specification, PageRequest.of(page - 1, pageSize));
        return ResultVO.builder()
                .code("success")
                .message("Success")
                .data(
                        ListResultVO.builder()
                                .items(columns.getContent())
                                .page(page)
                                .pageSize(pageSize)
                                .total(columns.getTotalElements())
                                .build()
                )
                .build();
    }
}
