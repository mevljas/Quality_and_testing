
import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;

public class SkladTest {

    static Sklad<String> instance;

    public SkladTest() {
    }

    @BeforeClass
    public static void setUpOnce() {
        instance = new Sklad<>();
    }

    @Before
    public void setUp() {
        while (!instance.isEmpty()) {
            instance.pop();
        }
    }

    @Test
    public void testPush() {
        String a = "Test";
        instance.push(a);
    }

    @Test
    public void testPop() {
        String a = "Test";
        instance.push(a);
        String b = instance.pop();
        assertEquals("Test", b);
    }

    @Test
    public void testWithTwoElements() {
        String a = "Prvi element";
        String b = "Drugi element";
        instance.push(a);
        instance.push(b);
        assertEquals(b, instance.pop());
        assertEquals(a, instance.pop());
    }

    @Test(expected = java.util.NoSuchElementException.class)
    public void testPopOnEmptyStack() {
        String a = instance.pop();
    }

    @Test
    public void testIsEmptyOnEmpty() {
        assertTrue(instance.isEmpty());
    }

    @Test
    public void testIsEmptyOnFull() {
        instance.push("Test");
        assertFalse(instance.isEmpty());
    }

    @Test(expected = java.util.NoSuchElementException.class)
    public void testTopOnEmptyStack() {
        String a = instance.top();
    }

    @Test
    public void testTopOnFullStack() {
        String a = "Vrednost 1";
        String b = "Vrednost 2";
        instance.push(a);
        instance.push(b);
        String c = instance.top();
        assertEquals(c, b);
        assertEquals(2, instance.size());
    }

    @Test
    public void testTopSame() {
        instance.push("Test1");
        instance.push("Test2");
        instance.push("Test3");
        assertEquals("Test3", instance.top());
        assertEquals("Test3", instance.pop());
        assertEquals("Test2", instance.pop());
        assertEquals(1, instance.size());
        assertEquals("Test1", instance.pop());
        assertTrue(instance.isEmpty());
    }

    @Test
    public void testSizeEmpty() {
        assertEquals(0, instance.size());
        assertTrue(instance.isEmpty());
    }

    @Test
    public void testSizeOne() {
        instance.push("Test1");
        assertEquals(1, instance.size());
        assertEquals("Test1", instance.pop());
        assertTrue(instance.isEmpty());
    }

    @Test(timeout = 100)
    public void testSizeMore() {
        instance.push("Vrednost 1");
        instance.push("Vrednost 2");
        instance.push("Vrednost 3");
        assertEquals(3, instance.size());
        assertEquals("Vrednost 3", instance.top());
    }

    @Test
    public void testIsTopTrue() {
        instance.push("Test");
        assertTrue(instance.isTop("Test"));
    }

    @Test
    public void testIsTopFalse() {
        instance.push("Test1");
        assertFalse(instance.isTop("Test2"));
    }

    @Test(expected = java.util.NoSuchElementException.class)
    public void testIsTopEmpty() {
        assertFalse(instance.isTop("Test"));
    }

    @Test
    public void testIsTopSame() {
        instance.push("Test1");
        instance.push("Test2");
        instance.push("Test3");
        assertTrue(instance.isTop("Test3"));
        assertEquals("Test3", instance.pop());
        assertEquals("Test2", instance.pop());
        assertEquals("Test1", instance.pop());
        assertTrue(instance.isEmpty());
    }

    @Test
    public void testSearchEmpty() {
        assertEquals(-1, instance.search("Test"));
    }

    @Test(timeout = 100)
    public void testSearchFoundTop() {
        instance.push("Vrednost 1");
        instance.push("Vrednost 2");
        instance.push("Vrednost 3");
        assertEquals(0, instance.search("Vrednost 3"));
    }

    @Test(timeout = 100)
    public void testSearchFoundNonTop() {
        instance.push("Vrednost 1");
        instance.push("Vrednost 2");
        instance.push("Vrednost 3");
        assertEquals(2, instance.search("Vrednost 1"));
    }

    @Test(timeout = 100)
    public void testSearchNotFound() {
        instance.push("Vrednost 1");
        instance.push("Vrednost 2");
        instance.push("Vrednost 3");
        assertEquals(-1, instance.search("Vrednost"));
    }

    @Test
    public void testSearchSame() {
        instance.push("Test1");
        instance.push("Test2");
        instance.push("Test3");
        assertEquals(1, instance.search("Test2"));
        assertEquals("Test3", instance.pop());
        assertEquals("Test2", instance.pop());
        assertEquals("Test1", instance.pop());
        assertTrue(instance.isEmpty());
    }

    // exists
    @Test()
    public void testExistsBasic() {
        instance.add("Test1");
        assertTrue(instance.exists("Test1"));
    }

    @Test
    public void testExistsFalse() {
        instance.add("Test");
        assertFalse(instance.exists("test2"));
    }

    @Test()
    public void testExistsMultiple() {
        instance.add("Test");
        instance.add("Test1");
        instance.add("Test2");
        instance.add("Test3");
        assertTrue(instance.exists("Test2"));
    }

    @Test
    public void testExistsEmpty() {
        assertFalse(instance.exists("test"));
    }

    // remove
    @Test()
    public void testRemoveBasic() {
        String name = "Test1";
        instance.add(name);
        assertEquals(name, instance.remove(name));
    }

    @Test(expected = java.util.NoSuchElementException.class)
    public void testRemoveFalse() {
        instance.add("Test");
        assertEquals("Test2", instance.remove("tt"));
    }

    @Test()
    public void testRemoveMultiple() {
        String name1 = "Test1";
        String name2 = "Test4";
        instance.add("Test");
        instance.add(name1);
        instance.add("Test3");
        instance.add(name2);
        instance.add("Test5");
        assertEquals(name1, instance.remove(name1));
        assertEquals(name2, instance.remove(name2));
    }

    @Test(expected = java.util.NoSuchElementException.class)
    public void testRemoveEmpty() {
        instance.remove("test");
    }
    
    // testiranje metode asList
   

    @Test
    public void testAsListOne() {
        instance.add("Test1");
        assertEquals("Test1", instance.asList().get(0));
        assertEquals(1, instance.asList().size());
    }

    @Test
    public void testAsListMultiple() {
        instance.add("Test1");
        instance.add("Test5");
        instance.add("Test2");
        instance.add("Test4");
        instance.add("Test3");
        instance.add("Test6");
        instance.add("Test8");
        instance.add("Test7");

        List l = instance.asList();
        assertEquals("Test7", l.get(0));
        assertEquals("Test8", l.get(1));
        assertEquals("Test6", l.get(2));
        assertEquals("Test3", l.get(3));
        assertEquals("Test4", l.get(4));
        assertEquals("Test2", l.get(5));
        assertEquals("Test5", l.get(6));
        assertEquals("Test1", l.get(7));
        assertEquals(8, instance.asList().size());
    }
    
    @Test
    public void testasListOnEmpty() {
        assertTrue( instance.asList().isEmpty());
    }

}
