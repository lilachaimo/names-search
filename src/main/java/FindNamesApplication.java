import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;

public class FindNamesApplication {

    private static final String FILE_LOCATION = "big.txt";


    public static void main(String[] args) throws Exception {


        File file = new File(FILE_LOCATION);
        FileInputStream inputStream = new FileInputStream(file);
        Reader reader = new Reader(Constant.getNamesToFind(), inputStream);
        reader.process();



    }


}
