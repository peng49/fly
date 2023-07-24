package fly.admin.service;

import junit.framework.TestCase;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

public class IndexTest extends TestCase {
    public void testReadIndex() throws IOException {
        String currentPath = new java.io.File(".").getCanonicalPath();
        System.out.println("Current dir:" + currentPath);

        String currentDir = System.getProperty("user.dir");
        System.out.println("Current dir using System:" + currentDir);

        URL url = getClass().getResource("/application-prod.yml");
        System.out.println(url.getFile());


        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("application-prod.yml");
        System.out.println(inputStream);

        assert inputStream != null;
        Scanner scanner = new Scanner(inputStream).useDelimiter("\n");
        StringBuilder content = new StringBuilder();
        do{
            content.append("\n").append(scanner.next());
        }while (scanner.hasNext());
        System.out.println(content);




        Resource resource = new ClassPathResource("/application-prod.yml");




//        String content = readFile("test.txt", StandardCharsets.UTF_8);
//        Path path = Paths.get();
//        BufferedReader reader = new BufferedReader(new FileReader(url.getPath()));
//        byte[] encoded = Files.readAllBytes(Paths.get(url.getPath().replaceAll("^/([A-Z]:)","$1")));
//        System.out.println(new String(encoded, StandardCharsets.UTF_8));
    }
}
