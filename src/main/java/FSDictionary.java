import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


public class FSDictionary {

    private static final String Dictionary_FilePath = "first_names.txt";
    private Set<String> firstNames = new HashSet<String>();

    public FSDictionary() throws IOException, URISyntaxException {
        String dictionaryString = readFileAsString();

        firstNames.addAll(Arrays.asList(dictionaryString.split(",")));
    }

    private String readFileAsString() throws IOException {
        InputStream in = FSDictionary.class.getClassLoader().getResourceAsStream(Dictionary_FilePath);
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String dictionaryString = reader.readLine();
        reader.close();

        return dictionaryString;
    }

    public Set<String> getDictionaryWords() {
        return firstNames;
    }
}
