package fly.frontend.utils;

import org.junit.Test;

import java.sql.Timestamp;

public class TextUtilsTest {

    @Test
    public void formatTextForTimestampTest()
    {
        System.out.println(TextUtils.formatTextForTimestamp(new Timestamp(System.currentTimeMillis()-25 *60 * 60 * 1000)));
    }
}
