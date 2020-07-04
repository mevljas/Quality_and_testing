
import org.junit.*;
import static org.junit.Assert.assertEquals;

public class SeznamiUVTest {

    SeznamiUV uv;

    public SeznamiUVTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

     @Before
    public void setUp() {
        uv = new SeznamiUV();
        uv.addImpl("pv", new PrioritetnaVrsta<BolnikObj>(new BolnikPrimerjajPoImenu()), new PrioritetnaVrsta<BolnikObj>(new BolnikPrimerjajPoEMSO()));
        uv.addImpl("sk", new Sklad<BolnikObj>(new BolnikPrimerjajPoImenu()), new Sklad<BolnikObj>(new BolnikPrimerjajPoEMSO()));
        uv.addImpl("bst", new Bst<BolnikObj>(new BolnikPrimerjajPoImenu()), new Bst<BolnikObj>(new BolnikPrimerjajPoEMSO()));
        uv.addImpl("skmock", new BinKopicaMock<BolnikObj>());
        uv.addImpl("bk", new BinomskaKopica<BolnikObj>(new BolnikPrimerjajPoImenu()), new BinomskaKopica<BolnikObj>(new BolnikPrimerjajPoEMSO()));
    }


    @After
    public void tearDown() {
    }

    @Test
    public void testUseSklad() {
        assertEquals("OK", uv.processInput("use sk"));
        reset();
        testAdd();
        reset();
        testAddNothing();
        reset();
        testRemoveFirst();
        reset();
        testRemoveFirstNothing();
        reset();
        testGet();
        reset();
        testGetNothing();
        reset();
        testResetOnEmpty();
        reset();
        testResetOnFull();
        reset();
        testCountOnEmpty();
        reset();
        testCountOne();
        reset();
        testCountTwo();
        reset();
        testDepthOnEmpty();
        reset();
        testDepthOne();
        reset();
        testDepthTwo();
        reset();
        testSklad(true); // preveri, ali je PS dejansko sklad
    }

    @Test
    public void testUsePrioritetnaVrsta() {
        assertEquals("OK", uv.processInput("use pv"));
        reset();
        testAdd();
        reset();
        testAddNothing();
        reset();
        testRemoveFirst();
        reset();
        testRemoveFirstNothing();
        reset();
        testGet();
        reset();
        testGetNothing();
        reset();
        testResetOnEmpty();
        reset();
        testResetOnFull();
        reset();
        testCountOnEmpty();
        reset();
        testCountOne();
        reset();
        testCountTwo();
        reset();
        testDepthOnEmpty();
        reset();
        testDepthOne();
        reset();
        testDepthTwo();
        reset();
        testPrioritetnaVrsta(true); // preveri, ali je PS dejansko prioritetna vrsta
    }

    @Test
    public void testUseBST() {
        assertEquals("OK", uv.processInput("use bst"));
        reset();
        testAdd();
        reset();
        testAddNothing();
        reset();
        testRemoveFirst();
        reset();
        testRemoveFirstNothing();
        reset();
        testGet();
        reset();
        testGetNothing();
        reset();
        testResetOnEmpty();
        reset();
        testResetOnFull();
        reset();
        testCountOnEmpty();
        reset();
        testCountOne();
        reset();
        testCountTwo();
        reset();
        testDepthOnEmpty();
        reset();
        testDepthOne();
        reset();
        testDepthTwo();
        reset();
        testBst(true); // preveri, ali je PS dejansko BST
    }
    
    @Test
    public void testUseBK() {
        assertEquals("OK", uv.processInput("use bk"));
        reset();
        testAdd();
        reset();
        testAddNothing();
        reset();
        testRemoveFirst();
        reset();
        testRemoveFirstNothing();
        reset();
        testGet();
        reset();
        testGetNothing();
        reset();
        testResetOnEmpty();
        reset();
        testResetOnFull();
        reset();
        testCountOnEmpty();
        reset();
        testCountOne();
        reset();
        testCountTwo();
        reset();
        testDepthOnEmpty();
        reset();
        testDepthBKOne();
        reset();
        testDepthBKTwo();
        reset();
        testBK(true); 
    }

    @Test
    public void testUseAll() {
        testUseSklad();
        testUseBST();
        testUsePrioritetnaVrsta();
        testUseBK();
    }

