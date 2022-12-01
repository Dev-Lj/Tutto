package ch.uzh;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

public class ConsoleInputTest {

    private ConsoleInput testInput = ConsoleInput.instance();

    private int readNumberInput(String input, int min, int max) throws Throwable {
        Method method = testInput.getClass().getDeclaredMethod("readNumberInput", Scanner.class, Integer.TYPE, Integer.TYPE, String.class);
        method.setAccessible(true);
        try {
            return (int) method.invoke(testInput, new Scanner(input), min, max, "");
        } catch (InvocationTargetException e) {
            throw e.getCause();
        }
    }

    private String readStringInput(String input, int minLength, int maxLength) throws Throwable {
        Method method = testInput.getClass().getDeclaredMethod("readStringInput", Scanner.class, Integer.TYPE, Integer.TYPE, String.class);
        method.setAccessible(true);
        try {
            return (String) method.invoke(testInput, new Scanner(input), minLength, maxLength, "");
        } catch (InvocationTargetException e) {
            throw e.getCause();
        }
    }

    @Test
    void testReadNumberInput() {
        try {
            int result = readNumberInput("3", 0, 5);
            assertEquals(3, result);
        } catch (Throwable e) {
            fail();
        }
    }

    @Test
    void testReadNumberInput_String() {
        try {
            readNumberInput("test", 0, 5);
            fail();
        } catch (Throwable e) {
            assertEquals(IllegalArgumentException.class, e.getClass());
        }
    }

    @Test
    void testReadNumberInput_belowMin() {
        try {
            readNumberInput("-1", 0, 10);
        } catch (Throwable e) {
            assertEquals(IllegalArgumentException.class, e.getClass());
            return;
        }
        // no or wrong exception was thrown, which means the test must fail
        fail();
    }
    
    @Test
    void testReadNumberInput_aboveMax() throws NoSuchMethodException, SecurityException, IllegalAccessException, InvocationTargetException {

        try {
            readNumberInput("11", 0, 10);
        } catch (Throwable e) {
            assertEquals(IllegalArgumentException.class, e.getClass());
            return;
        }
        // no or wrong exception was thrown, which means the test must fail
        fail();
    }

    @Test
    void testReadStringInput() {
        try {
            String result = readStringInput("Test", 0, 100);
            assertEquals("Test", result);
        } catch (Throwable e) {
            fail();
        }
    }

    @Test
    void testReadStringInput_belowMin() {
        try {
            readStringInput("T", 2, 100);
            fail();
        } catch (Throwable e) {
            assertEquals(IllegalArgumentException.class, e.getClass());
        }
    }

    @Test
    void testReadStringInput_aboveMax() {
        try {
            readStringInput("Testing", 2, 5);
            fail();
        } catch (Throwable e) {
            assertEquals(IllegalArgumentException.class, e.getClass());
        }
    }

    @Test
    void testGetCharacterInput_closed() {
        testInput.close();
        try {
            testInput.getCharacterInput(new Character[]{'T', 'N'}, "Test");
            fail();
        } catch (Exception e) {
            assertEquals(IllegalStateException.class, e.getClass());
        }
    }

    @Test
    void testGetNumberInput_closed() {
        testInput.close();
        try {
            testInput.getNumberInput(0,1, "Test");
            fail();
        } catch (Exception e) {
            assertEquals(IllegalStateException.class, e.getClass());
        }
    }

    @Test
    void testGetStringInput_closed() {
        testInput.close();
        try {
            testInput.getStringInput(0,1, "Test");
            fail();
        } catch (Exception e) {
            assertEquals(IllegalStateException.class, e.getClass());
        }
    }


}
