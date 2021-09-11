package phonebook;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        String phoneBookPath = "C:\\Users\\Andrey\\Downloads\\directory.txt";  //small_directory.txt or directory.txt
        String findPath = "C:\\Users\\Andrey\\Downloads\\find.txt";

        /* Get all the lines from the files into lists */
        List<String> phoneBook = Files.readAllLines(Paths.get(phoneBookPath));
        List<String> find = Files.readAllLines(Paths.get(findPath));

        SearchAndSorting newSearch = new SearchAndSorting(phoneBook, find);

        System.out.println("Start searching (linear search)...");
        newSearch.linearSearch();
        newSearch.output(false);
        newSearch.bubbleSort();
        if (newSearch.isTooLong) {
            newSearch.linearSearch();
        } else {
            newSearch.jumpSearch();
        }
        newSearch.output(true);
    }
}