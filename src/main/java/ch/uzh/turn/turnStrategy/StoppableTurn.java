package ch.uzh.turn.turnStrategy;

import ch.uzh.ConsoleInput;
import ch.uzh.dice.DiceManager;
import ch.uzh.dice.NormalDiceScoreStrategy;
import ch.uzh.turn.TurnStrategy;

public abstract class StoppableTurn implements TurnStrategy{
    private int score = 0;
    private boolean isTutto = false;
    private boolean hasLost = false;

    protected final void playStoppableTurn() {
        DiceManager aDiceManager = new DiceManager(6, new NormalDiceScoreStrategy());
        aDiceManager = turnLoop(aDiceManager, ConsoleInput.instance());
        computeTurn(aDiceManager);
    }

    private void computeTurn(DiceManager aDiceManager){
        if (aDiceManager.hadNullTurn()){
            hasLost = true;
        }
        isTutto = aDiceManager.isTutto();
    }

    private DiceManager turnLoop(DiceManager aDiceManager, ConsoleInput aConsoleInput){
        while(!aDiceManager.hadNullTurn() && !aDiceManager.isTutto()){
            // Ask user wheter to roll the Dice or end Turn prematurely
            Character[] acceptedInputs = {'R','E'};
            char aUserInput = aConsoleInput.getCharacterInput(acceptedInputs,"Decide wheter you want to Roll the dice or End your Turn. (R/E)");

            if(aUserInput == 'E'){return aDiceManager;}

            // Roll and Display Dice
            aDiceManager.rollDice();
            aDiceManager.printScoredDices();
            score = aDiceManager.getScore();
        }
        return aDiceManager;
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
