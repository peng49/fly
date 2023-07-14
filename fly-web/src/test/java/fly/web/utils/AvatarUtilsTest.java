package fly.web.utils;

import fly.web.FlyWebApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {FlyWebApplication.class})
public class AvatarUtilsTest {

    @Value("${user.avatar-dir}")
    private String userDir;

    @Test
    public void generate() throws IOException {
        for (int i = 0; i < 20; i++) {
            BufferedImage bi = new AvatarUtils().getARandomAvatar();
            String filename = userDir + "/" + UUID.randomUUID() + ".png";
            File file = new File(filename);
            ImageIO.write(bi, "PNG", file);
        }
    }
}
