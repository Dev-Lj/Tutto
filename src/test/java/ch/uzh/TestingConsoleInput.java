package ch.uzh;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Scanner;

public class TestingConsoleInput {

    public static ConsoleInput createInstance(String[] inputs) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        Constructor<ConsoleInput> constructor = ConsoleInput.class.getDeclaredConstructor(Scanner.class);
        constructor.setAccessible(true);
        String inputString = String.join("\n", inputs);
        ConsoleInput input = constructor.newInstance(new Scanner(inputString));
        return input;
    }
    
    public static int readNumberInput(ConsoleInput testInput, String input, int min, int max) throws Throwable {
        Method method = testInput.getClass().getDeclaredMethod("readNumberInput", Scanner.class, Integer.TYPE, Integer.TYPE, String.class);
        method.setAccessible(true);
        try {
            return (int) method.invoke(testInput, new Scanner(input), min, max, "");
        } catch (InvocationTargetException e) {
            throw e.getCause();
        }
    }

    public static String readStringInput(ConsoleInput testInput, String input, int minLength, int maxLength) throws Throwable {
        Method method = testInput.getClass().getDeclaredMethod("readStringInput", Scanner.class, Integer.TYPE, Integer.TYPE, String.class);
        method.setAccessible(true);
        try {
            return (String) method.invoke(testInput, new Scanner(input), minLength, maxLength, "");
        } catch (InvocationTargetException e) {
            throw e.getCause();
        }
    }

    public static char readCharacterInput(ConsoleInput testInput, String input, List<Character> acceptedInputs) throws Throwable {
        Method method = testInput.getClass().getDeclaredMethod("readCharacterInput", Scanner.class, List.class, String.class);
        method.setAccessible(true);
        try {
            return (char) method.invoke(testInput, new Scanner(input), acceptedInputs, "");
        } catch (InvocationTargetException e) {
            throw e.getCause();
        }
    }
}
