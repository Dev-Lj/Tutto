package ch.uzh.turn.turnStrategy;

import ch.uzh.command.Command;
import ch.uzh.command.NullCommand;
import ch.uzh.command.PlusMinusCommand;
import ch.uzh.dice.DiceManager;
import ch.uzh.dice.NormalDiceScoreStrategy;
import ch.uzh.turn.TurnStrategy;

public class PlusMinusTurn implements TurnStrategy {
    private int score = 0;

    @Override
    public Command playTurn() {
        DiceManager dice = new DiceManager(6, new NormalDiceScoreStrategy());

        while (!dice.hadNullTurn() && !dice.isTutto()) {
            dice.rollDice();
        }

        if (dice.isTutto()) {
            return new PlusMinusCommand();
            
        } else {
            return new NullCommand();
        }
    }

    @Override
    public int getScore() {
        return this.score;
    }
    
}
