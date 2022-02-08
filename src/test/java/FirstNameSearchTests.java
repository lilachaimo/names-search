import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FirstNameSearchTests {




    @Test
    public void blocker_counts() throws Exception {
        File file = new File(Constants.BIG_FILE);
        FileInputStream inputStream = new FileInputStream(file);
        Reader reader = new Reader(Constants.getNamesToFind(), inputStream);
        reader.process();
        assertEquals(128, reader.getBlockNumber());
    }

    @Test
    public void resultForSmallTest() throws Exception {
        File file = new File(Constants.SMALL_FILE);
        FileInputStream inputStream = new FileInputStream(file);
        Reader reader = new Reader(Constants.getNamesToFind(), inputStream);
        reader.process();
        assertEquals("[[[lineOffset=2, charOffset=8]], [[lineOffset=3, charOffset=27]]]",
                reader.getWordVsLocations().get("Arthur").toString());
    }
}
