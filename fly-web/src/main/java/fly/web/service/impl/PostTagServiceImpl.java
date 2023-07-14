package fly.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fly.web.dao.PostTagMapper;
import fly.web.entity.model.PostTag;
import fly.web.service.PostTagService;
import org.springframework.stereotype.Service;

@Service
public class PostTagServiceImpl extends ServiceImpl<PostTagMapper, PostTag> implements PostTagService {

}
