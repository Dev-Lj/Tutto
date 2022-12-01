package ch.uzh.turn.turnStrategy;

import ch.uzh.ConsoleInput;
import ch.uzh.command.Command;
import ch.uzh.command.NullCommand;
import ch.uzh.dice.DiceManager;
import ch.uzh.dice.StraightDiceScoreStrategy;
import ch.uzh.turn.PlayerTurn;
import ch.uzh.turn.TurnStrategy;

public class StraightTurn implements TurnStrategy{
    private int score;
    private DiceManager aDiceManager;

    public StraightTurn(){
        this.score = 0;
        this.aDiceManager = new DiceManager(6, new StraightDiceScoreStrategy());
    };

    @Override
    public Command playTurn(PlayerTurn currentTurn) {       
        aDiceManager = turnLoop(aDiceManager, ConsoleInput.instance());
        return evaluateTurn(aDiceManager, currentTurn); //evaluateTurn returns Command
    }

    private DiceManager turnLoop(DiceManager aDiceManager, ConsoleInput aConsoleInput){
        while(!aDiceManager.hadNullTurn() && !aDiceManager.isTutto()){
            // Ask user to roll the Dice
            Character[] acceptedInputs = {'R'};
            aConsoleInput.getCharacterInput(acceptedInputs,"Please enter R to roll the dice. You're not allowed to stop your turn.");

            // Roll and Display Dice
            aDiceManager.rollDice();
            aDiceManager.printScoredDices();
            
        }
        return aDiceManager;
    }

    private Command evaluateTurn(DiceManager aDiceManager, PlayerTurn currentTurn){
        if (aDiceManager.isTutto()){
            score = 2000;
        } else {
            score = 0;
            currentTurn.looseTurn();
        }
        return new NullCommand();
    }

    @Override
    public int getScore() {
        return score;
    }
    
}
