package ch.uzh;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ConsoleInput {
    private static ConsoleInput instance;

    private Scanner scanner;
    private boolean closed = false;

    private ConsoleInput(Scanner scanner){
        this.scanner = scanner;
    }

    public static ConsoleInput instance() {
        if (instance == null) {

            instance = new ConsoleInput(new Scanner(System.in));
        }
        return instance;
    }

    /**
     * read String input from scanner
     * @param minLength including
     * @param maxLength including
     * @param message
     * @throws IllegalArgumentException
     * @pre message != null
     * @return input string
     */
    private String readStringInput(Scanner scanner, int minLength, int maxLength, String message) {
        assert message != null;
        System.out.print(message);
        String input = scanner.nextLine();
        if (input.length() < minLength){
            throw new IllegalArgumentException(String.format("Input length below defined minimum (%d)", minLength));
        } else if(input.length() > maxLength) {
            throw new IllegalArgumentException(String.format("Input length above defined maximum (%d)", maxLength));
        }
        return input;
    }

    /**
     * 
     * @param min
     * @param max
     * @param message
     * @return
     * @throws IllegalArgumentException
     */
    private int readNumberInput(Scanner scanner, int min, int max, String message) throws IllegalArgumentException{
        assert message != null;
        System.out.print(message);
        int input;
        try {
            String stringInput = scanner.nextLine();
            input = Integer.parseInt(stringInput);
        } catch (Exception e) {
            throw new IllegalArgumentException("Please enter a number");
        }
        
        if (input > max) {
            throw new IllegalArgumentException(String.format("Input above defined maximum (%d)", max));
        } else if (input < min) {
            throw new IllegalArgumentException(String.format("Input below defined minimum (%d)", min));
        }
        return input;
    }

    /**
     * Read a character from scanner
     * @param scanner
     * @param acceptedInputsList
     * @param message
     * @pre acceptedInputsList.size() > 0 && message != null && scanner != null;
     * @return
     */
    private char readCharacterInput(Scanner scanner, List<Character> acceptedInputsList, String message) {
        assert acceptedInputsList.size() > 0 && message != null && scanner != null;
        char input = readStringInput(scanner, 1, 1, message).charAt(0);
        if(!acceptedInputsList.contains(input)) {
            throw new IllegalArgumentException("Entered character not in accepted inputs.");
        }
        return input;
    }

    /**
     * prompt a question to select char from acceptedInputs
     * @param acceptedInputs
     * @param message
     * @pre acceptedInputs.length > 0 && message != null
     * @throws IllegalAccessError
     * @return char input from user
     */
    public char getCharacterInput(Character[] acceptedInputs, String message) throws IllegalAccessError{
        assert acceptedInputs.length > 0 && message != null;

        if (closed) {
            throw new IllegalStateException("ConsoleInput was closed");
        }

        List<Character> acceptedInputsList = Arrays.asList(acceptedInputs);
        Boolean inputAccepted = false;
        char input = acceptedInputs[0];
        while(!inputAccepted) {
            try{
                input = readCharacterInput(scanner, acceptedInputsList, message);
                inputAccepted = true;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                scanner.nextLine();
                inputAccepted = false;
            }
        }
        return input;
    }

    /**
     * Get integer input from user
     * @param min including
     * @param max including
     * @param message message before Input
     * @pre min != max && message != null
     * @return Number input from user
     */
    public int getNumberInput(int min, int max, String message) {
        assert min != max && message != null;

        if (closed) {
            throw new IllegalStateException("ConsoleInput was closed");
        }

        boolean hasError = true;
        int output = 0;
        while (hasError) {
            try {
                output = readNumberInput(scanner, min, max, message);
                hasError = false;
            } catch (Exception e) {
                System.out.println("Invalid Input: " + e.getMessage());
                scanner.nextLine();
                hasError = true;
            }
        }
        return output;
    }

    public String getStringInput(int minLength, int maxLength, String message) {
        if (closed) {
            throw new IllegalStateException("ConsoleInput was closed");
        }

        boolean hasError = true;
        String input = "";
        while (hasError) {
            try {
                input = readStringInput(scanner, minLength, maxLength, message);
                hasError = false;
            } catch (Exception e) {
                System.out.println("Invalid Input: " + e.getMessage());
                scanner.nextLine();
                hasError = true;
            }
        }
        return input;
    }

    public void close() {
        this.scanner.close();
        this.closed = true;
    }
}
