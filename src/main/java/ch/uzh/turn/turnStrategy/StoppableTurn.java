package ch.uzh.turn.turnStrategy;

import java.util.Arrays;
import java.util.List;

import ch.uzh.dice.DiceManager;
import ch.uzh.dice.NormalDiceScoreStrategy;
import ch.uzh.turn.TurnStrategy;

public abstract class StoppableTurn implements TurnStrategy{
    private int score = 0;
    private boolean isTutto = false;
    private boolean hasLost = false;

    protected final void playStoppableTurn() {
        
        DiceManager aDiceManager = new DiceManager(6, new NormalDiceScoreStrategy());

        while(!aDiceManager.hadNullTurn() && !aDiceManager.isTutto()){
            // Ask user wheter to roll the Dice or end Turn prematurely
            Character[] acceptedInputs = {'R','E'};
            char aUserInput = getUserInput(acceptedInputs,"Decide wheter you want to Roll the dice or End your Turn. (R/E)");

            if(aUserInput == 'E'){return;}

            // Roll and Display Dice
            aDiceManager.rollDice();
            aDiceManager.printScoredDices();
            score = aDiceManager.getScore();
        }

        if (aDiceManager.hadNullTurn()){
            hasLost = true;
        }
        isTutto = aDiceManager.isTutto();

    }

    public char getUserInput(Character[] acceptedInputs, String message){
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

    @Override
    public int getScore() {
        return this.score;
    }

    protected final boolean isTutto() {
        return isTutto;
    }

    protected boolean hasLost() {
        return this.hasLost;
    }

}
