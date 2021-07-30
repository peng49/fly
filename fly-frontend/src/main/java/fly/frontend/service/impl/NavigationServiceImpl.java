package fly.frontend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fly.frontend.dao.NavigationMapper;
import fly.frontend.entity.model.Navigation;
import fly.frontend.service.NavigationService;
import org.springframework.stereotype.Service;

@Service
public class NavigationServiceImpl extends ServiceImpl<NavigationMapper, Navigation> implements NavigationService {

}
