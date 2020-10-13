package fly.admin.rest.auth;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/api/auth/users")
public class UserController {

    @GetMapping("/{id}")
    public Object get(@PathVariable("id") int id) {
        HashMap<Object, Object> map = new HashMap<>();
        map.put("id", id);
        return map;
    }
}
