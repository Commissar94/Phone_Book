package phonebook;

import java.util.Collections;
import java.util.List;

class SearchAndSorting {

    private final List<String> directory;
    private final List<String> find;
    private long searchTime;
    private long sortingTime;
    private int matchCounter;
    boolean isTooLong = false;

    SearchAndSorting(List<String> directory, List<String> find) {
        this.directory = directory;
        this.find = find;
    }

    protected void linearSearch() {

        matchCounter = 0;

        /* Start time in milliseconds */
        long startTime = System.currentTimeMillis();

        /* Search algorithm */
        for (String findValue : find) {
            for (String directoryValue : directory) {
                if (directoryValue.contains(findValue)) {
                    matchCounter++;
                    break;
                }
            }
        }

        /* Total search time in milliseconds */
        searchTime = System.currentTimeMillis() - startTime;

    }

    protected void bubbleSort() {

        System.out.println("Start searching (bubble sort + jump search)...");

        long startTime = System.currentTimeMillis();

        for (int i = 0; i < directory.size() - 1; i++) {
            for (int j = 0; j < directory.size() - i - 1; j++) {
                if (directory.get(j).compareTo(directory.get(j + 1)) > 0) {
                    Collections.swap(directory, j, j + 1);
                    sortingTime = System.currentTimeMillis() - startTime;

                    /* Stops if sorting time takes too long */
                    if (sortingTime > searchTime * 10) {
                        isTooLong = true;
                        return;
                    }
                }
            }
        }
    }

    protected void jumpSearch() {

        int blockSize = (int) Math.sqrt(directory.size());
        boolean isFound = false;
        int currentRight = 0;
        int prevRight = 0;
        matchCounter = 0;

        if (directory.size() == 0 || find.size() == 0) {
            searchTime = 0;
            return;
        }

        long startTime = System.currentTimeMillis();

        /* Jump search loop */
        for (String findValue : find) {
            if (directory.get(0).contains(findValue)) {
                matchCounter++;
            }
            while (currentRight < directory.size() - 1) {

                currentRight = Math.min(directory.size() - 1, currentRight + blockSize);

                if (directory.get(currentRight).compareTo(findValue) > 0) {
                    isFound = true;
                    break;
                }

                prevRight = currentRight;

            }

            /* Backward search in found block */
            if (isFound) {
                for (int j = currentRight; j > prevRight; j--) {
                    if (directory.get(j).contains(findValue)) {
                        matchCounter++;
                    }
                }
            }
        }

        searchTime = System.currentTimeMillis() - startTime;
    }

    private static String timeConverter(long totalMilliseconds) {

        /* Convert milliseconds into our time format */
        long minutes = (totalMilliseconds / 1000) / 60;
        long seconds = (totalMilliseconds / 1000) % 60;
        long millis = totalMilliseconds - seconds * 1000;
        return String.format("%2d min. %2d sec. %2d ms.", minutes, seconds, millis);
    }

    protected void output(boolean isNextSearch) {

        /* Output */
        if (!isNextSearch) {
            System.out.printf("Found %d / %d entries. ", matchCounter, find.size());
            System.out.println("Time taken: " + timeConverter(searchTime) + "\n");
        } else {
            System.out.printf("Found %d / %d entries. ", matchCounter, find.size());
            System.out.println("Time taken: " + timeConverter(sortingTime + searchTime));
            System.out.println("Sorting time: " + timeConverter(sortingTime) +
                    (isTooLong ? "- STOPPED, moved to linear search" : ""));
            System.out.println("Searching time: " + timeConverter(searchTime));
        }
    }
}