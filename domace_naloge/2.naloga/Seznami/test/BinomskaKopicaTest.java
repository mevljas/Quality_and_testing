/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


class StringComparator implements Comparator<String> {

    @Override
    public int compare(String s1, String s2) {
        return s1.compareTo(s2);
    }
}
/**
 *
 * @author Seba
 */
public class BinomskaKopicaTest {

    private BinomskaKopica<String> bk;

    public BinomskaKopicaTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        bk = new BinomskaKopica(new StringComparator());
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testAddOne() {
        bk.add("Test");
    }

    @Test
    public void testAddMultiple() {
        bk.add("Test1");
        bk.add("Test2");
    }

    @Test
    public void testAddAdditional() {
        bk.add("Test1");
        bk.add("Test5");
        assertEquals("Test5", bk.getFirst());
        bk.add("Test2");
        assertEquals("Test5", bk.getFirst());
        bk.add("Test4");
        assertEquals("Test5", bk.getFirst());
        bk.add("Test3");
        assertEquals("Test5", bk.getFirst());
        bk.add("Test6");
        assertEquals("Test6", bk.getFirst());
        bk.add("Test9");
        assertEquals("Test9", bk.getFirst());
        bk.add("Test7");
        assertEquals("Test9", bk.getFirst());
    }

    @Test
    public void testAddAdditional2() {
        bk.add("Test1");
        bk.add("Test6");
        assertEquals("Test6", bk.getFirst());
        bk.add("Test8");
        assertEquals("Test8", bk.getFirst());
        bk.add("Test3");
        assertEquals("Test8", bk.getFirst());
        bk.add("Test5");
        assertEquals("Test8", bk.getFirst());
        bk.add("Test9");
        assertEquals("Test9", bk.getFirst());
        bk.add("Test2");
        assertEquals("Test9", bk.getFirst());
        bk.add("Test4");
        assertEquals("Test9", bk.getFirst());
    }

    @Test
    public void testAddAdditional3() {
        bk.add("Test1");
        bk.add("Test3");
        assertEquals("Test3", bk.getFirst());
        bk.add("Test2");
        assertEquals("Test3", bk.getFirst());
        bk.add("Test5");
        assertEquals("Test5", bk.getFirst());
        bk.add("Test4");
        assertEquals("Test5", bk.getFirst());
        bk.add("Test9");
        assertEquals("Test9", bk.getFirst());
        bk.add("Test8");
        assertEquals("Test9", bk.getFirst());
        bk.add("Test7");
        assertEquals("Test9", bk.getFirst());
    }

    @Test()
    public void testRemoveMultiple2() {
        bk.add("Test1");
        bk.add("Test9");
        bk.add("Test2");
        bk.add("Test8");
        bk.add("Test3");
        bk.add("Test6");
        bk.add("Test5");
        bk.add("Test4");
        assertEquals("Test6", bk.remove("Test6"));
        assertEquals("Test1", bk.remove("Test1"));
        assertEquals("Test9", bk.remove("Test9"));
    }

    @Test()
    public void testRemoveMultiple3() {
        bk.add("Test3");
        bk.add("Test6");
        bk.add("Test5");
        bk.add("Test4");
        bk.add("Test1");
        bk.add("Test9");
        bk.add("Test2");
        bk.add("Test8");
        assertEquals("Test6", bk.remove("Test6"));
        assertEquals("Test1", bk.remove("Test1"));
        assertEquals("Test9", bk.remove("Test9"));
    }

    // testi brisanja
    @Test(expected = java.util.NoSuchElementException.class)
    public void testRemoveFirstEmpty() {
        bk.removeFirst();
    }

    @Test
    public void testRemoveFirstOne() {
        bk.add("Test");
        assertEquals("Test", bk.removeFirst());
    }

