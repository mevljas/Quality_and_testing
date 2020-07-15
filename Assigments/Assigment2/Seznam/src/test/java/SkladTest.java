import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SkladTest {
    
    
    public SkladTest() {
    }
    @Test
    public void testPush() {
        Sklad instance = new Sklad();
        String a = "Test";
        instance.push(a);
    }
    
    @Test
    public void testPop() {
        Sklad<String> instance = new Sklad<>();
        String a = "Test";
        instance.push(a);
        String b = instance.pop();
        assertEquals("Test", b);
    }
    

    @Test
    public void testWithTwoElements(){
        Sklad<String> instance = new Sklad<>();
        String a = "Prvi test";
        String b = "Drugi test";
        instance.push(a);
        instance.push(b);
        assertEquals(b, instance.pop());
        assertEquals(a, instance.pop());
    }
    
    @Test
    public void testPopOnEmptyStack(){
        Sklad<String> instance = new Sklad<>();
        assertThrows(java.util.NoSuchElementException.class,
                () -> {instance.pop();});
    }
    
    
    @Test
    public void testIsEmptyOnEmpty(){
        Sklad<String> instance = new Sklad<>();
        assertTrue(instance.isEmpty());
    }
    @Test
    public void testIsEmptyOnFull(){
        Sklad<String> instance = new Sklad<>();
        instance.push("Test");
        assertFalse(instance.isEmpty());
    }
    
    @Test
    public void testTop() {
        Sklad<String> instance = new Sklad<>();
        String a = "Test";
        instance.push(a);
        String b = instance.top();
        assertEquals("Test", b);
    }
    
    @Test
    public void testTopWithTwoElements(){
        Sklad<String> instance = new Sklad<>();
        String a = "Prvi test";
        String b = "Drugi test";
        instance.push(a);
        instance.push(b);
        assertEquals(b, instance.top());
    }
    
    @Test
    public void testTopWithTenElements(){
        Sklad<String> instance = new Sklad<>();
        for (int i = 0; i < 10; i++) {
            instance.push(i+". test");
        }
        String ten = "9. test";
        assertEquals(ten, instance.top());
    }
    
    @Test
    public void testTopOnEmptyStack(){
        Sklad<String> instance = new Sklad<>();
        assertThrows(java.util.NoSuchElementException.class,
                () -> {instance.top();});
    }
    
    @Test
    public void testTopImmutability(){
        Sklad<String> instance = new Sklad<>();
        String a = "Prvi test";
        String b = "Drugi test";
        instance.push(a);
        instance.push(b);
        instance.top();
        assertEquals(b, instance.pop());
        
    }
    
    
    @Test
    public void testSize() {
        Sklad<String> instance = new Sklad<>();
        String a = "Test";
        instance.push(a);
        int size= instance.Size();
        assertEquals(1, size);
    }
    
    @Test
    public void testSizeWithTwoElements() {
        Sklad<String> instance = new Sklad<>();
        String a = "Prvi test";
        String b = "Drugi test";
        instance.push(a);
        instance.push(b);
        int size= instance.Size();
        assertEquals(2, size);
    }
    
    @Test
    public void testSizeWithTenElements() {
        Sklad<String> instance = new Sklad<>();
        for (int i = 0; i < 10; i++) {
            instance.push(""+i);
        }
        int size= instance.Size();
        assertEquals(10, size);
    }
    
    
    @Test
    public void testSizeOnEmptyStack(){
        Sklad<String> instance = new Sklad<>();
        int size= instance.Size();
        assertEquals(0, size);
    }
    
    
    @Test
    public void testSizeImmutability(){
        Sklad<String> instance = new Sklad<>();
        String a = "Prvi test";
        String b = "Drugi test";
        instance.push(a);
        instance.push(b);
        instance.Size();
        assertEquals(b, instance.pop());
        
    }
    
    
    
    
    
}