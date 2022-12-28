package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Parser {
    public static List<Map<String, String>> parsJson(String filepath1, String filepath2) throws IOException {
        Path file1 = Path.of(filepath1);
        Path file2 = Path.of(filepath2);

        String json1 = Files.readString(file1);
        String json2 = Files.readString(file2);

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> jsonMap1 = objectMapper.readValue(json1, new TypeReference<Map<String, String>>() { });
        Map<String, String> jsonMap2 = objectMapper.readValue(json2, new TypeReference<Map<String, String>>() { });
        List<Map<String, String>> maps = new ArrayList<>();
        maps.add(jsonMap1);
        maps.add(jsonMap2);
        return maps;
    }

    public static List<Map<String, String>> parsYaml(String filepath1, String filepath2) throws IOException {
        Path file1 = Path.of(filepath1);
        Path file2 = Path.of(filepath2);

        String yaml1 = Files.readString(file1);
        String yaml2 = Files.readString(file2);

        ObjectMapper objectMapper = new YAMLMapper();
        Map<String, String> jsonMap1 = objectMapper.readValue(yaml1, new TypeReference<Map<String, String>>() { });
        Map<String, String> jsonMap2 = objectMapper.readValue(yaml2, new TypeReference<Map<String, String>>() { });
        List<Map<String, String>> maps = new ArrayList<>();
        maps.add(jsonMap1);
        maps.add(jsonMap2);
        return maps;
    }
}
