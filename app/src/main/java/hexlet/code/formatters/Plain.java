package hexlet.code.formatters;

import java.util.Map;


public class Plain {
    public static void fixComplexValue(Map<String, Object> map) {
        for (Map.Entry<String, Object> item : map.entrySet()) {
            if (item.getValue() instanceof String && !item.getValue().equals("null")) {
                map.put(item.getKey(), "'" + item.getValue() + "'");
            }
            if (!(item.getValue() instanceof Integer) && !(item.getValue() instanceof Boolean)
                    && !(item.getValue() instanceof String)) {
                map.put(item.getKey(), "[complex value]");
            }
        }
    }
    public static String get(Map<String, Object> map1, Map<String, Object> map2, Map<String, String> diff) {
        String result = "";
        fixComplexValue(map1);
        fixComplexValue(map2);
        for (Map.Entry<String, String> itemDiff : diff.entrySet()) {
            if (itemDiff.getValue().equals("added")) {
                result += "Property '" + itemDiff.getKey() + "' was added with value: "
                        + map2.get(itemDiff.getKey()) + "\n";
            }
            if (itemDiff.getValue().equals("deleted")) {
                result += "Property '" + itemDiff.getKey() + "' was removed\n";
            }
            if (itemDiff.getValue().equals("changed")) {
                result += "Property '" + itemDiff.getKey() + "' was updated. From " + map1.get(itemDiff.getKey())
                        + " to " + map2.get(itemDiff.getKey()) + "\n";
            }
        }
        return result.substring(0, result.length() - 1);
    }
}
