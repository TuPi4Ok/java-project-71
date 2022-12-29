package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
@Command(
        name = "gendiff",
        version = "gendiff 2.28",
        mixinStandardHelpOptions = true
)
public class App implements Runnable {
    @Parameters(index = "0", description = "path to first file")
    private String filepath1;
    @Parameters(index = "1", description = "path to second file")
    private String filepath2;
    @Override
    public void run() {
        try {
            String dir = System.getProperty("user.dir");
            if (filepath1.charAt(0) != '/') {
                filepath1 = dir + "/" + filepath1;
            }
            if (filepath2.charAt(0) != '/') {
                filepath2 = dir + "/" + filepath2;
            }

            System.out.println(Differ.generate(filepath1, filepath2));
        } catch (Exception e) {
            throw new RuntimeException("Возникла ошибка!");
        }
    }
    @Option(names = {"-f", "--format"}, description = "output format [default: stylish]")
    String format = "stylish";
    public static void main(String[] args) {
        new CommandLine(new App()).execute(args);
    }
}
