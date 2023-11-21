package apps.demo.filemanagement;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ReadFile {
    public static String readFile () {
        List<String> lines = new ArrayList<>();
        Random random = new Random();
        String filePath = "C:\\prog\\demo\\src\\main\\java\\apps\\demo\\filemanagement\\files\\test1.txt";
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        int randomIndex = random.nextInt(lines.size());
        return lines.get(randomIndex);
    }
}
