package fly.frontend.dao;

import fly.frontend.entity.model.OauthAccount;
import org.apache.ibatis.annotations.*;

@Mapper
public interface OauthAccountMapper {
    @Select("select oa.*,u.username,u.avatar from oauth_account oa inner join users as u on u.id = oa.user_id where oa.openid = #{openid} and oa.platform = #{platform}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "openid", column = "openid"),
            @Result(property = "platform", column = "platform"),
            @Result(property = "user.id", column = "user_id"),
            @Result(property = "user.username", column = "username"),
            @Result(property = "user.avatar", column = "avatar")
    })
    OauthAccount get(String openid,String platform);

    @Insert("insert into oauth_account(user_id,openid,platform,created_at,updated_at) values(#{user.id},#{openid},#{platform},now(),now())")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int add(OauthAccount account);
}
