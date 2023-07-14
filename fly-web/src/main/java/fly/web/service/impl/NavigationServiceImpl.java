package fly.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fly.web.dao.NavigationMapper;
import fly.web.entity.model.Navigation;
import fly.web.service.NavigationService;
import org.springframework.stereotype.Service;

@Service
public class NavigationServiceImpl extends ServiceImpl<NavigationMapper, Navigation> implements NavigationService {

}
