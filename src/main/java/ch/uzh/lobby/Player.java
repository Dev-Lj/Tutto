package ch.uzh.lobby;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Player implements Comparable<Player> {
    private int score;
    private final String name;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void reduceScore(int reduction) {
        this.score = Math.max(this.score - reduction, 0);
    }

    public void addScore(int addition) {
        this.score += addition;
    }

    public static char getCharacterInput(Character[] acceptedInputs, String message) {
        List<Character> acceptedInputsList = Arrays.asList(acceptedInputs);
        Boolean inputAccepted;
        String input = "";
        do {
            try{
                System.out.println(message);
                input = System.console().readLine();
                if (input.length() != 1){
                    throw new IllegalArgumentException("Please only enter one character.");
                }
                if (!acceptedInputsList.contains(input.charAt(0))){
                    throw new IllegalArgumentException("The character you entered wasn't one of the possible options.");
                }
                inputAccepted = true;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Let's try again...\n");
                inputAccepted = false;
            }
        } while(!inputAccepted);

        return (char) input.charAt(0);
    }

    /**
     * Get integer input from user
     * @param scanner
     * @param min including
     * @param max including
     * @param message
     * @return Number input from user
     * @pre scanner != null && min != max && message != null
     */
    public static int getNumberInput(Scanner scanner, int min, int max, String message) {
        assert scanner != null && min != max && message != null;
        boolean hasError = true;
        int output = 0;
        while (hasError) {
            try {
                System.out.print(message);
                output = scanner.nextInt();
                if (output > max) {
                    throw new IllegalArgumentException(String.format("Input above defined maximum (%d)", max));
                } else if (output < min) {
                    throw new IllegalArgumentException(String.format("Input below defined minimum (%d)", min));
                }
                hasError = false;
            } catch (Exception e) {
                // TODO avoid null output
                System.out.println("Invalid Input: " + e.getMessage());
                scanner.nextLine();
                hasError = true;
            }
        }
        return output;
    }

    @Override
    public int compareTo(Player o) {
        return name.compareTo(o.getName());
    }

    public static Comparator<Player> RankComparator = new Comparator<Player>() {
        @Override
        public int compare(Player o1, Player o2) {
            return o2.getScore() - o1.getScore();
        }
    };
}
