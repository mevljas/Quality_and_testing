
import org.junit.*;
import static org.junit.Assert.*;

public class SeznamiUVTest {

    private SeznamiUV uv;

    public SeznamiUVTest() {
    }

    @Before
    public void setUp() {
        uv = new SeznamiUV();
    }

    // TO DO 
    // razsirite integracijske teste za metodo Use
    @Test
    public void testUse() {
        uv.processInput("use pv");
        uv.processInput("use sk");
        uv.processInput("use bst");
    }

    @Test
    public void testUseIncorrect() {
        assertEquals("Error: please specify a correct data structure type (pv, sk, bst)",
                uv.processInput("use d"));
    }

    @Test
    public void testUseEmpty() {
        assertEquals("Error: please specify a data structure type (pv, sk, bst)",
                uv.processInput("use"));
    }

    // TO DO
    // napišite teste za sklad, prioritetno vrsto in BS drevo
    // testi kličejo (zaporedoma) vse operacije nad določeno strukturo 
    // glej POMOZNE METODE
    @Test
    public void testUseSklad() {
        uv.processInput("use sk");
        otherMethods();
        testSklad(true);
        reset();

    }

    @Test
    public void testUsePrioritetnaVrsta() {
        uv.processInput("use pv");
        otherMethods();
        testPrioritetnaVrsta(true);
        reset();
    }

    @Test
    public void testUseBst() {
        uv.processInput("use bst");
        otherMethods();
        testBst(true);
        reset();
    }

    // *****************
    // POMOZNE METODE
    // *****************   
    public void otherMethods() {
        reset();
        testAdd();
        reset();
        testAddNothing();
        reset();
        testRemoveFirst();
        reset();
        testRemoveFirstNothing();
        reset();
        testGetFirst();
        reset();
        testGetFirstNothing();
        reset();
        testSizeOnEmpty();
        reset();
        testSizeOne();
        reset();
        testSizeTwo();
        reset();
        testDepthOnEmpty();
        reset();
        testDepthOne();
        reset();
        testDepthTwo();
        reset();
        testIsEmptyEmpty();
        reset();
        testIsEmptyNotEmpty();
        reset();
        testResetOnEmpty();
        reset();
        testResetOnFull();
        reset();
        testExistsOne();
        reset();
        testExistsTwo();
        reset();
        testExistsMore();
        reset();
        testExistsEmpty();
        reset();
        testExistsFalse();
        reset();
        testExistsWrongArgument();
        reset();
        testRemoveOne();
        reset();
        testRemoveTwo();
        reset();
        testRemoveMore();
        reset();
        testRemoveEmpty();
        reset();
        testRemoveFalse();
        reset();
        testRemoveWrongArgument();
        reset();
        testAddTestSequence();
        reset();

    }

    public void reset() {
        uv.processInput("reset");
    }

    public void testAdd() {
        assertEquals("OK", uv.processInput("add Test1"));
        assertEquals("OK", uv.processInput("add Test2"));
    }

    public void testAddNothing() {
        assertEquals("Error: please specify a string", uv.processInput("add"));
    }

    public void testRemoveFirst() {
        assertEquals("OK", uv.processInput("add Test"));
        assertEquals("Test", uv.processInput("remove_first"));
    }

    public void testRemoveFirstNothing() {
        assertEquals("Error: data structure is empty", uv.processInput("remove_first"));
        assertEquals("Error: please specify a string", uv.processInput("add"));
        assertEquals("Error: data structure is empty", uv.processInput("remove_first"));
    }

    public void testGetFirst() {
        assertEquals("OK", uv.processInput("add Test"));
        assertEquals("Test", uv.processInput("get_first"));
    }

    public void testGetFirstNothing() {
        assertEquals("Error: data structure is empty", uv.processInput("get_first"));
        assertEquals("Error: please specify a string", uv.processInput("add"));
        assertEquals("Error: data structure is empty", uv.processInput("get_first"));
    }

    public void testSizeOnEmpty() {
        assertEquals("0", uv.processInput("size"));
    }

    public void testSizeOne() {
        assertEquals("OK", uv.processInput("add Test"));
        assertEquals("1", uv.processInput("size"));
    }

    public void testSizeTwo() {
        assertEquals("OK", uv.processInput("add Test1"));
        assertEquals("OK", uv.processInput("add Test2"));
        assertEquals("2", uv.processInput("size"));
    }

    public void testDepthOnEmpty() {
        assertEquals("0", uv.processInput("depth"));
    }

    public void testDepthOne() {
        assertEquals("OK", uv.processInput("add Test"));
        assertEquals("1", uv.processInput("depth"));
    }

    public void testDepthTwo() {
        assertEquals("OK", uv.processInput("add Test1"));
        assertEquals("OK", uv.processInput("add Test2"));
        assertEquals("2", uv.processInput("depth"));
    }

