import java.io.FileWriter;
import java.io.IOException;

public class CSVWriter {
    public static void write(String fileName, String content) {
        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.write(content + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
