package ch.uzh.dice;
import java.util.Map;

public interface DiceScoreStrategy {
    public int calculateDiceScore(Map<Integer, Integer> dice);
    
    public Map<Integer, Integer> calculateScoredDices(Map<Integer, Integer> dice,
            Map<Integer, Integer> scoredDices);

    public int calculateRemainingDice(Map<Integer, Integer> scoredDices, int initialDice);
}
