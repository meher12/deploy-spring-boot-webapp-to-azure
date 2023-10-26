package com.guru2azure.junit.test;

import com.guru2azure.tools.Utils;
import org.junit.jupiter.api.*;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UtilsTest {

    Utils demoUtils;


    @BeforeEach
    void stepBeforeEach() {
        demoUtils = new Utils();
        System.out.println("@BeforeEach executes before the execution of each test method to create an instance of Utils class");
    }
    /*
    @AfterEach
    void tearDownAfterEach(){
        System.out.println("Running @AfterEach");
        System.out.println();
    }

    @BeforeAll
    static void setupBeforeEachClass(){
        System.out.println("BeforeAll, executed only once before all test methods");
        System.out.println();
    }

    @AfterAll
    static void setupAfterEachClass(){
        System.out.println("AfterAll, executed only once after all test methods");
        System.out.println();
    }*/

    @Test
    @Order(1)
    @DisplayName("Equals and Not Equals")
    void testEqualsAndNotEquals() {
        assertEquals(6, demoUtils.add(2, 4), "2 + 4 must be 6");
        assertNotEquals(6, demoUtils.add(5, 8), "5 + 8 must  not be 6");
    }

    @Test
    @DisplayName("Null and Not Null")
    @Order(2)
    void testNullAndNotNull() {
        String str1 = null;
        String str2 = "Guru to azure";
        assertNull(demoUtils.checkNull(str1), "Object should be null");
        assertNotNull(demoUtils.checkNull(str2), "Object should be null");
    }

    @Test
    @DisplayName("Same and Not Same")
    @Order(3)
    void testSameAndNotSame() {
        String str = "Guru to azure";
        assertSame(demoUtils.getBlogTitle(), demoUtils.getBlogTitleDuplicate(), "Object should refer to same object");
        assertNotSame(str, demoUtils.getBlogTitle(), "Object should not refer to same object");
    }

    @Test
    @DisplayName("True and False")
    @Order(4)
    void testTrueFalse() {
        int gradeOne = 10;
        int gradeTwo = 5;
        assertTrue(demoUtils.isGreater(gradeOne, gradeTwo), "This should return true");
        assertFalse(demoUtils.isGreater(gradeTwo, gradeOne), "This should return false");
    }

    @Test
    @DisplayName("Iterable Equals")
    void testIterableEquals(){
        List<String> theList = List.of("guru", "2", "azure");
        assertIterableEquals(theList, demoUtils.getBlogInList(), "Expented list should be same as actual list");
    }

    @Test
    @DisplayName("Lines Match")
    void testLineMatch(){
        List<String> theList = List.of("guru", "2", "azure");
        assertLinesMatch(theList, demoUtils.getBlogInList(), "Lines should match");
    }

    @DisplayName("Throws and Does Not Throw")
    @Test
    void testThrowsAndDoesNotThrow(){
        assertThrows(Exception.class, ()-> {demoUtils.throwException(-1);},"should throw exception");
        assertDoesNotThrow( ()-> {demoUtils.throwException(7);},"should not throw exception");
    }

    @DisplayName("Timeout")
    @Test
    void testTimeout(){
        assertTimeoutPreemptively(Duration.ofSeconds(3),()-> {demoUtils.checkTimeout();},
                "Method should execute in 3 seconds");

    }

}
