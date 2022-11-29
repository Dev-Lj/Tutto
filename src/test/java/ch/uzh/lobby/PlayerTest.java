package ch.uzh.lobby;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.jupiter.api.Test;
import java.util.Scanner;

public class PlayerTest {
    @Test
    void testAddScore() {
        // TODO
    }

    void setUserInput(String input) {
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
    }

    @Test
    void testGetNumberInput() {
        setUserInput("5");
        int output = Player.getNumberInput(0, 06, "");
        assertEquals(5, output);
    }

    @Test
    void testGetNumberInput_belowMin() throws NoSuchMethodException, SecurityException, IllegalAccessException, InvocationTargetException {
        setUserInput("-1");
        Method method = Player.class.getDeclaredMethod("getNumberInput", Scanner.class, Integer.TYPE, Integer.TYPE, String.class);
        method.setAccessible(true);
        try {
            method.invoke(null, new Scanner(System.in), 0, 10, "");
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
        Method method = Player.class.getDeclaredMethod("getNumberInput", Scanner.class, Integer.TYPE, Integer.TYPE, String.class);
        method.setAccessible(true);
        try {
            method.invoke(null, new Scanner(System.in), 0, 10, "");
        } catch (InvocationTargetException e) {
            assertEquals(IllegalArgumentException.class, e.getCause().getClass());
            return;
        }
        // no exception was thrown, which means the test must fail
        fail();
    }

    @Test
    void testReduceScore() {
        // TODO
    }
}