    @Test
    public void testRemoveFirstAdditional() {
        bk.add("Test1");
        bk.add("Test2");
        bk.add("Test3");
        bk.add("Test4");
        bk.add("Test9");
        bk.add("Test7");
        bk.add("Test8");
        bk.add("Test5");
        assertEquals("Test9", bk.removeFirst());
        assertEquals("Test8", bk.removeFirst());
        assertEquals("Test7", bk.removeFirst());
    }

    @Test
    public void testRemoveFirstMultiple() {
        bk.add("Test1");
        bk.add("Test5");
        bk.add("Test2");
        bk.add("Test4");
        bk.add("Test3");
        assertEquals("Test5", bk.removeFirst());
        assertEquals("Test4", bk.removeFirst());
        assertEquals("Test3", bk.removeFirst());
        assertEquals("Test2", bk.removeFirst());
        assertEquals("Test1", bk.removeFirst());
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
        String name99 = "Test99";
        bk.add(name1);
        bk.add(name2);
        bk.add(name3);
        bk.add(name4);
        bk.add(name5);
        bk.add(name9);
        bk.add(name8);
        bk.add(name99);
        bk.add(name6);
        bk.add(name7);
        assertEquals(name99, bk.removeFirst());
        assertEquals(name9, bk.removeFirst());
        assertEquals(name8, bk.removeFirst());
        assertEquals(name7, bk.removeFirst());
        assertEquals(name6, bk.removeFirst());
        assertEquals(name5, bk.removeFirst());
        assertEquals(name4, bk.removeFirst());
        assertEquals(name3, bk.removeFirst());
        assertEquals(name2, bk.removeFirst());
        assertEquals(name1, bk.removeFirst());

    }

    @Test
    public void testGetFirstOne() {
        bk.add("Test");
        assertEquals("Test", bk.getFirst());
        assertEquals(1, bk.size());
        assertEquals(0, bk.depth());
    }

    @Test
    public void testGetFirstMultiple() {
        bk.add("Test2");
        assertEquals("Test2", bk.getFirst());
        assertEquals(1, bk.size());
        assertEquals(0, bk.depth());
        bk.add("Test3");
        bk.add("Test1");
        assertEquals("Test3", bk.getFirst());
        assertEquals("Test3", bk.getFirst());
        assertEquals(3, bk.size());
        assertEquals(1, bk.depth());
    }

    @Test(expected = java.util.NoSuchElementException.class)
    public void testGetFirstOnEmpty() {
        bk.getFirst();
    }

    // test metode size
    @Test
    public void testSizeOnEmpty() {
        assertEquals(0, bk.size());
    }

    @Test
    public void testSizeOne() {
        bk.add("Test");
        assertEquals(1, bk.size());
    }

    @Test
    public void testSizeMultiple() {
        assertEquals(0, bk.size());
        bk.add("Test");
        assertEquals(1, bk.size());
        bk.add("Test1");
        assertEquals(2, bk.size());
        bk.add("Test2");
        assertEquals(3, bk.size());
    }

    // testiranje metode za globino
    @Test
    public void testDepthOnEmpty() {
        assertEquals(0, bk.depth());
    }

    @Test
    public void testDepthOne() {
        bk.add("Test1");
        assertEquals(0, bk.depth());
    }

    @Test
    public void testDepthMultiple() {
        bk.add("Test1");
        assertEquals(0, bk.depth());
        bk.add("Test5");
        assertEquals(1, bk.depth());
        bk.add("Test2");
        assertEquals(1, bk.depth());
        bk.add("Test4");
        assertEquals(2, bk.depth());
        bk.add("Test3");
        assertEquals(2, bk.depth());
        bk.add("Test6");
        assertEquals(2, bk.depth());
        bk.add("Test8");
        assertEquals(2, bk.depth());
        bk.add("Test7");
        assertEquals(3, bk.depth());
    }

    // test metode isEmpty
    @Test
    public void testIsEmptyEmpty() {
        assertTrue(bk.isEmpty());
    }

