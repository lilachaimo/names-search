import java.util.*;
import java.util.concurrent.Callable;


public class Matcher implements Callable<HashMap<String, List<String>>> {

    private List<LineDetailes> input;

    private int lineNumber;
    private Set<String> dictionary;

    private HashMap<String, List<String>> result;



    public Matcher (List<LineDetailes> input, Set<String> dictionary, int lineNumber){
        this.input = input;
        this.dictionary = dictionary;
        this.lineNumber = lineNumber;


    }

    public void createNamesMap(){
        //create map
        //put words to the map
        //aggregator.add(map);

       /*dictionary.forEach(name -> {
           result = new ConcurrentHashMap<>();
           input.forEach( line -> {
                   List<String> matches = getMatchesByLineAndWord(line, name);
               if (!matches.isEmpty()) {
                   if(!result.containsKey(name))
                       result.put(name, new ArrayList<>());

               result.get(name).add("[" + String.join(",", matches) + "]");
                  // print(result);
               }

           });
      //     print(result);
       });
        return result;
      //  aggregator.add(result);
*/
    }

    public void print(HashMap<String, List<String>> wordVslocations) {
        for (Map.Entry<String, List<String>> entry : wordVslocations.entrySet()) {
            System.out.print(entry.getKey() + " --> [" +String.join(", ",entry.getValue())+"]");
            System.out.println("\n");
        }
    }

    private List<String> getMatchesByLineAndWord(LineDetailes lineDetailes, String name){
        int charOffset = 0;
        int wordLength = 0;
        List<String> matches = new ArrayList<>();
        while (charOffset != -1) {
            charOffset = lineDetailes.getLine().indexOf(name, charOffset + wordLength);
            if (charOffset != -1) {
                matches.add("[lineOffset=" + lineDetailes.getLineIndex() + ", charOffset=" + (charOffset + 1) + "]");
            }
            wordLength = name.length();
        }

          return matches;
    }


    @Override
    public HashMap<String, List<String>> call() throws Exception {

        result = new HashMap<>();
        dictionary.forEach(name -> {
            input.forEach( line -> {
                List<String> matches = getMatchesByLineAndWord(line, name);
                if (matches.isEmpty() == false) {
                    if(result.containsKey(name) == false)
                        result.put(name, new ArrayList<>());

                    result.get(name).add("[" + String.join(",", matches) + "]");
                    // print(result);
                }

            });
            //     print(result);
        });
        return result;
    }
}
