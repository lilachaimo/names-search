import java.util.Arrays;
import java.util.HashSet;

public class Constant {

    public static HashSet<String> getNamesToFind() {

        String namesToFind = "James,John,Robert,Michael,William,David,Richard,Charles,Joseph,Thomas,Christopher,Daniel,Paul,Mark,Donal d,George,Kenneth,Steven,Edward,Brian,Ronald,Anthony,Kevin,Jason,Matthew,Gary,Timothy,Jose,Larry,Jeffrey,Frank,Scott,Eric,Stephen,Andrew,Raymond,Gregory,Joshua,Jerry,Dennis,Walter,Patrick,Peter,Harold,Douglas,Henry,Carl,Arthur,Ryan,Roger";
        //String namesToFind = "The";
        //String namesToFind = "Project";
        //String namesToFind = "eBook,Arthur";
        String[] names = namesToFind.split(",");

        return new HashSet<>(Arrays.asList(names));
    }
}
