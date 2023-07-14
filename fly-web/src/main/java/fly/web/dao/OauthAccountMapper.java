package fly.web.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import fly.web.entity.model.OauthAccount;
import org.apache.ibatis.annotations.*;

@Mapper
public interface OauthAccountMapper extends BaseMapper<OauthAccount> {

}