    @Test
    public void testUseAllMixed() {
        assertEquals("OK", uv.processInput("use sk"));
        testAddTestSequence();
        assertEquals("OK", uv.processInput("use pv"));
        testAddTestSequence();
        assertEquals("OK", uv.processInput("use bst"));
        testAddTestSequence();
        assertEquals("OK", uv.processInput("use bk"));
        testAddTestSequence();
        assertEquals("OK", uv.processInput("use pv"));
        testPrioritetnaVrsta(false);
        assertEquals("OK", uv.processInput("use bst"));
        testBst(false);
        assertEquals("OK", uv.processInput("use sk"));
        testSklad(false);
        assertEquals("OK", uv.processInput("use bk"));
        testBK(false);
    }
    
    /*
    @Test
    public void testAddMemoryFull() {
        assertEquals("OK", uv.processInput("use sk"));
        while (true) {
            uv.processInput("add Ime Priimek 1 3105940500231");
        }
    }
    */
    
    @Test
    public void testAddMemoryFullMock() {
        uv.addImpl("skmock", new BinKopicaMock<BolnikObj>(), new BinKopicaMock<BolnikObj>());
        assertEquals("OK", uv.processInput("use skmock"));
        assertEquals("Error: not enough memory, operation failed",
                uv.processInput("add Ime1 Priimek1 1 3105940500231"));
    }
    
//    @Test 
//    public void testAddMemoryFull(){
//        assertEquals("OK",  uv.processInput("use bk"));
//        int i = 0;
//        while (true){ 
//            assertEquals("Error: not enough memory, operation failed",
//                    uv.processInput("add Ime"+i+" Priimek"+i+" "+i+" 310594050023"+i));
//            
//            i++;
//        }
//    }
////   
//    
//    
    @Test
    public void testSaveMemoryFullMock() {
        assertEquals("OK", uv.processInput("use skmock"));
        assertEquals("Error: not enough memory, operation failed",
        uv.processInput("save Ime1"));
    }



    // *****************
    // POMOŽNE METODE
    // *****************   
    public void reset() {
        uv.processInput("reset");
    }

    public void testAdd() {
        assertEquals("OK", uv.processInput("add Ime1 Priimek1 1 3105940500231"));
        assertEquals("OK", uv.processInput("add Ime2 Priimek2 2 3105940500232"));
    }

    public void testAddNothing() {
        assertEquals("Error: please specify four strings", uv.processInput("add"));
    }

    public void testRemoveFirst() {
        assertEquals("OK", uv.processInput("add Ime Priimek 1 3105940500231"));
        assertEquals("3105940500231 | Priimek, Ime | 1", uv.processInput("removefirst"));
    }

    public void testRemoveFirstNothing() {
        assertEquals("Error: structure is empty", uv.processInput("removefirst"));
        assertEquals("Error: please specify four strings", uv.processInput("add"));
        assertEquals("Error: structure is empty", uv.processInput("removefirst"));
    }

    public void testGet() {
        assertEquals("OK", uv.processInput("add Ime Priimek 1 3105940500231"));
        assertEquals("3105940500231 | Priimek, Ime | 1", uv.processInput("getfirst"));
    }
    

    public void testGetNothing() {
        assertEquals("Error: structure is empty", uv.processInput("getfirst"));
        assertEquals("Error: please specify four strings", uv.processInput("add"));
        assertEquals("Error: structure is empty", uv.processInput("getfirst"));
    }

    public void testResetOnEmpty() {
        assertEquals("OK", uv.processInput("reset"));
    }

    public void testResetOnFull() {
        assertEquals("OK", uv.processInput("add Ime Priimek 1 3105940500231"));
        assertEquals("OK", uv.processInput("reset"));
        assertEquals("Error: structure is empty", uv.processInput("removefirst"));
        assertEquals("0", uv.processInput("count"));
    }

    public void testCountOnEmpty() {
        assertEquals("0", uv.processInput("count"));
    }

    public void testCountOne() {
        assertEquals("OK", uv.processInput("add Ime Priimek 1 3105940500231"));
        assertEquals("1", uv.processInput("count"));
    }

    public void testCountTwo() {
        assertEquals("OK", uv.processInput("add Ime1 Priimek1 1 3105940500231"));
        assertEquals("OK", uv.processInput("add Ime2 Priimek2 2 3105940500232"));
        assertEquals("2", uv.processInput("count"));
    }

    public void testDepthOnEmpty() {
        assertEquals("0", uv.processInput("depth"));
    }

    public void testDepthOne() {
        assertEquals("OK", uv.processInput("add Ime Priimek 1 3105940500231"));
        assertEquals("1", uv.processInput("depth"));
    }

