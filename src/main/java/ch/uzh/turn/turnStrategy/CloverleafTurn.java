package ch.uzh.turn.turnStrategy;

import ch.uzh.ConsoleInput;
import ch.uzh.command.Command;
import ch.uzh.command.EndGameCommand;
import ch.uzh.command.NullCommand;
import ch.uzh.dice.DiceManager;
import ch.uzh.dice.NormalDiceScoreStrategy;
import ch.uzh.turn.PlayerTurn;
import ch.uzh.turn.TurnStrategy;

public class CloverleafTurn implements TurnStrategy{

    @Override
    public Command playTurn(PlayerTurn currentTurn) {
        for (int i=0; i<2; i++){
            if (currentTurn.isActive()){
                DiceManager aDiceManager = new DiceManager(6, new NormalDiceScoreStrategy());
                aDiceManager = turnLoop(aDiceManager, ConsoleInput.instance());
                computeTurn(currentTurn, aDiceManager);
            }
            
                        
        }
        return evaluateTurn(currentTurn);
    }

    private Command evaluateTurn(PlayerTurn currentTurn){
        if (currentTurn.isActive()){
            return new EndGameCommand();
        } else {
            return new NullCommand();
        }
    }

    private void computeTurn(PlayerTurn currentTurn, DiceManager aDiceManager){
        if (aDiceManager.hadNullTurn()){
            currentTurn.endTurn();
        }
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
        return 0;
    }
    
}