    public void testIsEmptyEmpty() {
        assertEquals("Data structure is empty", uv.processInput("is_empty"));
        assertEquals("Error: please specify a string", uv.processInput("add"));
        assertEquals("Data structure is empty", uv.processInput("is_empty"));
    }

    public void testIsEmptyNotEmpty() {
        assertEquals("OK", uv.processInput("add Test1"));
        assertEquals("OK", uv.processInput("add Test2"));
        assertEquals("OK", uv.processInput("add Test3"));
        assertEquals("Data structure is not empty", uv.processInput("is_empty"));
    }

    public void testResetOnEmpty() {
        assertEquals("OK", uv.processInput("reset"));
    }

    public void testResetOnFull() {
        assertEquals("OK", uv.processInput("add Test"));
        assertEquals("OK", uv.processInput("reset"));
        assertEquals("Error: data structure is empty", uv.processInput("remove_first"));
        assertEquals("0", uv.processInput("size"));
    }

    public void testExistsOne() {
        String name = "Test1";
        uv.processInput("add " + name);
        assertEquals("Element exists in data structure", uv.processInput("exists " + name));
    }

    public void testExistsTwo() {
        String name = "Test1";
        uv.processInput("add " + name);
        uv.processInput("add Test2");
        assertEquals("Element exists in data structure", uv.processInput("exists " + name));
    }

    public void testExistsMore() {
        String name = "Test1";
        uv.processInput("add Test3");
        uv.processInput("add " + name);
        uv.processInput("add Test2");
        uv.processInput("add Test6");
        assertEquals("Element exists in data structure", uv.processInput("exists " + name));
    }

    public void testExistsEmpty() {
        assertEquals("Data structure is empty", uv.processInput("exists Test1"));
    }

    public void testExistsFalse() {
        uv.processInput("add Test1");
        assertEquals("Element doesn't exist in data structure", uv.processInput("exists Test2"));
    }

    public void testExistsWrongArgument() {
        assertEquals("Please specify a string", uv.processInput("exists"));
    }

    public void testRemoveOne() {
        String name = "Test1";
        uv.processInput("add " + name);
        assertEquals(name, uv.processInput("remove " + name));
    }

    public void testRemoveTwo() {
        String name = "Test1";
        uv.processInput("add " + name);
        uv.processInput("add Test2");
        assertEquals(name, uv.processInput("remove " + name));
    }

    public void testRemoveMore() {
        String name = "Test1";
        uv.processInput("add Test3");
        uv.processInput("add " + name);
        uv.processInput("add Test2");
        uv.processInput("add Test6");
        assertEquals(name, uv.processInput("remove " + name));
    }

    public void testRemoveEmpty() {
        assertEquals("Data structure is empty", uv.processInput("remove Test1"));
    }

    public void testRemoveFalse() {
        uv.processInput("add Test1");
        assertEquals("Error: element does not exist in data structure", uv.processInput("remove Test2"));
    }

    public void testRemoveWrongArgument() {
        assertEquals("Error: please specify a string", uv.processInput("remove"));
    }

    public void testAddTestSequence() {
        assertEquals("OK", uv.processInput("add Test4"));
        assertEquals("OK", uv.processInput("add Test2"));
        assertEquals("OK", uv.processInput("add Test3"));
        assertEquals("OK", uv.processInput("add Test1"));
        assertEquals("OK", uv.processInput("add Test5"));
    }

    public void testSklad(boolean add) {
        if (add) {
            testAddTestSequence();
        }
        assertEquals("Test5", uv.processInput("remove_first"));
        assertEquals("Test1", uv.processInput("remove_first"));
        assertEquals("Test3", uv.processInput("remove_first"));
        assertEquals("Test2", uv.processInput("remove_first"));
        assertEquals("Test4", uv.processInput("remove_first"));
    }

    public void testPrioritetnaVrsta(boolean add) {
        if (add) {
            testAddTestSequence();
        }
        assertEquals("Test5", uv.processInput("remove_first"));
        assertEquals("Test4", uv.processInput("remove_first"));
        assertEquals("Test3", uv.processInput("remove_first"));
        assertEquals("Test2", uv.processInput("remove_first"));
        assertEquals("Test1", uv.processInput("remove_first"));
    }

    public void testBst(boolean add) {
        if (add) {
            testAddTestSequence();
        }
        assertEquals("Test4", uv.processInput("remove_first"));
        assertEquals("Test5", uv.processInput("remove_first"));
        assertEquals("Test2", uv.processInput("remove_first"));
        assertEquals("Test3", uv.processInput("remove_first"));
        assertEquals("Test1", uv.processInput("remove_first"));
    }

}
