package fly.frontend.utils;

import org.junit.Test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.regex.Pattern;

public class TextUtilsTest {

    @Test
    public void formatTextForTimestampTest()
    {
        System.out.println(TextUtils.formatTextForTimestamp(new Timestamp(System.currentTimeMillis()-25 *60 * 60 * 1000)));
    }

    @Test
    public void arrayTest(){
        Integer[] array = {1,2,2,2,2,2,2,3};

//        System.out.println(array);
        //去重
        ArrayList<Integer> ids = new ArrayList<Integer>(new HashSet<Integer>(Arrays.asList(array)));
        System.out.println(ids);
    }

    @Test
    public void regexTest()
    {
        String path = "id=3&v=6&name=id";
        System.out.println(path.replaceAll("name=.*?(&|$)","id=7$1"));
    }

    @Test
    public void isMobileTest()
    {
        String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.125 Safari/537.36";
        boolean matches = Pattern.matches(".*(phone|pad|pod|iPhone|iPod|ios|iPad|Android|Mobile|BlackBerry|IEMobile|MQQBrowser|JUC|Fennec|wOSBrowser|BrowserNG|WebOS|Symbian|Windows Phone).*", userAgent);

        System.out.println(matches);
    }


}
