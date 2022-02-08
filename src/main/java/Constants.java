import java.util.Arrays;
import java.util.HashSet;

public class Constants {

    public static final String BIG_FILE = "big.txt";
    public static final String SMALL_FILE = "small.txt";

    public static HashSet<String> getNamesToFind() {

        String namesToFind = "James,John,Robert,Michael,William,David,Richard,Charles,Joseph,Thomas,Christopher,Daniel,Paul,Mark,Donal d,George,Kenneth,Steven,Edward,Brian,Ronald,Anthony,Kevin,Jason,Matthew,Gary,Timothy,Jose,Larry,Jeffrey,Frank,Scott,Eric,Stephen,Andrew,Raymond,Gregory,Joshua,Jerry,Dennis,Walter,Patrick,Peter,Harold,Douglas,Henry,Carl,Arthur,Ryan,Roger";
        String[] names = namesToFind.split(",");

        return new HashSet<>(Arrays.asList(names));
    }
}
