import java.util.*;
import java.util.concurrent.Callable;


public class Matcher implements Callable<HashMap<String, List<String>>> {

    private final List<LineDetails> input;


    private final Set<String> dictionary;

    private HashMap<String, List<String>> result;



    public Matcher (List<LineDetails> input, Set<String> dictionary){
        this.input = List.copyOf(input);
        this.dictionary = dictionary;

    }





    private List<String> getMatchesByLineAndWord(LineDetails lineDetails, String name){
        int charOffset = 0;
        int wordLength = 0;
        List<String> matches = new ArrayList<>();
        while (charOffset != -1) {
            charOffset = lineDetails.getLine().indexOf(name, charOffset + wordLength);
            if (charOffset != -1) {
                matches.add("[lineOffset=" + lineDetails.getLineIndex() + ", charOffset=" + (charOffset + 1) + "]");
            }
            wordLength = name.length();
        }

          return matches;
    }


    @Override
    public HashMap<String, List<String>> call() {
        System.out.println("Start Current Thread ID- " + Thread.currentThread().getId());


        result = new HashMap<>();
        input.forEach( line -> {
        dictionary.forEach(name -> {
                List<String> matches = getMatchesByLineAndWord(line, name);
                if (!matches.isEmpty()) {
                    if(!result.containsKey(name))
                        result.put(name, new ArrayList<>());

                    result.get(name).add("[" + String.join(",", matches) + "]");

                }

            });

        });


        return result;
    }

}