    @Test
    public void testIsEmptyOne() {
        bk.add("Test");
        assertFalse(bk.isEmpty());
    }

    @Test
    public void testIsEmptyMultiple() {
        bk.add("Test");
        bk.add("Test1");
        bk.add("Test2");
        assertFalse(bk.isEmpty());
    }

    // exists
    @Test()
    public void testExistsBasic() {
        bk.add("Test1");
        assertTrue(bk.exists("Test1"));
    }

    @Test
    public void testExistsFalse() {
        bk.add("Test");
        assertFalse(bk.exists("Test2"));
    }

    @Test()
    public void testExistsMultiple() {
        bk.add("Test");
        bk.add("Test1");
        bk.add("Test2");
        bk.add("Test3");
        assertTrue(bk.exists("Test2"));
    }

    @Test
    public void testExistsEmpty() {
        assertFalse(bk.exists("Test2"));
    }

    // remove
    @Test()
    public void testRemoveBasic() {
        String name = "Test1";
        bk.add(name);
        assertEquals(name, bk.remove(name));
    }

    @Test(expected = java.util.NoSuchElementException.class)
    public void testRemoveFalse() {
        bk.add("Test");
        assertEquals("Test2", bk.remove("tt"));
    }

    @Test()
    public void testRemoveMultiple() {
        String name1 = "Test1";
        String name2 = "Test4";
        bk.add("Test");
        bk.add(name1);
        bk.add("Test3");
        bk.add(name2);
        bk.add("Test5");
        assertEquals(name1, bk.remove(name1));
        assertEquals(name2, bk.remove(name2));
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
        bk.add(name1);
        bk.add(name2);
        bk.add(name3);
        bk.add(name4);
        bk.add(name5);
        bk.add(name9);
        bk.add(name8);
        bk.add(name10);
        bk.add(name6);
        bk.add(name7);
        assertEquals(name10, bk.remove(name10));
        assertEquals(name1, bk.remove(name1));
        assertEquals(name8, bk.remove(name8));
        assertEquals(name4, bk.remove(name4));
        assertEquals(name6, bk.remove(name6));
        assertEquals(name5, bk.remove(name5));
        assertEquals(name7, bk.remove(name7));
        assertEquals(name2, bk.remove(name2));
        assertEquals(name3, bk.remove(name3));
        assertEquals(name9, bk.remove(name9));

    }

    @Test(expected = java.util.NoSuchElementException.class)
    public void testRemoveEmpty() {
        bk.remove("test");
    }
    
    
    @Test
    public void testSave() {
        
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File("test.txt"));
            bk.add("test");
            bk.add("test1");
            bk.add("test2");
            assertEquals(3, bk.size());
            bk.save(fileOutputStream);
            assertEquals(3, bk.size());
        } catch (FileNotFoundException ex) {
            ;
            
        } catch (IOException ex) {
            ;
        }
        
    }
    
    
    @Test
    public void testRestore() {
        
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File("test.txt"));
            bk.add("test");
            bk.add("test1");
            bk.add("test2");
            assertEquals(3, bk.size());
            bk.save(fileOutputStream);
            fileOutputStream.close();
            bk.removeFirst();
            bk.removeFirst();
            bk.removeFirst();
            FileInputStream fileInputStream = new FileInputStream(new File("test.txt"));
            bk.restore(fileInputStream);
            assertEquals(3, bk.size());
        } catch (FileNotFoundException ex) {
            ;
            
        } catch (IOException ex) {
            ;
        } catch (ClassNotFoundException ex) {
            ;
        }
        
    }
    
    
    @Test
    public void testSearch() {
        
        bk.add("test1");
        bk.add("test2");
        bk.add("test3");
        assertEquals("test2", bk.search("test2"));
        
    }
    
    
    @Test
    public void testprint() {
        
        bk.add("test");
        bk.print();
        
    }
    
    
    
    



}
