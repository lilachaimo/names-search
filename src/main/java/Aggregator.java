import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Aggregator {

    private final List<HashMap<String, List<String>>> blockWordsVsLocations;

    public Aggregator(List<HashMap<String, List<String>>> blockWordsVsLocations) {
        this.blockWordsVsLocations = blockWordsVsLocations;
    }

    public HashMap<String, List<String>> aggregate() {

        HashMap<String, List<String>> wordVsLocations = new HashMap<>();

        for (HashMap<String, List<String>> map : blockWordsVsLocations) {

            for (HashMap.Entry<String, List<String>> entry : map.entrySet()) {

                //Aggregation
                List<String> tempList = wordVsLocations.getOrDefault(entry.getKey(), new ArrayList<>());
                tempList.addAll(entry.getValue());
                wordVsLocations.put(entry.getKey(), tempList);
            }
        }

        return wordVsLocations;
    }

    /*public void print(){
        for (Map.Entry<String, List<String>> entry : data.entrySet()) {
            System.out.print(entry.getKey() + " --> [" +String.join(", ",entry.getValue())+"]");
            System.out.println("\n");
        }
    }*/
}
