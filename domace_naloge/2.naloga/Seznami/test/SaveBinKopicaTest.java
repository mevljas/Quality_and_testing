
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import org.easymock.EasyMock;
import static org.easymock.EasyMock.replay;
import org.junit.*;

public class SaveBinKopicaTest {
// public class MenjalnoRazmerjeTest extends TestCase 

    private BinomskaKopica testObject;

    public SaveBinKopicaTest() {
    }

    @Before
    public void setUp() {
        testObject = new BinomskaKopica<>(new BolnikPrimerjajPoImenu());
    }

    /**
     * Test method razreda <printTestSklad.>
     */
    @Test
    public void testSaveMemory() throws FileNotFoundException {
        saveBinKopica mock = EasyMock.createMock(saveBinKopica.class);
        FileOutputStream fis = new FileOutputStream(new File("Test1"));
        mock.save(fis);
        EasyMock.expectLastCall()
                .andAnswer(() -> {
                    return null;
                });
        replay(mock);
    }

    @Test
    public void testSaveMemoryError() throws OutOfMemoryError, FileNotFoundException {
        saveBinKopica mock = EasyMock.createMock(saveBinKopica.class);
        FileOutputStream fis = new FileOutputStream(new File("Test1"));
        mock.save(fis);
        EasyMock.expectLastCall()
                .andThrow(new OutOfMemoryError())
                .andAnswer(() -> {
                    return null;
                });
        replay(mock);
    }
}
