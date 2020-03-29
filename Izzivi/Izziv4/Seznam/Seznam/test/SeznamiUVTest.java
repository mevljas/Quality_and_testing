
import org.junit.*;
import static org.junit.Assert.*;

public class SeznamiUVTest {

    private SeznamiUV uv;
    private PrioritetnaVrsta<String> pv;

    public SeznamiUVTest() {
    }

    @Before
    public void setUp() {
        uv = new SeznamiUV();
        pv = new PrioritetnaVrsta<>();
    }

    @Test
    public void testPushBasic() {
        assertEquals("OK", uv.processInput("s_add Test1"));
        assertEquals("OK", uv.processInput("s_add Test2"));
    }

    // @Ignore("To be implemented at a later stage...")
    @Test
    public void testPushMultipleWords() {
        assertEquals("OK", uv.processInput("s_add \"Test with multiple words\""));
        assertEquals("1", uv.processInput("s_size"));
        assertEquals("OK", uv.processInput("s_add \"Another test with multiple words\""));
        assertEquals("2", uv.processInput("s_size"));
    }

    // @Ignore("To be implemented at a later stage...")
    @Test
    public void testPushMultipleWordsIncorrect() {
        assertEquals("Error: please specify a correct string", uv.processInput("s_add \"Test with multiple words"));
    }

    @Test
    public void testPushNothing() {
        assertEquals("Error: please specify a string", uv.processInput("s_add"));
    }

    @Test
    public void testPopBasic() {
        assertEquals("OK", uv.processInput("s_add Test1"));
        assertEquals("OK", uv.processInput("s_add Test2"));
        assertEquals("Test2", uv.processInput("s_removefirst"));
        assertEquals("Test1", uv.processInput("s_removefirst"));
    }

    // @Ignore("To be implemented at a later stage...")
    @Test
    public void testPopMultipleWords() {
        assertEquals("OK", uv.processInput("s_add \"Test with multiple words\""));
        assertEquals("OK", uv.processInput("s_add \"Another test with multiple words\""));
        assertEquals("2", uv.processInput("s_size"));
        assertEquals("Another test with multiple words", uv.processInput("s_removefirst"));
        assertEquals("1", uv.processInput("s_size"));
        assertEquals("Test with multiple words", uv.processInput("s_removefirst"));
        assertEquals("0", uv.processInput("s_size"));
    }

    @Test
    public void testPopNothing() {
        assertEquals("Error: stack is empty", uv.processInput("s_removefirst"));
        assertEquals("Error: please specify a string", uv.processInput("s_add"));
        assertEquals("Error: stack is empty", uv.processInput("s_removefirst"));
    }

    @Test
    public void testResetOnEmpty() {
        assertEquals("OK", uv.processInput("s_reset"));
    }

    @Test
    public void testResetOnFull() {
        assertEquals("OK", uv.processInput("s_add Test"));
        assertEquals("OK", uv.processInput("s_reset"));
        assertEquals("Error: stack is empty", uv.processInput("s_removefirst"));
        assertEquals("0", uv.processInput("s_size"));
    }

    @Test
    public void testCountOnEmpty() {
        assertEquals("0", uv.processInput("s_size"));
    }

    @Test(timeout = 100)
    public void testCountMore() {
        assertEquals("OK", uv.processInput("s_add Test1"));
        assertEquals("OK", uv.processInput("s_add Test2"));
        assertEquals("2", uv.processInput("s_size"));
    }

    @Test
    public void testIsTopBasic() {
        assertEquals("OK", uv.processInput("s_add Test1"));
        assertEquals("OK", uv.processInput("s_add Test2"));
        assertEquals("OK", uv.processInput("s_isTop Test2"));
    }

    // @Ignore("To be implemented at a later stage...")
    @Test
    public void testIsTopMultipleWords() {
        assertEquals("OK", uv.processInput("s_add \"Test with multiple words\""));
        assertEquals("OK", uv.processInput("s_isTop \"Test with multiple words\""));
    }

    // @Ignore("To be implemented at a later stage...")
    @Test
    public void testIsTopMultipleWordsIncorrect() {
        assertEquals("Error: please specify a correct string", uv.processInput("s_isTop \"Test with multiple words"));
    }

    @Test
    public void testIsTopNotEqual() {
        assertEquals("OK", uv.processInput("s_add Test1"));
        assertEquals("OK", uv.processInput("s_add Test2"));
        assertEquals("2", uv.processInput("s_size"));
        assertEquals("Error: wrong element", uv.processInput("s_isTop Test"));
        assertEquals("2", uv.processInput("s_size"));
    }

