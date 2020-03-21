
import org.junit.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SkladTest {

    Sklad<String> instance;

    public SkladTest() {
    }

    @Before
    public void setUp() {
        instance = new Sklad();
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
        String a = "Prvi test";
        String b = "Drugi test";
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

    @Test
    public void testTop() {
        String a = "Test";
        instance.push(a);
        String b = instance.top();
        assertEquals("Test", b);
    }

    @Test
    public void testTopWithTwoElements() {
        String a = "Prvi test";
        String b = "Drugi test";
        instance.push(a);
        instance.push(b);
        assertEquals(b, instance.top());
    }

    @Test
    public void testTopWithTenElements() {
        for (int i = 0; i < 10; i++) {
            instance.push(i + ". test");
        }
        String ten = "9. test";
        assertEquals(ten, instance.top());
    }

    @Test(expected = java.util.NoSuchElementException.class)
    public void testTopOnEmptyStack() {
        instance.top();
    }

    @Test
    public void testTopImmutability() {
        String a = "Prvi test";
        String b = "Drugi test";
        instance.push(a);
        instance.push(b);
        instance.top();
        assertEquals(b, instance.pop());

    }

    @Test
    public void testSize() {
        String a = "Test";
        instance.push(a);
        int size = instance.size();
        assertEquals(1, size);
    }

    @Test
    public void testSizeWithTwoElements() {
        String a = "Prvi test";
        String b = "Drugi test";
        instance.push(a);
        instance.push(b);
        int size = instance.size();
        assertEquals(2, size);
    }

    @Test
    public void testSizeWithTenElements() {
        for (int i = 0; i < 10; i++) {
            instance.push("" + i);
        }
        int size = instance.size();
        assertEquals(10, size);
    }

    @Test
    public void testSizeOnEmptyStack() {
        int size = instance.size();
        assertEquals(0, size);
    }

    @Test
    public void testSizeImmutability() {
        String a = "Prvi test";
        String b = "Drugi test";
        instance.push(a);
        instance.push(b);
        instance.size();
        assertEquals(b, instance.pop());

    }

    @Test(timeout = 100)
    public void testSearchOne() {
        System.out.println("testSearchOne");
        String a = "Test1";
        instance.push(a);
        assertEquals(0, instance.search(a));
    }

    //@Test(timeout=100)
    @Test
    public void testSearchTwo() {
        System.out.println("testSearchTwo");
        String a = "Test1";
        String b = "Test2";
        instance.push(a);
        instance.push(b);
        instance.search(b);
        assertEquals(0, instance.search(b));
    }

    @Test(timeout = 100)
    public void testSearchDeeper() {
        System.out.println("testSearchDeeper");
        String a = "Test1";
        String b = "Test2";
        String c = "Test3";
        instance.push(a);
        instance.push(b);
        instance.push(c);
        assertEquals(1, instance.search(b));
    }

    @Test(timeout = 100)
    public void testSearchTen() {
        System.out.println("testSearchTen");
        for (int i = 1; i <= 10; i++) {
            instance.push("Test" + i + "");
        }
        assertEquals(0, instance.search("Test10"));
    }

    @Test
    public void testSearchOnEmpty() {
        System.out.println("testSearchOnEmpty");
        assertEquals(-1, instance.search("Test1"));
    }

    @Test(timeout = 100)
    public void testSearchNoElement() {
        System.out.println("testSearchNoElement");
        String a = "Test1";
        String b = "Test2";
        instance.push(a);
        instance.push(b);
        assertEquals(-1, instance.search("Test5"));
    }

    @Test(timeout = 100)
    public void testSearchImmutability() {
        System.out.println("testSearchImmutability");
        String a = "Test1";
        String b = "Test2";
        instance.push(a);
        instance.push(b);
        instance.search("Test2");
        assertEquals(b, instance.pop());

    }
}
