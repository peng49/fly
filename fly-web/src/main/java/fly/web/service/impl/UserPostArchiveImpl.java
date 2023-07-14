package fly.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fly.web.dao.UserPostArchiveMapper;
import fly.web.entity.model.UserPostArchive;
import fly.web.service.UserPostArchiveService;
import org.springframework.stereotype.Service;

@Service
public class UserPostArchiveImpl extends ServiceImpl<UserPostArchiveMapper,UserPostArchive> implements UserPostArchiveService {
}