    public void testDepthTwo() {
        assertEquals("OK", uv.processInput("add Ime1 Priimek1 1 3105940500231"));
        assertEquals("OK", uv.processInput("add Ime2 Priimek2 2 3105940500232"));
        assertEquals("2", uv.processInput("depth"));
    }
    
     public void testDepthBKOne() {
        assertEquals("OK", uv.processInput("add Ime Priimek 1 3105940500231"));
        assertEquals("0", uv.processInput("depth"));
    }

    public void testDepthBKTwo() {
        assertEquals("OK", uv.processInput("add Ime1 Priimek1 1 3105940500231"));
        assertEquals("OK", uv.processInput("add Ime2 Priimek2 2 3105940500232"));
        assertEquals("1", uv.processInput("depth"));
    }

    public void testAddTestSequence() {
        assertEquals("OK", uv.processInput("add Ime4 Priimek4 4 3105940500234"));
        assertEquals("OK", uv.processInput("add Ime2 Priimek2 2 3105940500232"));
        assertEquals("OK", uv.processInput("add Ime3 Priimek3 3 3105940500233"));
        assertEquals("OK", uv.processInput("add Ime1 Priimek1 1 3105940500231"));
        assertEquals("OK", uv.processInput("add Ime5 Priimek5 5 3105940500235"));
    }

    public void testSklad(boolean add) {
        if (add) {
            testAddTestSequence();
        }
        assertEquals("3105940500235 | Priimek5, Ime5 | 5", uv.processInput("removefirst"));
        assertEquals("3105940500231 | Priimek1, Ime1 | 1", uv.processInput("removefirst"));
        assertEquals("3105940500233 | Priimek3, Ime3 | 3", uv.processInput("removefirst"));
        assertEquals("3105940500232 | Priimek2, Ime2 | 2", uv.processInput("removefirst"));
        assertEquals("3105940500234 | Priimek4, Ime4 | 4", uv.processInput("removefirst"));
    }

    public void testPrioritetnaVrsta(boolean add) {
        if (add) {
            testAddTestSequence();
        }
        assertEquals("3105940500235 | Priimek5, Ime5 | 5", uv.processInput("removefirst"));
        assertEquals("3105940500234 | Priimek4, Ime4 | 4", uv.processInput("removefirst"));
        assertEquals("3105940500233 | Priimek3, Ime3 | 3", uv.processInput("removefirst"));
        assertEquals("3105940500232 | Priimek2, Ime2 | 2", uv.processInput("removefirst"));
        assertEquals("3105940500231 | Priimek1, Ime1 | 1", uv.processInput("removefirst"));
    }

    public void testBst(boolean add) {
        if (add) {
            testAddTestSequence();
        }
        assertEquals("3105940500234 | Priimek4, Ime4 | 4", uv.processInput("removefirst"));
        assertEquals("3105940500235 | Priimek5, Ime5 | 5", uv.processInput("removefirst"));
        assertEquals("3105940500232 | Priimek2, Ime2 | 2", uv.processInput("removefirst"));
        assertEquals("3105940500233 | Priimek3, Ime3 | 3", uv.processInput("removefirst"));
        assertEquals("3105940500231 | Priimek1, Ime1 | 1", uv.processInput("removefirst"));
    }
    
    public void testBK(boolean add) {
        if (add) {
            testAddTestSequence();
        }
        assertEquals("3105940500235 | Priimek5, Ime5 | 5", uv.processInput("removefirst"));
        assertEquals("3105940500234 | Priimek4, Ime4 | 4", uv.processInput("removefirst"));
        assertEquals("3105940500233 | Priimek3, Ime3 | 3", uv.processInput("removefirst"));
        assertEquals("3105940500232 | Priimek2, Ime2 | 2", uv.processInput("removefirst"));
        assertEquals("3105940500231 | Priimek1, Ime1 | 1", uv.processInput("removefirst"));
    }
    
    
     public void testSizeOnEmpty() {
        assertEquals("0", uv.processInput("size"));
    }

    public void testSizeOne() {
        assertEquals("OK", uv.processInput("add Ime1 Priimek1 1 3105940500231"));
        assertEquals("1", uv.processInput("size"));
    }

    public void testSizeTwo() {
        assertEquals("OK", uv.processInput("add Ime1 Priimek1 1 3105940500231"));
        assertEquals("OK", uv.processInput("add Ime2 Priimek2 2 3105940500232"));
        assertEquals("2", uv.processInput("size"));
    }

