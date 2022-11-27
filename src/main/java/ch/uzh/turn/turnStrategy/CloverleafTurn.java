package ch.uzh.turn.turnStrategy;

import java.util.Arrays;
import java.util.List;

import ch.uzh.command.Command;
import ch.uzh.command.EndGameCommand;
import ch.uzh.command.LooseTurnCommand;
import ch.uzh.dice.DiceManager;
import ch.uzh.dice.NormalDiceScoreStrategy;
import ch.uzh.turn.TurnStrategy;

public class CloverleafTurn implements TurnStrategy{

    @Override
    public Command playTurn() {
        for (int i=0; i<2; i++){
            DiceManager aDiceManager = new DiceManager(6, new NormalDiceScoreStrategy());
            while(!aDiceManager.hadNullTurn() && !aDiceManager.isTutto()){
                // Ask user to roll the Dice
                Character[] acceptedInputs = {'R'};
                getUserInput(acceptedInputs,"Please enter R to roll the dice. You're not allowed to stop your turn.");

                // Roll and Display Dice
                aDiceManager.rollDice();
                aDiceManager.printScoredDices();
            }
            if (aDiceManager.hadNullTurn()){
                return new LooseTurnCommand();
            }            
        }
        return new EndGameCommand();
    }


    @Override
    public int getScore() {
        return 0;
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
