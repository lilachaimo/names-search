import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class Matcher implements Runnable{

    private static String FIRST_NAMES = "James,John,Robert,Michael,William,David,Richard,Charles,Joseph,Thomas,Christopher,Daniel,Paul,Mark,Donald,George,Kenneth,Steven,Edward,Brian,Ronald,Anthony,Kevin,Jason,Matthew,Gary,Timothy,Jose,Larry,Jeffrey,Frank,Scott,Eric,Stephen,Andrew,Raymond,Gregory,Joshua,Jerry,Dennis,Walter,Patrick,Peter,Harold,Douglas,Henry,Carl,Arthur,Ryan,Roger";

    private String input;

    public Matcher (String input){
        this.input = input;
    }

    public ConcurrentHashMap<String, Integer[][]> createNamesMap(){

    }

    @Override
    public void run() {

    }
}
