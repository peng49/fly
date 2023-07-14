package fly.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fly.web.dao.ColumnMapper;
import fly.web.entity.model.Column;
import fly.web.service.ColumnService;
import org.springframework.stereotype.Service;

@Service
public class ColumnServiceImpl extends ServiceImpl<ColumnMapper,Column> implements ColumnService {

}
