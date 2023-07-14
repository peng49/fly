package fly.admin.service;

import junit.framework.TestCase;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class IndexTest extends TestCase {
    public void testReadIndex() throws IOException {
        String currentPath = new java.io.File(".").getCanonicalPath();
        System.out.println("Current dir:" + currentPath);

        String currentDir = System.getProperty("user.dir");
        System.out.println("Current dir using System:" + currentDir);

        URL url = getClass().getResource("/vue/dist/index.html");
        System.out.println(url.getPath().replaceAll("^/([A-Z]:)","$1"));



//        String content = readFile("test.txt", StandardCharsets.UTF_8);
//        Path path = Paths.get();
//        BufferedReader reader = new BufferedReader(new FileReader(url.getPath()));
        byte[] encoded = Files.readAllBytes(Paths.get(url.getPath().replaceAll("^/([A-Z]:)","$1")));
        System.out.println(new String(encoded, StandardCharsets.UTF_8));
    }
}
