package fly.frontend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fly.frontend.entity.model.Column;
import fly.frontend.dao.ColumnMapper;
import fly.frontend.service.ColumnService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ColumnServiceImpl extends ServiceImpl<ColumnMapper,Column> implements ColumnService {

}
