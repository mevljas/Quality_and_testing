/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Seba
 */
public class SeznamiUVTest {

    SeznamiUV uv;

    public SeznamiUVTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        uv = new SeznamiUV();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testPushBasic() {
        System.out.println("testPushBasic");
        assertEquals("OK", uv.processInput("push Test1"));
        assertEquals("OK", uv.processInput("push Test2"));
    }

    @Test
    public void testPushMultipleWords() {
        System.out.println("testPushMultipleWords");
        assertEquals("OK",
                uv.processInput("push \"Test with multiple words\""));
        assertEquals("1", uv.processInput("count"));
        assertEquals("OK", uv.processInput("push \"Another test with multiple words\""));
        assertEquals("2", uv.processInput("count"));
    }

    @Test
    public void testPushNothing() {
        System.out.println("testPushNothing");
        assertEquals("Error: please specify a string", uv.processInput("push"));
    }

    @Test
    public void testPopBasic() {
        System.out.println("testPopBasic");
        assertEquals("OK", uv.processInput("push Test1"));
        assertEquals("OK", uv.processInput("push Test2"));
        assertEquals("Test2", uv.processInput("pop"));
        assertEquals("Test1", uv.processInput("pop"));
    }

    @Test
    public void testPopMultipleWords() {
        System.out.println("testPopMultipleWords");
        assertEquals("OK", uv.processInput("push \"Test with multiple words\""));
        assertEquals("OK", uv.processInput("push \"Another test with multiple words\""));
        assertEquals("2", uv.processInput("count"));
        assertEquals("Another test with multiple words", uv.processInput("pop"));
        assertEquals("1", uv.processInput("count"));
        assertEquals("Test with multiple words", uv.processInput("pop"));
        assertEquals("0", uv.processInput("count"));
    }

    @Test
    public void testPopNothing() {
        System.out.println("testPopNothing");
        assertEquals("Error: stack is empty", uv.processInput("pop"));
        assertEquals("Error: please specify a string", uv.processInput("push"));
        assertEquals("Error: stack is empty", uv.processInput("pop"));
    }

    @Test
    public void testResetOnEmpty() {
        System.out.println("testResetOnEmpty");
        assertEquals("OK", uv.processInput("reset"));
    }

    @Test
    public void testResetOnFull() {
        System.out.println("testResetOnFull");
        assertEquals("OK", uv.processInput("push Test"));
        assertEquals("OK", uv.processInput("reset"));
        assertEquals("Error: stack is empty", uv.processInput("pop"));
        assertEquals("0", uv.processInput("count"));
    }

    @Test
    public void testCountOnEmpty() {
        System.out.println("testCountOnEmpty");
        assertEquals("0", uv.processInput("count"));
    }

    @Test(timeout = 100)
    public void testCountOne() {
        System.out.println("testCountOne");
        assertEquals("OK", uv.processInput("push Test"));
        assertEquals("1", uv.processInput("count"));
    }

    @Test(timeout = 100)
    public void testCountTwo() {
        System.out.println("testCountTwo");
        assertEquals("OK", uv.processInput("push Test1"));
        assertEquals("OK", uv.processInput("push Test2"));
        assertEquals("2", uv.processInput("count"));
    }

    @Test
    public void testIsTopOne() {
        System.out.println("testIsTopOne");
        assertEquals("OK", uv.processInput("push Test1"));
        assertEquals("OK", uv.processInput("isTop Test1"));
    }

    @Test
    public void testIsTopTwo() {
        System.out.println("testIsTopTwo");
        assertEquals("OK", uv.processInput("push Test1"));
        assertEquals("OK", uv.processInput("push Test2"));
        assertEquals("OK", uv.processInput("isTop Test2"));
    }

    @Test
    public void testIsTopTen() {
        System.out.println("testIsTopTen");
        for (int i = 1; i <= 10; i++) {
            assertEquals("OK", uv.processInput("push Test" + i + ""));
        }

        assertEquals("OK", uv.processInput("isTop Test10"));
    }

    @Test
    public void testIsTopOnEmpty() {
        System.out.println("testIsTopOnEmpty");
        assertEquals("Error: stack is empty", uv.processInput("isTop Test1"));
        assertEquals("Error: please specify a string", uv.processInput("push"));
        assertEquals("Error: stack is empty", uv.processInput("isTop Test1"));
    }

    @Test
    public void testIsTopWrongElement() {
        System.out.println("testIsTopWrongElement");
        assertEquals("OK", uv.processInput("push Test1"));
        assertEquals("OK", uv.processInput("push Test2"));
        assertEquals("Error: wrong element", uv.processInput("isTop Test5"));
        assertEquals("Error: please specify a string", uv.processInput("push"));
        assertEquals("Error: wrong element", uv.processInput("isTop Test5"));
    }

    @Test
    public void testIsTopNoArgument() {
        System.out.println("testIsTopWrongElement");
        assertEquals("OK", uv.processInput("push Test1"));
        assertEquals("OK", uv.processInput("push Test2"));
        assertEquals("Error: please specify a string", uv.processInput("isTop"));
        assertEquals("Error: please specify a string", uv.processInput("push"));
        assertEquals("Error: please specify a string", uv.processInput("isTop"));
    }

    @Test
    public void testIsTopImmutability() {
        System.out.println("testIsTopImmutability");
        String a = "Test1";
        String b = "Test2";
        assertEquals("OK", uv.processInput("push " + a));
        assertEquals("OK", uv.processInput("push " + b));
        uv.processInput("isTop Test2");
        assertEquals(b, uv.processInput("pop"));

    }

    @Test(timeout = 100)
    public void testSearchBasic() {
        System.out.println("testSearchBasic");
        assertEquals("OK", uv.processInput("push Test1"));
        assertEquals("OK", uv.processInput("push Test2"));
        assertEquals("0", uv.processInput("search Test2"));
    }

    @Test(timeout = 100)
    public void testSearchNoArgument() {
        System.out.println("testSearchNoArgument");
        assertEquals("OK", uv.processInput("push Test1"));
        assertEquals("OK", uv.processInput("push Test2"));
        assertEquals("Error: please specify a string", uv.processInput("search"));
        assertEquals("Error: please specify a string", uv.processInput("push"));
        assertEquals("Error: please specify a string", uv.processInput("search"));
    }

}
