package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine;
import picocli.CommandLine.*;

import java.io.File;

@Command(
        name = "gendiff",
        version = "gendiff 2.28",
        mixinStandardHelpOptions = true
)
public class App implements Runnable {
    @Parameters(index = "0", description = "path to first file")
    private File filepath1;
    @Parameters(index = "1", description = "path to second file")
    private File filepath2;
    @Override
    public void run() {
        System.out.println("Hello World!");
    }
    @Option(names = {"-f", "--format"}, description = "output format [default: stylish]")
    String format;
    public static void main(String[] args) {
        new CommandLine(new App()).execute(args);
    }
}