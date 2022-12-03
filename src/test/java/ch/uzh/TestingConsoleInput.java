package ch.uzh;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Scanner;

public class TestingConsoleInput {

    /**
     * Creates instance of ConsoleInput with predefined input.
     * @param inputs each input represents one Line of input
     * @return ConsoleInput instance
     */
    public static ConsoleInput createFakeScannerInstance(String[] inputs) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        Constructor<ConsoleInput> constructor = ConsoleInput.class.getDeclaredConstructor(Scanner.class);
        constructor.setAccessible(true);
        String inputString = String.join("\n", inputs);
        ConsoleInput input = constructor.newInstance(new Scanner(inputString));
        return input;
    }

    /**
     * Creates instance of ConsoleInput with an empty scanner source.
     * Should be used for testing in order to avoid tampering with System.in
     * 
     * @param inputs each input represents one Line of input
     * @return ConsoleInput instance
     */
    public static ConsoleInput createNullSourceScannerInstance() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        Constructor<ConsoleInput> constructor = ConsoleInput.class.getDeclaredConstructor(Scanner.class);
        constructor.setAccessible(true);
        ConsoleInput input = constructor.newInstance(new Scanner(""));
        return input;
    }
    
    /**
     * Makes private method readNumberInput accessible
     * @param testInput ConsoleInput from which you wish to access method readNumberInput
     * @param input Fake user input
     * @param min
     * @param max
     * @return Number input
     * @throws Throwable
     */
    public static int readNumberInput(ConsoleInput testInput, String input, int min, int max) throws Throwable {
        Method method = testInput.getClass().getDeclaredMethod("readNumberInput", Scanner.class, Integer.TYPE, Integer.TYPE, String.class);
        method.setAccessible(true);
        try {
            return (int) method.invoke(testInput, new Scanner(input), min, max, "");
        } catch (InvocationTargetException e) {
            throw e.getCause();
        }
    }

    /**
     * Makes private method readStringInput accessible
     * @param testInput ConsoleInput from which you wish to access method readStringInput
     * @param input Fake user input
     * @param minLength
     * @param maxLength
     * @return String input
     * @throws Throwable
     */
    public static String readStringInput(ConsoleInput testInput, String input, int minLength, int maxLength) throws Throwable {
        Method method = testInput.getClass().getDeclaredMethod("readStringInput", Scanner.class, Integer.TYPE, Integer.TYPE, String.class);
        method.setAccessible(true);
        try {
            return (String) method.invoke(testInput, new Scanner(input), minLength, maxLength, "");
        } catch (InvocationTargetException e) {
            throw e.getCause();
        }
    }

    /**
     * Makes private method readCharacterInput accessible
     * @param testInput ConsoleInput from which you wish to access method readNumberInput
     * @param input
     * @param acceptedInputs
     * @return char input
     * @throws Throwable
     */
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
