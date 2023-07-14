package fly.admin.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
@RequestMapping("/")
public class IndexController {
    @GetMapping("")
    public String index() throws IOException {
        URL url = getClass().getResource("/vue/dist/index.html");
        assert url != null;
        byte[] encoded = Files.readAllBytes(Paths.get(url.getPath().replaceAll("^/([A-Z]:)", "$1")));
        return new String(encoded, StandardCharsets.UTF_8);
    }
}
