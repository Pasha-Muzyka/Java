import java.io.BufferedWriter;
import java.io.IOException;

public class FileWriter {
    public static void writeStringToFile(String text, String filePath) {
        try(BufferedWriter writer = new BufferedWriter(new java.io.FileWriter(filePath))) {
            writer.write(text);
        } catch (IOException exception) {
            System.out.println("File did not write");
        }
    }
}
