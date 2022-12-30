package hexlet.code.formatters;

import java.util.Map;

public class Json {
    public static String get(Map<String, String> diff) {
        String result = "";
        for (Map.Entry<String, String> itemDiff : diff.entrySet()) {
            result += "\"" + itemDiff.getKey() + "\":\"" + itemDiff.getValue() + "\",";
        }
        return "{" + result.substring(0, result.length() - 1) + "}";
    }
}
