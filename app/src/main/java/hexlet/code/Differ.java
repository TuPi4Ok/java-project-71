package hexlet.code;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Comparator;


public class Differ {
    private static String genDiff(Map<String, String> map1, Map<String, String> map2) {
        String result = "";
        for (Map.Entry<String, String> item1 : map1.entrySet()) {
            if (!map2.containsKey(item1.getKey())) {
                result += "  - " + item1.getKey() + ": " + item1.getValue() + "\n";
            }
            if (map2.containsKey(item1.getKey()) && !item1.getValue().equals(map2.get(item1.getKey()))) {
                result += "  - " + item1.getKey() + ": " + item1.getValue() + "\n";
                result += "  + " + item1.getKey() + ": " + map2.get(item1.getKey()) + "\n";
            }
            if (map2.containsKey(item1.getKey()) && item1.getValue().equals(map2.get(item1.getKey()))) {
                result += "    " + item1.getKey() + ": " + item1.getValue() + "\n";
            }
        }
        for (Map.Entry<String, String> item2 : map2.entrySet()) {
            if (!map1.containsKey(item2.getKey())) {
                result += "  + " + item2.getKey() + ": " + item2.getValue() + "\n";
            }
        }
        List<String> resultList = new ArrayList<>();
        resultList.addAll(Arrays.asList(result.split("\n")));

        resultList = resultList.stream()
                .sorted(Comparator.comparing(x -> x.charAt(4)))
                .toList();
        result = String.join("\n", resultList);
        return "{\n" + result + "\n}";
    }
    public static String generate(String filepath1, String filepath2) throws Exception {
        List<Map<String, String>> maps = new ArrayList<>();
        String extension = filepath1.split("/")[filepath1.split("/").length - 1].split("\\.")[1];
        if (extension.equals("json")) {
            maps = Parser.parsJson(filepath1, filepath2);
        }
        if (extension.equals("yaml")) {
            maps = Parser.parsYaml(filepath1, filepath2);
        }

        return genDiff(maps.get(0), maps.get(1));
    }
}
