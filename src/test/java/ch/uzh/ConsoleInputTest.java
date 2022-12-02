package ch.uzh;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

public class ConsoleInputTest {
    private ConsoleInput testInput = ConsoleInput.instance();

    

    @Test
    void testReadNumberInput() {
        try {
            int result = TestingConsoleInput.readNumberInput(testInput, "3", 0, 5);
            assertEquals(3, result);
        } catch (Throwable e) {
            fail();
        }
    }

    @Test
    void testReadNumberInput_String() {
        try {
            TestingConsoleInput.readNumberInput(testInput, "test", 0, 5);
            fail();
        } catch (Throwable e) {
            assertEquals(IllegalArgumentException.class, e.getClass());
        }
    }

    @Test
    void testReadNumberInput_belowMin() {
        try {
            TestingConsoleInput.readNumberInput(testInput,"-1", 0, 10);
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
            TestingConsoleInput.readNumberInput(testInput,"11", 0, 10);
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
            String result = TestingConsoleInput.readStringInput(testInput,"Test", 0, 100);
            assertEquals("Test", result);
        } catch (Throwable e) {
            fail();
        }
    }

    @Test
    void testReadStringInput_belowMin() {
        try {
            TestingConsoleInput.readStringInput(testInput, "T", 2, 100);
            fail();
        } catch (Throwable e) {
            assertEquals(IllegalArgumentException.class, e.getClass());
        }
    }

    @Test
    void testReadStringInput_aboveMax() {
        try {
            TestingConsoleInput.readStringInput(testInput, "Testing", 2, 5);
            fail();
        } catch (Throwable e) {
            assertEquals(IllegalArgumentException.class, e.getClass());
        }
    }

    @Test
    void testReadCharacterInput() {
        List<Character> accepted = Arrays.asList('A', 'B');
        try {
            char input = TestingConsoleInput.readCharacterInput(testInput, "B", accepted);
            assertEquals('B', input);
        } catch (Throwable e) {
            fail();
        }
    }

    @Test
    void testReadCharacterInput_NotAccepted() {
        List<Character> accepted = Arrays.asList('A', 'B');
        try {
            TestingConsoleInput.readCharacterInput(testInput, "C", accepted);
            fail();
        } catch (Throwable e) {
            assertEquals(IllegalArgumentException.class, e.getClass());
        }
    }

    @Test
    @Timeout (value = 1, unit = TimeUnit.SECONDS)
    void testGetCharacterInput() {
        try {
            ConsoleInput test = TestingConsoleInput.createInstance(new String[]{"A"});
            char input = test.getCharacterInput(new Character[]{'A', 'B'}, "test");
            assertEquals('A', input);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Timeout (value = 1, unit = TimeUnit.SECONDS)
    void testGetNumberInput() {
        try {
            ConsoleInput test = TestingConsoleInput.createInstance(new String[]{"1"});
            int input = test.getNumberInput(0, 2, "test");
            assertEquals(1, input);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Timeout (value = 1, unit = TimeUnit.SECONDS)
    void testGetStringInput() {
        try {
            ConsoleInput test = TestingConsoleInput.createInstance(new String[]{"ABC"});
            String input = test.getStringInput(0, 5, "test");
            assertEquals("ABC", input);
        } catch (Exception e) {
            fail();
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
