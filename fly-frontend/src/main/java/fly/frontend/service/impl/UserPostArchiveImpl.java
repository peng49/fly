package fly.frontend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fly.frontend.dao.UserPostArchiveMapper;
import fly.frontend.entity.model.UserPostArchive;
import fly.frontend.service.UserPostArchiveService;
import org.springframework.stereotype.Service;

@Service
public class UserPostArchiveImpl extends ServiceImpl<UserPostArchiveMapper,UserPostArchive> implements UserPostArchiveService {
}
