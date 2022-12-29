package hexlet.code;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Differ {
    public static String stylish(Map<String, Object> map1, Map<String, Object> map2, Map<String, String> diff) {
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
    private static void fixNull(Map<String, Object> map) {
        for (Map.Entry<String, Object> item1 : map.entrySet()) {
            if (item1.getValue() == null) {
                item1.setValue("null");
            }
        }
    }
    private static Map<String, String> genDiff(Map<String, Object> map1, Map<String, Object> map2) {
        fixNull(map1);
        fixNull(map2);
        Map<String, String> result = new TreeMap<>();
        for (Map.Entry<String, Object> item1 : map1.entrySet()) {
            if (!map2.containsKey(item1.getKey())) {
                result.put(item1.getKey(), "deleted");
            }
            if (map2.containsKey(item1.getKey()) && !item1.getValue().equals(map2.get(item1.getKey()))) {
                result.put(item1.getKey(), "changed");
            }
            if (map2.containsKey(item1.getKey()) && item1.getValue().equals(map2.get(item1.getKey()))) {
                result.put(item1.getKey(), "unchanged");
            }
        }
        for (Map.Entry<String, Object> item2 : map2.entrySet()) {
            if (!map1.containsKey(item2.getKey())) {
                result.put(item2.getKey(), "added");
            }
            if (map1.containsKey(item2.getKey()) && !item2.getValue().equals(map1.get(item2.getKey()))) {
                result.put(item2.getKey(), "changed");
            }
            if (map1.containsKey(item2.getKey()) && item2.getValue().equals(map1.get(item2.getKey()))) {
                result.put(item2.getKey(), "unchanged");
            }
        }
        return result;
    }
    public static String generate(String filepath1, String filepath2) throws Exception {
        List<Map<String, Object>> maps = new ArrayList<>();
        String extension = filepath1.split("/")[filepath1.split("/").length - 1].split("\\.")[1];
        if (extension.equals("json")) {
            maps = Parser.parsJson(filepath1, filepath2);
        }
        if (extension.equals("yml")) {
            maps = Parser.parsYaml(filepath1, filepath2);
        }

        return stylish(maps.get(0), maps.get(1), genDiff(maps.get(0), maps.get(1)));
    }
}
