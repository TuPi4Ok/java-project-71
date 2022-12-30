package hexlet.code.formatters;

import java.util.Map;

public class Stylish {
    public static String get(Map<String, Object> map1, Map<String, Object> map2, Map<String, String> diff) {
        String result = "";
        for (Map.Entry<String, String> itemDiff : diff.entrySet()) {
            if (itemDiff.getValue().equals("added")) {
                result += "  + " + itemDiff.getKey() + ": " + map2.get(itemDiff.getKey()) + "\n";
            }
            if (itemDiff.getValue().equals("deleted")) {
                result += "  - " + itemDiff.getKey() + ": " + map1.get(itemDiff.getKey()) + "\n";
            }
            if (itemDiff.getValue().equals("changed")) {
                result += "  - " + itemDiff.getKey() + ": " + map1.get(itemDiff.getKey()) + "\n";
                result += "  + " + itemDiff.getKey() + ": " + map2.get(itemDiff.getKey()) + "\n";
            }
            if (itemDiff.getValue().equals("unchanged")) {
                result += "    " + itemDiff.getKey() + ": " + map1.get(itemDiff.getKey()) + "\n";
            }
        }
        return "{\n" + result + "}";
    }
}
