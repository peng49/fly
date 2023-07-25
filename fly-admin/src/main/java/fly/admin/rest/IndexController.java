package fly.admin.rest;

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
        //读取jar中的内容失败
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("/vue/dist/index.html");
        if (inputStream == null) {
            inputStream = getClass().getClassLoader().getResourceAsStream("vue/dist/index.html");
        }
        assert inputStream != null;
        Scanner scanner = new Scanner(inputStream).useDelimiter("\n");
        StringBuilder content = new StringBuilder();
        do {
            content.append("\n").append(scanner.next());
        } while (scanner.hasNext());
        return String.valueOf(content);
    }
}
