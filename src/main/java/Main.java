import java.io.File;
import java.io.FileInputStream;

public class Main {




    public static void main(String[] args) throws Exception {


        File file = new File(Constants.BIG_FILE);
         /*FileInputStream just opens the connection to the file to be read,
           be it images, characters, etc.
           It doesn’t particularly care what the file actually is,
           because Java reads the input stream as raw bytes of data.*/
        FileInputStream inputStream = new FileInputStream(file);
        Reader reader = new Reader(Constants.getNamesToFind(), inputStream);
        reader.process();



    }


}
