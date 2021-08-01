package fly.admin.service.impl;

import fly.admin.entity.model.Navigation;
import fly.admin.entity.vo.ListResultVO;
import fly.admin.entity.vo.ResultVO;
import fly.admin.repository.NavigationRepository;
import fly.admin.service.NavigationService;
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
public class NavigationServiceImpl implements NavigationService {
    @Resource
    private NavigationRepository navigationRepository;

    @Override
    public Navigation add(Navigation navigation) {
        return navigationRepository.save(navigation);
    }

    @Override
    public void delete(Navigation navigation) {
        navigationRepository.delete(navigation);
    }

    @Override
    public Navigation update(Navigation navigation) {
        return navigationRepository.save(navigation);
    }

    @Override
    public Navigation get(int id) {
        return navigationRepository.getOne(id);
    }

    @Override
    public ResultVO search(int page, int pageSize, Map<String, Object> query) {
        //构造查询条件
        Specification<Navigation> specification = (Specification<Navigation>) (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (query.get("keyword") != null) {
                Path<String> path = root.get("title");
                predicates.add(criteriaBuilder.like(path, "%" + query.get("keyword") + "%"));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

        Page<Navigation> navigations = navigationRepository.findAll(specification, PageRequest.of(page - 1, pageSize));
        return ResultVO.builder()
                .code("success")
                .message("Success")
                .data(
                        ListResultVO.builder()
                                .items(navigations.getContent())
                                .page(page)
                                .pageSize(pageSize)
                                .total(navigations.getTotalElements())
                                .build()
                )
                .build();
    }
}
