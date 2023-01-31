import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class SQLFileMerger {
    public static void main(String[] args) {
        Path inputsPath = Paths.get("../inputs");
        File folder = new File(inputsPath.toAbsolutePath().toString());

        File[] listOfFiles = folder.listFiles();
        Arrays.sort(listOfFiles);

        Path mergePath = Paths.get("../output/mergedFile.sql");
        File mergedFile = new File(mergePath.toAbsolutePath().toString());

        try {
            // Create the file if it doesn't exist
            if (!mergedFile.exists()) {
                mergedFile.createNewFile();
            }
            try (FileWriter writer = new FileWriter(mergedFile)) {
                for (File file : listOfFiles) {
                    if (file.isFile() && file.getName().endsWith(".sql")) {
                        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                            String line;
                            while ((line = reader.readLine()) != null) {
                                writer.write(line + System.lineSeparator());
                            }
                        }
                    }
                }
                System.out.println("SQL files merged successfully!");
            }
        } catch (IOException e) {
            System.out.println("Error merging SQL files: " + e.getMessage());
        }
    }
}
