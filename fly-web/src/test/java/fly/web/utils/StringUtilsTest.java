package fly.web.utils;


import fly.web.FlyWebApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {FlyWebApplication.class})
public class StringUtilsTest {

    @Test
    public void formatTextForTimestampTest()
    {
        System.out.println(StringUtils.formatTextForTimestamp(new Timestamp(System.currentTimeMillis()-25 *60 * 60 * 1000)));
    }

    @Test
    public void arrayTest(){

    }

    @Test
    public void regexTest()
    {
        String path = "id=3&v=6&name=id";
        System.out.println(path.replaceAll("name=.*?(&|$)","id=7$1"));
    }

    @Test
    public void html2textTest(){
        String html = "<string class='hello'>Hello</string>&nbsp;&nbsp;World!";
        assertEquals(StringUtils.html2text(html),"Hello World!");
    }

    @Test
    public void isMobileTest()
    {
        String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.125 Safari/537.36";
        boolean matches = Pattern.matches(".*(phone|pad|pod|iPhone|iPod|ios|iPad|Android|Mobile|BlackBerry|IEMobile|MQQBrowser|JUC|Fennec|wOSBrowser|BrowserNG|WebOS|Symbian|Windows Phone).*", userAgent);

        System.out.println(matches);
    }


}
