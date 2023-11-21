package apps.demo.filemanagement;
import apps.demo.models.User;
import java.io.FileWriter;
import java.io.IOException;

public class WriteFile {
    public static void writeFile(User user) {
        String filePath = "C:\\prog\\demo\\src\\main\\java\\apps\\demo\\filemanagement\\files\\test.txt";
        try (FileWriter fileWriter = new FileWriter(filePath, true)) {
            fileWriter.write(user.toJsonString() + System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
