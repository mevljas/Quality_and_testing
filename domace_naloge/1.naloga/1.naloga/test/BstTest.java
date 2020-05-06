
import java.util.List;
import static org.junit.Assert.*;
import org.junit.*;

public class BstTest {

    private Bst<String> bst;

    public BstTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        bst = new Bst<>();
    }

    @After
    public void tearDown() {
    }

    // Praviloma bi morali testirati vsako funkcijo v razredu
    // torej tudi: member, insert, delete, deleteMin, getDepth, countNodes
    // Glede na to, da teste zasnujemo pred poznavanjem podrobnosti implementacije
    // zasnujemo teste za metode vmesnika: 
    // add, removeFirst, getFirst, size,depth, isEmpty, remove, exists
    // testi dodajanja
    @Test
    public void testAddOne() {
        bst.add("Test");
    }

    @Test
    public void testAddMultiple() {
        bst.add("Test1");
        bst.add("Test2");
    }

    @Test(expected = java.lang.IllegalArgumentException.class)
    public void testAddExists() {
        bst.add("Test");
        bst.add("Test");
    }

    // testi brisanja
    @Test(expected = java.util.NoSuchElementException.class)
    public void testRemoveFirstEmpty() {
        bst.removeFirst();
    }

    @Test
    public void testRemoveFirstOne() {
        bst.add("Test");
        assertEquals("Test", bst.removeFirst());
    }

    @Test
    public void testRemoveFirstMultiple() {
        bst.add("Test1");
        bst.add("Test5");
        bst.add("Test2");
        bst.add("Test4");
        bst.add("Test3");
        assertEquals("Test1", bst.removeFirst());
        assertEquals("Test5", bst.removeFirst());
        assertEquals("Test2", bst.removeFirst());
        assertEquals("Test4", bst.removeFirst());
        assertEquals("Test3", bst.removeFirst());
    }

    @Test()
    public void testRemoveFirstMultipleAdditional() {
        String name1 = "Test1";
        String name2 = "Test2";
        String name3 = "Test3";
        String name4 = "Test4";
        String name5 = "Test5";
        String name6 = "Test6";
        String name7 = "Test7";
        String name8 = "Test8";
        String name9 = "Test9";
        String name10 = "Test10";
        bst.add(name1);
        bst.add(name2);
        bst.add(name3);
        bst.add(name4);
        bst.add(name5);
        bst.add(name9);
        bst.add(name8);
        bst.add(name10);
        bst.add(name6);
        bst.add(name7);
        assertEquals(name1, bst.removeFirst());
        assertEquals(name2, bst.removeFirst());
        assertEquals(name3, bst.removeFirst());
        assertEquals(name4, bst.removeFirst());
        assertEquals(name5, bst.removeFirst());
        assertEquals(name6, bst.removeFirst());
        assertEquals(name7, bst.removeFirst());
        assertEquals(name8, bst.removeFirst());
        assertEquals(name9, bst.removeFirst());
        assertEquals(name10, bst.removeFirst());

    }

    @Test
    public void testGetFirstOne() {
        bst.add("Test");
        assertEquals("Test", bst.getFirst());
        assertEquals(1, bst.size());
        assertEquals(1, bst.depth());
    }

    @Test
    public void testGetFirstMultiple() {
        bst.add("Test2");
        assertEquals("Test2", bst.getFirst());
        assertEquals(1, bst.size());
        assertEquals(1, bst.depth());
        bst.add("Test3");
        bst.add("Test1");
        assertEquals("Test2", bst.getFirst());
        assertEquals("Test2", bst.getFirst());
        assertEquals(3, bst.size());
        assertEquals(2, bst.depth());
    }

    @Test(expected = java.util.NoSuchElementException.class)
    public void testGetFirstOnEmpty() {
        bst.getFirst();
    }

    // test metode size
    @Test
    public void testSizeOnEmpty() {
        assertEquals(0, bst.size());
    }

    @Test
    public void testSizeOne() {
        bst.add("Test");
        assertEquals(1, bst.size());
    }

    @Test
    public void testSizeMultiple() {
        assertEquals(0, bst.size());
        bst.add("Test");
        assertEquals(1, bst.size());
        bst.add("Test1");
        assertEquals(2, bst.size());
        bst.add("Test2");
        assertEquals(3, bst.size());
    }

    // testiranje metode za globino
    @Test
    public void testDepthOnEmpty() {
        assertEquals(0, bst.depth());
    }

    @Test
    public void testDepthOne() {
        bst.add("Test1");
        assertEquals(1, bst.depth());
    }

    @Test
    public void testDepthMultiple() {
        bst.add("Test1");
        assertEquals(1, bst.depth());
        bst.add("Test5");
        assertEquals(2, bst.depth());
        bst.add("Test2");
        assertEquals(3, bst.depth());
        bst.add("Test4");
        assertEquals(4, bst.depth());
        bst.add("Test3");
        assertEquals(5, bst.depth());
        bst.add("Test6");
        assertEquals(5, bst.depth());
        bst.add("Test8");
        assertEquals(5, bst.depth());
        bst.add("Test7");
        assertEquals(5, bst.depth());
    }

    // test metode isEmpty
    @Test
    public void testIsEmptyEmpty() {
        assertTrue(bst.isEmpty());
    }

    @Test
    public void testIsEmptyOne() {
        bst.add("Test");
        assertFalse(bst.isEmpty());
    }

    @Test
    public void testIsEmptyMultiple() {
        bst.add("Test");
        bst.add("Test1");
        bst.add("Test2");
        assertFalse(bst.isEmpty());
    }

    // exists
    @Test()
    public void testExistsBasic() {
        bst.add("Test1");
        assertTrue(bst.exists("Test1"));
    }

    @Test
    public void testExistsFalse() {
        bst.add("Test");
        assertFalse(bst.exists("Test2"));
    }

    @Test()
    public void testExistsMultiple() {
        bst.add("Test");
        bst.add("Test1");
        bst.add("Test2");
        bst.add("Test3");
        assertTrue(bst.exists("Test2"));
    }

    @Test
    public void testExistsEmpty() {
        assertFalse(bst.exists("Test2"));
    }

    // remove
    @Test()
    public void testRemoveBasic() {
        String name = "Test1";
        bst.add(name);
        assertEquals(name, bst.remove(name));
    }

    @Test(expected = java.util.NoSuchElementException.class)
    public void testRemoveFalse() {
        bst.add("Test");
        assertEquals("Test2", bst.remove("tt"));
    }

    @Test()
    public void testRemoveMultiple() {
        String name1 = "Test1";
        String name2 = "Test4";
        bst.add("Test");
        bst.add(name1);
        bst.add("Test3");
        bst.add(name2);
        bst.add("Test5");
        assertEquals(name1, bst.remove(name1));
        assertEquals(name2, bst.remove(name2));
    }

    @Test()
    public void testRemoveMultipleAdditional() {
        String name1 = "Test1";
        String name2 = "Test2";
        String name3 = "Test3";
        String name4 = "Test4";
        String name5 = "Test5";
        String name6 = "Test6";
        String name7 = "Test7";
        String name8 = "Test8";
        String name9 = "Test9";
        String name10 = "Test10";
        bst.add(name1);
        bst.add(name2);
        bst.add(name3);
        bst.add(name4);
        bst.add(name5);
        bst.add(name9);
        bst.add(name8);
        bst.add(name10);
        bst.add(name6);
        bst.add(name7);
        assertEquals(name10, bst.remove(name10));
        assertEquals(name1, bst.remove(name1));
        assertEquals(name8, bst.remove(name8));
        assertEquals(name4, bst.remove(name4));
        assertEquals(name6, bst.remove(name6));
        assertEquals(name5, bst.remove(name5));
        assertEquals(name7, bst.remove(name7));
        assertEquals(name2, bst.remove(name2));
        assertEquals(name3, bst.remove(name3));
        assertEquals(name9, bst.remove(name9));

    }

    @Test(expected = java.util.NoSuchElementException.class)
    public void testRemoveEmpty() {
        bst.remove("test");
    }
    
    // testiranje metode asList
   

    @Test
    public void testAsListOne() {
        bst.add("Test1");
        assertEquals("Test1", bst.asList().get(0));
        assertEquals(1, bst.asList().size());
    }

    @Test
    public void testAsListMultiple() {
        bst.add("Test1");
        bst.add("Test5");
        bst.add("Test2");
        bst.add("Test4");
        bst.add("Test3");
        bst.add("Test6");
        bst.add("Test8");
        bst.add("Test7");

        List l = bst.asList();
        assertEquals("Test1", l.get(0));
        assertEquals("Test2", l.get(1));
        assertEquals("Test3", l.get(2));
        assertEquals("Test4", l.get(3));
        assertEquals("Test5", l.get(4));
        assertEquals("Test6", l.get(5));
        assertEquals("Test7", l.get(6));
        assertEquals("Test8", l.get(7));
        assertEquals(8, bst.asList().size());
    }
    
     @Test
    public void testasListOnEmpty() {
        assertTrue( bst.asList().isEmpty());
    }

}
