package ch.uzh;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ConsoleInput {
    private static ConsoleInput instance;

    private final Scanner scanner;
    private boolean closed = false;

    private ConsoleInput(){
        scanner = new Scanner(System.in);
    }

    public static ConsoleInput instance() {
        if (instance == null) {
            instance = new ConsoleInput();
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
    private String readStringInput(int minLength, int maxLength, String message) {
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
    private int readNumberInput(int min, int max, String message) throws IllegalArgumentException{
        assert message != null;
        System.out.print(message);
        int input;
        try {
            input = scanner.nextInt();
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
     * prompt a question to select char from acceptedInputs
     * @param acceptedInputs
     * @param message
     * @pre acceptedInputs.length > 0 && message != null
     * @throws IllegalAccessError
     * @return char input from user
     */
    public char getCharacterInput(Character[] acceptedInputs, String message) throws IllegalAccessError{
        assert acceptedInputs.length > 1 && message != null;

        if (closed) {
            throw new IllegalStateException("ConsoleInput was closed");
        }

        List<Character> acceptedInputsList = Arrays.asList(acceptedInputs);
        Boolean inputAccepted = false;
        char input = acceptedInputs[0];
        while(!inputAccepted) {
            try{
                input = readStringInput(1, 1, message).charAt(0);
                if(!acceptedInputsList.contains(input)) {
                    throw new IllegalArgumentException("Entered character not in accepted inputs.");
                }
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
                output = readNumberInput(min, max, message);
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
                input = readStringInput(minLength, maxLength, message);
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
