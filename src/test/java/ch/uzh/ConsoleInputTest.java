package ch.uzh;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.jupiter.api.Test;

public class ConsoleInputTest {

    void setUserInput(String input) {
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
    }

    @Test
    void testGetNumberInput() {
        setUserInput("5");
        ConsoleInput testInput = ConsoleInput.instance();
        int output = testInput.getNumberInput(0, 6, "");
        assertEquals(5, output);
    }

    @Test
    void testGetNumberInput_belowMin() throws NoSuchMethodException, SecurityException, IllegalAccessException, InvocationTargetException {
        setUserInput("-1");
        ConsoleInput testInput = ConsoleInput.instance();
        Method method = testInput.getClass().getDeclaredMethod("readNumberInput", Integer.TYPE, Integer.TYPE, String.class);
        method.setAccessible(true);
        try {
            method.invoke(testInput, 0, 10, "");
        } catch (InvocationTargetException e) {
            assertEquals(IllegalArgumentException.class, e.getCause().getClass());
            return;
        }
        // no exception was thrown, which means the test must fail
        fail();
    }
    
    @Test
    void testGetNumberInput_aboveMax() throws NoSuchMethodException, SecurityException, IllegalAccessException, InvocationTargetException {
        setUserInput("11");
        ConsoleInput testInput = ConsoleInput.instance();
        Method method = testInput.getClass().getDeclaredMethod("readNumberInput", Integer.TYPE, Integer.TYPE, String.class);
        method.setAccessible(true);
        try {
            method.invoke(testInput, 0, 10, "");
        } catch (InvocationTargetException e) {
            assertEquals(IllegalArgumentException.class, e.getCause().getClass());
            return;
        }
        // no exception was thrown, which means the test must fail
        fail();
    }
}
