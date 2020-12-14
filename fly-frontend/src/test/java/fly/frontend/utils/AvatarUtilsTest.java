package fly.frontend.utils;

import fly.frontend.FlyFrontendApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {FlyFrontendApplication.class})
public class AvatarUtilsTest {

    @Value("${user.avatar-dir}")
    private String userDir;

    @Test
    public void generate() throws IOException {
        int width = 420;
        int height = 420;

        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D ig2 = bi.createGraphics();

        ig2.setColor(Color.WHITE);
        ig2.fillRect(0, 0, width, height);

        ig2.setColor(Color.BLACK);
        ig2.fillRect(10,10,20,20);






        String filename = userDir+"/"+UUID.randomUUID() + ".png";
        File file = new File(filename);
        ImageIO.write(bi,"PNG",file);
    }
}
