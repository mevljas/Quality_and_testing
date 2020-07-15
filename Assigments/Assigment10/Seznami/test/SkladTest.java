
import org.junit.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SkladTest {

    public SkladTest() {
    }

    @Test
    public void testPush() {
        Sklad instance = new Sklad(new StringComparator());
        String a = "Test";
        instance.push(a);
    }

    @Test
    public void testPop() {
        Sklad<String> instance = new Sklad<>(new StringComparator());
        String a = "Test";
        instance.push(a);
        String b = instance.pop();
        assertEquals("Test", b);
    }

    @Test
    public void testWithTwoElements() {
        Sklad<String> instance = new Sklad<>(new StringComparator());
        String a = "Prvi test";
        String b = "Drugi test";
        instance.push(a);
        instance.push(b);
        assertEquals(b, instance.pop());
        assertEquals(a, instance.pop());
    }

    @Test(expected = java.util.NoSuchElementException.class)
    public void testPopOnEmptyStack() {
        Sklad<String> instance = new Sklad<>(new StringComparator());
        String a = instance.pop();
    }

    @Test
    public void testIsEmptyOnEmpty() {
        Sklad<String> instance = new Sklad<>(new StringComparator());
        assertTrue(instance.isEmpty());
    }

    @Test
    public void testIsEmptyOnFull() {
        Sklad<String> instance = new Sklad<>(new StringComparator());
        instance.push("Test");
        assertFalse(instance.isEmpty());
    }
    
     @Test(expected = java.util.NoSuchElementException.class)
    public void testPeekOnEmptyStack() {
        Sklad<String> instance = new Sklad<>(new StringComparator());
        String a = instance.peek();
    }

    @Test
    public void testPeekOnFullStack() {
        Sklad<String> instance = new Sklad<>(new StringComparator());
        String a = "Vrednost 1";
        String b = "Vrednost 2";
        instance.push(a);
        instance.push(b);
        String c = instance.peek();
        assertEquals(c, b);
    }

    @Test
    public void testPeekSame() {
        Sklad<String> instance = new Sklad<>(new StringComparator());
        instance.push("Test1");
        instance.push("Test2");
        instance.push("Test3");
        assertEquals("Test3", instance.peek());
        assertEquals("Test3", instance.pop());
        assertEquals("Test2", instance.pop());
        assertEquals("Test1", instance.pop());
        assertTrue(instance.isEmpty());
    }

    @Test
    public void testCountEmpty() {
        Sklad<String> instance = new Sklad<>(new StringComparator());
        assertEquals(0, instance.count());
    }

    @Test(timeout = 100)
    public void testCountNonEmpty() {
        Sklad<String> instance = new Sklad<>(new StringComparator());
        instance.push("Vrednost 1");
        instance.push("Vrednost 2");
        instance.push("Vrednost 3");
        assertEquals(3, instance.count());
    }

    @Test
    public void testCountSame() {
        Sklad<String> instance = new Sklad<>(new StringComparator());
        instance.push("Test1");
        instance.push("Test2");
        instance.push("Test3");
        assertEquals(3, instance.count());
        assertEquals("Test3", instance.pop());
        assertEquals("Test2", instance.pop());
        assertEquals("Test1", instance.pop());
        assertTrue(instance.isEmpty());
    }

   

    @Test
    public void testSearchEmpty() {
        Sklad<String> instance = new Sklad<>(new StringComparator());
        assertEquals(-1, instance.search("Test"));
    }

    @Test(timeout = 100)
    public void testSearchFoundTop() {
        Sklad<String> instance = new Sklad<>(new StringComparator());
        instance.push("Vrednost 1");
        instance.push("Vrednost 2");
        instance.push("Vrednost 3");
        assertEquals(0, instance.search("Vrednost 3"));
    }

    @Test(timeout = 100)
    public void testSearchFoundNonTop() {
        Sklad<String> instance = new Sklad<>(new StringComparator());
        instance.push("Vrednost 1");
        instance.push("Vrednost 2");
        instance.push("Vrednost 3");
        assertEquals(2, instance.search("Vrednost 1"));
    }

    @Test(timeout = 100)
    public void testSearchNotFound() {
        Sklad<String> instance = new Sklad<>(new StringComparator());
        instance.push("Vrednost 1");
        instance.push("Vrednost 2");
        instance.push("Vrednost 3");
        assertEquals(-1, instance.search("Vrednost"));
    }

    @Test
    public void testSearchSame() {
        Sklad<String> instance = new Sklad<>(new StringComparator());
        instance.push("Test1");
        instance.push("Test2");
        instance.push("Test3");
        assertEquals(1, instance.search("Test2"));
        assertEquals("Test3", instance.pop());
        assertEquals("Test2", instance.pop());
        assertEquals("Test1", instance.pop());
        assertTrue(instance.isEmpty());
    }

    // Testi za add, removeFirst, getFirst, size in depth
    // so zelo poenostavljeni, ker gre le za klice že testiranih metod! 
    @Test
    public void testAdd() {
        Sklad<String> instance = new Sklad<>(new StringComparator());
        String a = "Test";
        instance.add(a);
    }

    @Test
    public void testRemoveFirst() {
        Sklad<String> instance = new Sklad<>(new StringComparator());
        String a = "Test";
        instance.add(a);
        String b = instance.removeFirst();
        assertEquals("Test", b);
    }

    @Test
    public void testGetFirst() {
        Sklad<String> instance = new Sklad<>(new StringComparator());
        String a = "Test";
        instance.add(a);
        String b = instance.getFirst();
        assertEquals("Test", b);
    }

    @Test
    public void testSize() {
        Sklad<String> instance = new Sklad<>(new StringComparator());
        String a = "Test";
        assertEquals(0, instance.size());
        instance.add(a);
        assertEquals(1, instance.size());
    }

    @Test
    public void testDepth() {
        Sklad<String> instance = new Sklad<>(new StringComparator());
        String a = "Test";
        assertEquals(0, instance.depth());
        instance.add(a);
        assertEquals(1, instance.depth());
    }

    // Testi za remove() in exists() ...
    @Test
    public void testRemoveOK() {
        Sklad<String> instance = new Sklad<>(new StringComparator());
        String a = "Test";
        instance.add(a);
        assertEquals(a, instance.remove(a));
        instance.add("Test1");
        instance.add("Test2");
        instance.add(a);
        instance.add("Test3");
        instance.add("Test4");
        assertEquals(a, instance.remove(a));
    }

    @Test(expected = java.util.NoSuchElementException.class)
    public void testRemoveOnEmptyStack() {
        Sklad<String> instance = new Sklad<>(new StringComparator());
        instance.add("Test1");
        instance.add("Test2");
        instance.add("Test3");
        instance.add("Test4");
        String a = instance.remove("Test");
    }


    @Test(expected = java.util.NoSuchElementException.class)
    public void testRemoveNOK() {
        Sklad<String> instance = new Sklad<>(new StringComparator());
        String a = instance.remove("Test");
    }


    @Test
    public void testExists() {
        Sklad<String> instance = new Sklad<>(new StringComparator());
        String a = "Test";
        assertFalse(instance.exists(a));
        instance.add(a);
        assertTrue(instance.exists(a));
    }
}
