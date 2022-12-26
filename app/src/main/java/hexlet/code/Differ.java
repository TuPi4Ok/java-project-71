package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Files;
import java.nio.file.Path;
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
        Path file1 = Path.of(filepath1);
        Path file2 = Path.of(filepath2);

        String json1 = Files.readString(file1);
        String json2 = Files.readString(file2);

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> jsonMap1 = objectMapper.readValue(json1, new TypeReference<Map<String, String>>() { });
        Map<String, String> jsonMap2 = objectMapper.readValue(json2, new TypeReference<Map<String, String>>() { });
        return genDiff(jsonMap1, jsonMap2);
    }
}
