package ch.uzh.turn.turnStrategy;

import ch.uzh.ConsoleInput;
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
            
            aDiceManager = turnLoop(aDiceManager, ConsoleInput.instance());
            score += aDiceManager.getScore();
            isTutto = aDiceManager.isTutto();
            
        } while(isTutto);
        currentTurn.endTurn();
        return new NullCommand();
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

    @Override
    public int getScore() {
        return score;
    }
    
}
