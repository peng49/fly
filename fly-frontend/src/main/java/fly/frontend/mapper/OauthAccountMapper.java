package fly.frontend.mapper;

import fly.frontend.entity.OauthAccount;
import org.apache.ibatis.annotations.*;

@Mapper
public interface OauthAccountMapper {
    @Select("select oa.*,u.username,u.avatar from oauth_account oa inner join users as u on u.id = oa.user_id where oa.open_id = #{openid}")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "openid",column = "openid"),
            @Result(property = "user.id",column = "user_id"),
            @Result(property = "user.username",column = "username"),
            @Result(property = "user.avatar",column = "avatar")
    })
    OauthAccount get(String openid);

    @Insert("insert into oauth_account(user_id,openid,platform,created_at) values(#{user.id},#{openid},#{platform},now())")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    OauthAccount add(OauthAccount account);
}
