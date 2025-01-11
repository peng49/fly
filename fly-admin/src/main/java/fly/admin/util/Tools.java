package fly.admin.util;

public class Tools {
    public static String getCode(Object ex) {
        return ex.getClass()
                .getSimpleName()
                .replaceAll("[A-Z]", ".$0")
                .replaceAll("^.", "")
                .toLowerCase();
    }
}
