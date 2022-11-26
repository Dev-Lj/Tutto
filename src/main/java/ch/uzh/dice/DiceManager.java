package ch.uzh.dice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DiceManager {
    private int score = 0;
    private int remainingDice;
    private final DiceScoreStrategy scoreStrategy;
    private boolean hadNullTurn = false;

    public DiceManager(int availableDice, DiceScoreStrategy scoreStrategy) {
        this.remainingDice = availableDice;
        this.scoreStrategy = scoreStrategy;
    }

    public void rollDice() {
        // TODO roll dice by generating random numbers for available Dice
        List<Integer> dice = new ArrayList<Integer>(Arrays.asList(1,5,3,2));
        int throwScore = scoreStrategy.calculateDiceScore(dice);
        if (throwScore == 0) {
            this.hadNullTurn = true;
        } else {
            score += scoreStrategy.calculateDiceScore(dice);
        }
        this.remainingDice = scoreStrategy.calculateRemainingDice(dice);
    }

    public int getScore() {
        return score;
    }

    public boolean isTutto() {
        return remainingDice == 0;
    }

    public boolean hadNullTurn() {
        return hadNullTurn;
    }

    
}
