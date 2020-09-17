package fly.frontend.mapper;

import fly.frontend.entity.model.FriendLink;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FriendLinkMapper {
    @Select("select * from friend_links where status = 1")
    public List<FriendLink> getAll();
}