    public void testIsEmptyEmpty() {
        assertEquals("Data structure is empty", uv.processInput("is_empty"));
        assertEquals("Error: please specify a string", uv.processInput("add"));
        assertEquals("Data structure is empty", uv.processInput("is_empty"));
    }

    public void testIsEmptyNotEmpty() {
        assertEquals("OK", uv.processInput("add Ime1 Priimek1 1 3105940500231"));
        assertEquals("OK", uv.processInput("add Ime2 Priimek2 2 3105940500232"));
        assertEquals("OK", uv.processInput("add Priimek3, Ime2 - TelefonskaStevilka3"));
        assertEquals("Data structure is not empty", uv.processInput("is_empty"));
    }

    public void testResetAdditional() {
        uv.processInput("add Ime1 Priimek1 1 3105940500231");
        uv.processInput("add Ime2 Priimek2 2 3105940500232");
        uv.processInput("add Ime3 Priimek3 3 3105940500233");
        uv.processInput("add Ime6 Priimek6 6 MESO6");
        uv.processInput("add Ime5 Priimek5 5 3105940500235");
        uv.processInput("add Ime4 Priimek4 4 3105940500234");
        assertEquals(6, uv.processInput("size"));
        uv.processInput("reset");
        assertEquals(0, uv.processInput("size"));
    }

    public void testExistsOne() {
        String name = "Ime1 Priimek1 1 3105940500231";
        uv.processInput("add " + name);
        assertEquals("Element exists in data structure", uv.processInput("exists " + name));
    }

    public void testExistsTwo() {
        String name = "Ime1 Priimek1 1 3105940500231";
        uv.processInput("add " + name);
        uv.processInput("add Ime2 Priimek2 2 3105940500232");
        assertEquals("Element exists in data structure", uv.processInput("exists " + name));
    }

    public void testExistsMore() {
        String name = "Ime1 Priimek1 1 3105940500231";
        uv.processInput("add Ime3 Priimek3 3 3105940500233");
        uv.processInput("add " + name);
        uv.processInput("add Ime2 Priimek2 2 3105940500232");
        uv.processInput("add Ime6 Priimek6 6 MESO6");
        assertEquals("Element exists in data structure", uv.processInput("exists " + name));
    }

    public void testExistsEmpty() {
        assertEquals("Data structure is empty", uv.processInput("exists Ime1 Priimek1 1 3105940500231"));
    }

    public void testExistsFalse() {
        uv.processInput("Ime1 Priimek1 1 3105940500231");
        assertEquals("Element doesn't exist in data structure", uv.processInput("exists Ime2 Priimek2 2 3105940500232"));
    }

    public void testExistsWrongArgument() {
        assertEquals("Please specify a string", uv.processInput("exists"));
    }

    public void testRemoveOne() {
        String name = "Ime1 Priimek1 1 3105940500231";
        uv.processInput("add " + name);
        assertEquals(name, uv.processInput("remove " + name));
    }

    public void testRemoveTwo() {
        String name = "Ime1 Priimek1 1 3105940500231";
        uv.processInput("add " + name);
        uv.processInput("add Ime2 Priimek2 2 3105940500232");
        assertEquals(name, uv.processInput("remove " + name));
    }

    public void testRemoveMore() {
        String name = "Ime1 Priimek1 1 3105940500231";
        uv.processInput("add Ime3 Priimek3 3 3105940500233");
        uv.processInput("add " + name);
        uv.processInput("add Ime2 Priimek2 2 3105940500232");
        uv.processInput("add Ime6 Priimek6 6 MESO6");
        assertEquals(name, uv.processInput("remove " + name));
    }

    public void testRemoveEmpty() {
        assertEquals("Data structure is empty", uv.processInput("remove Ime1 Priimek1 1 3105940500231"));
    }

    public void testRemoveFalse() {
        uv.processInput("add Ime1 Priimek1 1 3105940500231");
        assertEquals("Error: element does not exist in data structure", uv.processInput("remove Ime2 Priimek2 2 3105940500232"));
    }

    public void testRemoveWrongArgument() {
        assertEquals("Error: please specify a string", uv.processInput("remove"));
    }


    public void testResetOne() {
        uv.processInput("add Ime4 Priimek4 4 3105940500234");
        uv.processInput("reset");
        assertEquals(0, uv.processInput("size"));
    }

    public void testResetMultiple() {
        testAddTestSequence();
        uv.processInput("reset");
        assertEquals(0, uv.processInput("size"));
    }

    public void testResetEmpty() {
        uv.processInput("reset");
        assertEquals(0, uv.processInput("size"));
    }

