package fly.web.rest;

import fly.web.entity.vo.ResultVO;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "RestUserController")
public class UserController {
    /**
     * 登录
     *
     * @return
     */
    public ResultVO login(){
        return null;
    }


    /**
     * 注册
     *
     * @return
     */
    public ResultVO register(){
        return null;
    }

    /**
     * 我的文章
     * @return
     */
    public ResultVO posts() {
        return null;
    }

    /**
     * 我的收藏
     * @return
     */
    public ResultVO collection() {
        return null;
    }
}
