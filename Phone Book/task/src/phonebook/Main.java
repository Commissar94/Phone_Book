package phonebook;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        String phoneBookPath = "C:\\Users\\Andrey\\Downloads\\directory.txt";
        String findPath = "C:\\Users\\Andrey\\Downloads\\find.txt";

        List<String> findList = Files.readAllLines(Paths.get(findPath));
        List<String> phoneBookList = Files.readAllLines(Paths.get(phoneBookPath));

        long lines = 0;
        System.out.println("Start searching...");
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < phoneBookList.size(); i++) {
            String currentNumberAndPerson = phoneBookList.get(i);
            String currentPerson = currentNumberAndPerson.substring(currentNumberAndPerson.indexOf(" ")).substring(1);
            for (int j = 0; j < findList.size(); j++) {
                if (currentPerson.equals(findList.get(j))) {
                    lines = lines + 1;
                }
            }
        }

        long estimatedTime = System.currentTimeMillis() - startTime;
        long min = estimatedTime / 60000;
        long sec = estimatedTime / 1000;
        long ms = estimatedTime - min*60000 - sec*1000;
        System.out.printf("Found %d / 500 entries. Time taken: %d min. %d sec. %d ms.%n", lines, min,
                sec, ms);
    }
}