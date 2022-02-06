import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Reader {

    private static final int MAX_LINE_COUNT = 1000;

    Set<String> dictionary;
    FileInputStream fileInputStream;
    private ExecutorService executorService;
    private final List<HashMap<String, List<String>>> results = new ArrayList<>();

    public Reader(Set<String> dictionary, FileInputStream inputStream) {
        this.dictionary = dictionary;
        this.fileInputStream = inputStream;
    }

    public void process() throws Exception {
        // Create a thread pool for available processors
        executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        // Match words
        List<HashMap<String, List<String>>> blockWordsWithLocations = getFilePart();

        // Initiate shutdown
        executorService.shutdown();

        // Aggregate the results
        Aggregator aggregator = new Aggregator(blockWordsWithLocations);
        HashMap<String, List<String>> wordVsLocations = aggregator.aggregate();

        // Print
        print(wordVsLocations);
    }


    public List<HashMap<String, List<String>>> getFilePart() throws Exception {



        Scanner sc = new Scanner(fileInputStream, StandardCharsets.UTF_8);

        int lineNumber = 1;
        int blockNumber = 0;
        List<LineDetails> list = new ArrayList<>();

        while (sc.hasNextLine()) {
            if (lineNumber < MAX_LINE_COUNT) {
                LineDetails lineDetails = new LineDetails(sc.nextLine(), (lineNumber + blockNumber * MAX_LINE_COUNT));
                list.add(lineDetails);
                lineNumber++;
            } else {
                //The method that's start a new Thread
                matchWordsInBlock(list);
                list = new ArrayList<>();
                blockNumber++;
                lineNumber = 0;
            }


        }
        //The method that's start a new Thread - for the last small(<1000) block
        matchWordsInBlock(list);
        return results;


    }

    private void matchWordsInBlock(List<LineDetails> input) throws Exception {
        Matcher matcher = new Matcher(input, dictionary);
        Future<HashMap<String, List<String>>> futures = executorService.submit(matcher);
        results.add(new HashMap<>(futures.get()));
    }

    public void print(HashMap<String, List<String>> wordLocations) {
        for (Map.Entry<String, List<String>> entry : wordLocations.entrySet()) {
            System.out.print(entry.getKey() + " --> [" + String.join(", ", entry.getValue()) + "]");
            System.out.println("\n");
        }
    }

}
