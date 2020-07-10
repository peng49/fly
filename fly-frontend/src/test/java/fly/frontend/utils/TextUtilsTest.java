package fly.frontend.utils;

import org.junit.Test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

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


}
