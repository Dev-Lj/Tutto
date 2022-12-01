package ch.uzh.turn.turnStrategy;

import java.util.Arrays;
import java.util.List;

import ch.uzh.command.Command;
import ch.uzh.command.NullCommand;
import ch.uzh.dice.DiceManager;
import ch.uzh.dice.NormalDiceScoreStrategy;
import ch.uzh.turn.PlayerTurn;
import ch.uzh.turn.TurnStrategy;

public class FireworksTurn implements TurnStrategy{
    private int score;

    @Override
    public Command playTurn(PlayerTurn currentTurn) {
        boolean isTutto;
        do{
            DiceManager aDiceManager = new DiceManager(6, new NormalDiceScoreStrategy());
            int tempScore = 0;
            
            while(!aDiceManager.hadNullTurn() && !aDiceManager.isTutto()){
                // Ask user to roll the Dice
                Character[] acceptedInputs = {'R'};
                getUserInput(acceptedInputs,"Please enter R to roll the dice. You're not allowed to stop your turn.");

                // Roll and Display Dice
                aDiceManager.rollDice();
                aDiceManager.printScoredDices();
                tempScore = aDiceManager.getScore();
            }
            score += tempScore;
            isTutto = aDiceManager.isTutto();
            
        } while(isTutto);
        currentTurn.endTurn();
        return new NullCommand();
    }

    @Override
    public int getScore() {
        return score;
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
    
}
