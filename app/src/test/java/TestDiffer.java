import hexlet.code.Differ;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestDiffer {
    @Test
    public void testFlatJson() throws Exception {
        String expected = "{\n" +
                "  - follow: false\n" +
                "    host: hexlet.io\n" +
                "  - proxy: 123.234.53.22\n" +
                "  - timeout: 50\n" +
                "  + timeout: 20\n" +
                "  + verbose: true\n" +
                "}";
        assertThat(Differ
                .generate("/home/ivan/Project-for-Hexlet/java-project-71/app/src/test/resources/file1",
                          "/home/ivan/Project-for-Hexlet/java-project-71/app/file2.json"))
                .isEqualTo(expected);
    }
}