    @Test
    public void testIsTopEmpty() {
        assertEquals("Error: stack is empty", uv.processInput("s_isTop Test"));
        assertEquals("Error: please specify a string", uv.processInput("s_add"));
        assertEquals("Error: stack is empty", uv.processInput("s_isTop Test"));
    }

    @Test
    public void testIsTopNothing() {
        assertEquals("Error: please specify a string", uv.processInput("s_isTop"));
    }

    @Test
    public void testSearchFound() {
        assertEquals("OK", uv.processInput("s_add Test1"));
        assertEquals("OK", uv.processInput("s_add Test2"));
        assertEquals("0", uv.processInput("s_search Test2"));
        assertEquals("OK", uv.processInput("s_add Test3"));
        assertEquals("2", uv.processInput("s_search Test1"));
    }

    @Test
    public void testSearchMultipleWords() {
        assertEquals("OK", uv.processInput("s_add \"Test with multiple words\""));
        assertEquals("0", uv.processInput("s_search \"Test with multiple words\""));
    }

    // @Ignore("To be implemented at a later stage...")
    @Test
    public void testSearchMultipleWordsIncorrect() {
        assertEquals("Error: please specify a correct string", uv.processInput("s_search \"Test with multiple words"));
    }

    @Test
    public void testSearchNotFound() {
        assertEquals("-1", uv.processInput("s_search Test1"));
        assertEquals("OK", uv.processInput("s_add Test1"));
        assertEquals("OK", uv.processInput("s_add Test2"));
        assertEquals("-1", uv.processInput("s_search Test"));
    }

    @Test
    public void testSearchNothing() {
        assertEquals("Error: please specify a string", uv.processInput("s_search"));
    }

    //Prioritetna vrsta
    @Test
    public void testPqAddBasic() {
        assertEquals("OK", uv.processInput("pq_add Test1"));
        assertEquals("OK", uv.processInput("pq_add Test2"));
    }

    @Test
    public void testPqAddNothing() {
        assertEquals("Error: please specify a string", uv.processInput("pq_add"));
    }

    @Test
    public void testPqRemoveFirstBasic() {
        assertEquals("OK", uv.processInput("pq_add Test1"));
        assertEquals("OK", uv.processInput("pq_add Test2"));
        assertEquals("Test2", uv.processInput("pq_remove_first"));
        assertEquals("Test1", uv.processInput("pq_remove_first"));
    }

    @Test
    public void testPqRemoveFirstNothing() {
        assertEquals("Error: priority queue is empty", uv.processInput("pq_remove_first"));
        assertEquals("Error: please specify a string", uv.processInput("pq_add"));
        assertEquals("Error: priority queue is empty", uv.processInput("pq_remove_first"));
    }

    @Test
    public void testPqGetFirstBasic() {
        assertEquals("OK", uv.processInput("pq_add Test1"));
        assertEquals("OK", uv.processInput("pq_add Test2"));
        assertEquals("Test2", uv.processInput("pq_get_first"));
        assertEquals("Test2", uv.processInput("pq_get_first"));
    }

    @Test
    public void testPqGetFirstNothing() {
        assertEquals("Error: priority queue is empty", uv.processInput("pq_get_first"));
        assertEquals("Error: please specify a string", uv.processInput("pq_add"));
        assertEquals("Error: priority queue is empty", uv.processInput("pq_get_first"));
    }

    @Test
    public void testPqSIzeOnEmpty() {
        assertEquals("0", uv.processInput("pq_size"));
    }

    @Test(timeout = 100)
    public void testPqSizeMore() {
        assertEquals("OK", uv.processInput("pq_add Test1"));
        assertEquals("OK", uv.processInput("pq_add Test2"));
        assertEquals("2", uv.processInput("pq_size"));
    }

    @Test
    public void testPqDepthOnEmpty() {
        assertEquals("0", uv.processInput("pq_depth"));
    }

    @Test(timeout = 100)
    public void testPqDepthMore() {
        assertEquals("OK", uv.processInput("pq_add Test1"));
        assertEquals("OK", uv.processInput("pq_add Test2"));
        assertEquals("2", uv.processInput("pq_depth"));
    }

    @Test
    public void testPqIsEmptyOnEmpty() {
        assertEquals("Priority queue is  empty", uv.processInput("pq_isEmpty"));
    }

    @Test
    public void testPqIsEmptyBasic() {
        assertEquals("Priority queue is  empty", uv.processInput("pq_isEmpty"));
        assertEquals("OK", uv.processInput("pq_add Test1"));
        assertEquals("Priority queue is not empty", uv.processInput("pq_isEmpty"));
    }

}
