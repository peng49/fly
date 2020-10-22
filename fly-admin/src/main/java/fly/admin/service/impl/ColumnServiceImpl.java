package fly.admin.service.impl;

import fly.admin.entity.model.Column;
import fly.admin.entity.model.User;
import fly.admin.entity.vo.ListResultVO;
import fly.admin.entity.vo.ResultVO;
import fly.admin.repository.ColumnRepository;
import fly.admin.service.ColumnService;
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
public class ColumnServiceImpl implements ColumnService {

    @Resource
    private ColumnRepository columnRepository;

    @Override
    public Column add(Column column) {
        return columnRepository.save(column);
    }

    @Override
    public void delete(Column column) {
        columnRepository.delete(column);
    }

    @Override
    public Column update(Column column) {
        return columnRepository.save(column);
    }

    @Override
    public Column get(int id) {
        return columnRepository.getOne(id);
    }

    @Override
    public ResultVO search(int page, int pageSize, Map<String, Object> query) {
        //构造查询条件
        Specification<Column> specification = (Specification<Column>) (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (query.get("keyword") != null) {
                Path<String> path = root.get("name");
                predicates.add(criteriaBuilder.like(path, "%" + query.get("keyword") + "%"));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

        Page<Column> columns = columnRepository.findAll(specification, PageRequest.of(page - 1, pageSize));
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
