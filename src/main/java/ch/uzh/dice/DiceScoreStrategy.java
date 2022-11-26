package ch.uzh.dice;

import java.util.List;

public interface DiceScoreStrategy {
    public int calculateDiceScore(List<Integer> dice);
    public int calculateRemainingDice(List<Integer> dice);
}
