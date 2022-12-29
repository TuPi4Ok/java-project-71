package hexlet.code.formatters;

import java.util.Map;

public class Formatter {
    public static String get(Map<String, Object> map1,
                             Map<String, Object> map2, Map<String, String> diff, String format) {
        if (format.equalsIgnoreCase("plain")) {
            return Plain.get(map1, map2, diff);
        }
        return Stylish.get(map1, map2, diff);
    }
}
