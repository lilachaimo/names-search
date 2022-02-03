import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Reader {

    private static final int MAX_LINE_COUNT = 1000;

    public void getFilePart(String path) throws IOException {

        FileInputStream fileInputStream = null;
        Scanner sc = null;

        try{
            StringBuilder sb = new StringBuilder();
            fileInputStream = new FileInputStream(path);
            sc = new Scanner(fileInputStream,"UTF-8");

            int lineNumber = 0;

            while(sc.hasNextLine()){
                if( lineNumber % MAX_LINE_COUNT == 0)
                    sb.append(sc.nextLine());
                else{
                    System.out.println("send to match");
                    sb = new StringBuilder();
                }
            }
        }finally {
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            if (sc != null) {
                sc.close();
            }
        }



    }

}
