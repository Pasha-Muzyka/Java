import java.io.*;

public class FileReader {
    public static String readFileToString(String filePath){

        StringBuilder sb = new StringBuilder();
        try(BufferedReader br = new BufferedReader(new java.io.FileReader(filePath))) {

            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }

        } catch (IOException exception){
            System.out.println("File did not read!!!");
        }

        return sb.toString();
    }
}
