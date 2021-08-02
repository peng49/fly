package fly.frontend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fly.frontend.dao.PostTagMapper;
import fly.frontend.entity.model.PostTag;
import fly.frontend.service.PostTagService;
import org.springframework.stereotype.Service;

@Service
public class PostTagServiceImpl extends ServiceImpl<PostTagMapper, PostTag> implements PostTagService {

}
