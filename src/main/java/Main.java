import java.io.File;
import java.io.FileInputStream;

public class Main {




    public static void main(String[] args) throws Exception {


        File file = new File(Constants.FILE_LOCATION);
        FileInputStream inputStream = new FileInputStream(file);
        Reader reader = new Reader(Constants.getNamesToFind(), inputStream);
        reader.process();



    }


}
