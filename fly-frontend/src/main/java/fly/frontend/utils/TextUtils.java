package fly.frontend.utils;

import java.sql.Timestamp;

public class TextUtils {
    public static String formatTextForTimestamp(Timestamp timestamp) {
        long time = System.currentTimeMillis() - timestamp.getTime();
        if (time < 3 * 60 * 1000) {//3分钟之内
            return "刚刚";
        }
        if (time < 15 * 60 * 1000) {
            int minute = (int) Math.ceil(time / 1000.0 / 60.0);
            return minute + "分钟内";
        }

        if (time <= 30 * 60 * 1000) {
            return "半小时内";
        }
        System.out.println(time / 1000.0 / 60.0 / 60.0);
        if (time <= 24 * 60 * 60 * 1000) {
            int hour = (int) Math.ceil(time / 1000.0 / 60.0 / 60.0);
            return hour + "小时内";
        }
        return timestamp.toString();
    }

    public static String renderPageTpl(int total, int currentPage, int pageSize) {
        return "";
    }
}
