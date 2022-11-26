package ch.uzh.dice;
import java.util.Map;


public class StraightDiceScoreStrategy implements DiceScoreStrategy {
  
    @Override
    public int calculateDiceScore(Map<Integer, Integer> dice) {
        return 0;
    }

    @Override
    public Map<Integer, Integer> calculateScoredDices(Map<Integer, Integer> dice,Map<Integer, Integer> scoredDices) {
        for (int number : dice.keySet()) {
            scoredDices.put(number,1);
        }
        return scoredDices;
    }

    @Override
    public int calculateRemainingDice(Map<Integer, Integer> scoredDices, int initialDice) {
        int remainingDice = initialDice;
        for (int number : scoredDices.keySet()) {
            if (scoredDices.containsKey(number) == true) {
                remainingDice--;
            } else {
                continue;
            }
        }
        return remainingDice;
    }
        

}
