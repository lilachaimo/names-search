import java.io.FileInputStream;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Reader {

    private static final int MAX_LINE_COUNT = 1000;

    Set<String> dictionary;
    FileInputStream fileInputStream;
    private ExecutorService executorService;
    private  final List<HashMap<String, List<String>>> results = new ArrayList<>();

    public Reader(Set<String> dictionary, FileInputStream inputStream) {
        this.dictionary = dictionary;
        this.fileInputStream = inputStream;
    }

    public void process() throws Exception {
// Create a thread pool for available proccessors
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



        Scanner sc = null;




            sc = new Scanner(fileInputStream,"UTF-8");

            int lineNumber = 1;
            int blockNumber = 0;
            List<LineDetailes> list = new ArrayList<>();

            while(sc.hasNextLine()){
                if( lineNumber <  MAX_LINE_COUNT) {
                    LineDetailes lineDetailes = new LineDetailes(sc.nextLine(), (lineNumber + blockNumber * MAX_LINE_COUNT));
                    list.add(lineDetailes);
                    lineNumber++;
                }else{
                    matchWordsInBlock(list, (lineNumber + blockNumber * MAX_LINE_COUNT));
                    list = new ArrayList<>();
                    blockNumber ++;
                    lineNumber = 0;
                }





        }
        matchWordsInBlock(list, (lineNumber + blockNumber * MAX_LINE_COUNT));
        return results;


    }

    private void matchWordsInBlock(List<LineDetailes> input, Integer chunkNumber) throws Exception {
        Matcher matcher = new Matcher(input, dictionary, chunkNumber);

        Future<HashMap<String, List<String>>> futures = executorService.submit(matcher);
        results.add(new HashMap<>(futures.get()));
    }
    public void print(HashMap<String, List<String>> wordVslocations) {
        for (Map.Entry<String, List<String>> entry : wordVslocations.entrySet()) {
            System.out.print(entry.getKey() + " --> [" +String.join(", ",entry.getValue())+"]");
            System.out.println("\n");
        }
    }

}
