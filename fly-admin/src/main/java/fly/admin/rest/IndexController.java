package fly.admin.rest;

import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

@RestController
@RequestMapping("/")
public class IndexController {
    @GetMapping("")
    public String index() throws IOException {
//        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("/vue/dist/index.html");

        ClassPathResource resource = new ClassPathResource("/vue/dist/index.html");
        InputStream inputStream = resource.getInputStream();

        Scanner scanner = new Scanner(inputStream).useDelimiter("\n");
        StringBuilder content = new StringBuilder();
        do {
            content.append("\n").append(scanner.next());
        } while (scanner.hasNext());
        return String.valueOf(content);
    }
}
