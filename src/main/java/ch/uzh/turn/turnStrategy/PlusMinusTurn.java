package ch.uzh.turn.turnStrategy;

import java.util.Arrays;
import java.util.List;

import ch.uzh.command.Command;
import ch.uzh.command.NullCommand;
import ch.uzh.command.PlusMinusCommand;
import ch.uzh.dice.DiceManager;
import ch.uzh.dice.NormalDiceScoreStrategy;
import ch.uzh.turn.PlayerTurn;
import ch.uzh.turn.TurnStrategy;

public class PlusMinusTurn implements TurnStrategy {
    private int score;

    @Override
    public Command playTurn(PlayerTurn currentTurn) {
        DiceManager aDiceManager = new DiceManager(6, new NormalDiceScoreStrategy());

        while(!aDiceManager.hadNullTurn() && !aDiceManager.isTutto()){
            // Ask user to roll the Dice
            Character[] acceptedInputs = {'R'};
            getUserInput(acceptedInputs,"Please enter R to roll the dice. You're not allowed to stop your turn.");

            // Roll and Display Dice
            aDiceManager.rollDice();
            aDiceManager.printScoredDices();
        }

        if (aDiceManager.isTutto()) {
            score = 1000;
            return new PlusMinusCommand();
        } else {
            score = 0;
            return new NullCommand();
        }
    }

    @Override
    public int getScore() {
        return this.score;
    }
}