    public void testAsListOne() {
        uv.processInput("add Ime1 Priimek1 1 3105940500231");
        assertEquals("Ime1 Priimek1 1 3105940500231 ", uv.processInput("asList"));
    }


    public void testAsListEmpty() {
        assertEquals("", uv.processInput("asList"));
    }


    @Test
    public void testBstDuplicate() {
        uv.processInput("use bst");
        uv.processInput("add Ime1 Priimek1 1 3105940500231");
        assertEquals("Patient already exists", uv.processInput("add Ime1 Priimek1 1 3105940500231"));
    }
    
    
    @Test
    public void testNoStrucutre() {
        assertEquals("Error: please specify a data structure (use {pv|sk|bst|bk})", uv.processInput("1"));
    }
    
    
    @Test
    public void testRemoveName() {
        uv.processInput("use bk");
        assertEquals("OK", uv.processInput("add Ime1 Priimek1 1 3105940500231"));
        assertEquals("OK", uv.processInput("remove Ime1 Priimek1"));
    }
    
    @Test
    public void testRemoveEMSO() {
        uv.processInput("use bk");
        assertEquals("OK", uv.processInput("add Ime1 Priimek1 1 3105940500231"));
        assertEquals("OK", uv.processInput("remove 3105940500231"));
    }
    
    @Test
    public void testRemoveWrong() {
        uv.processInput("use bk");
        assertEquals("OK", uv.processInput("add Ime1 Priimek1 1 3105940500231"));
        assertEquals("Invalid input data", uv.processInput("remove 1 1 1 1 1 1"));
    }
    
    @Test
    public void testRemoveEmpty2() {
        uv.processInput("use bk");
        assertEquals("Patient does not exist", uv.processInput("remove 3105940500231"));
    }
    
    @Test
    public void testPrint() {
        uv.processInput("use bk");
        uv.processInput("print");
    }
    
    
    @Test
    public void testSave() {
        uv.processInput("use bk");
        assertEquals("OK", uv.processInput("add Ime1 Priimek1 1 3105940500231"));
        assertEquals("OK", uv.processInput("save test"));
    }
    
    @Test
    public void testSaveWrong() {
        uv.processInput("use bk");
        assertEquals("OK", uv.processInput("add Ime1 Priimek1 1 3105940500231"));
        assertEquals("Error: please specify a file name", uv.processInput("save test 2"));
    }
    
    
    @Test
    public void testRestore() {
        uv.processInput("use bk");
        assertEquals("OK", uv.processInput("add Ime1 Priimek1 1 3105940500231"));
        assertEquals("OK", uv.processInput("save test"));
        assertEquals("OK", uv.processInput("restore test"));
    }
    
    @Test
    public void testRestoreWrong() {
        uv.processInput("use bk");
        assertEquals("OK", uv.processInput("add Ime1 Priimek1 1 3105940500231"));
        assertEquals("Error: please specify a file name", uv.processInput("restore test 2"));
    }
    
    @Test
    public void testExit() {
        uv.processInput("use bk");
        assertEquals("OK", uv.processInput("add Ime1 Priimek1 1 3105940500231"));
        assertEquals("Have a nice day.", uv.processInput("exit"));
    }
    
    
    @Test
    public void testSearchName() {
        uv.processInput("use bk");
        assertEquals("OK", uv.processInput("add Ime1 Priimek1 1 3105940500231"));
        assertEquals("3105940500231 | Priimek1, Ime1 | 1", uv.processInput("search Ime1 Priimek1"));
    }
    
    @Test
    public void testSearchEMSO() {
        uv.processInput("use bk");
        assertEquals("OK", uv.processInput("add Ime1 Priimek1 1 3105940500231"));
        assertEquals("3105940500231 | Priimek1, Ime1 | 1", uv.processInput("search 3105940500231"));
    }
    
    @Test
    public void testSearchWrong() {
        uv.processInput("use bk");
        assertEquals("OK", uv.processInput("add Ime1 Priimek1 1 3105940500231"));
        assertEquals("Invalid input data", uv.processInput("search 1 1 1 1 1 1"));
    }
    
    @Test
    public void testSearchEmpty2() {
        uv.processInput("use bk");
        assertEquals("Patient does not exist", uv.processInput("search 3105940500231"));
    }
    
    
    @Test
    public void testInvalidCommand() {
        uv.processInput("use bk");
        assertEquals("Error: invalid command", uv.processInput("test"));
    }
    


    
    
    
    
    
  
}
