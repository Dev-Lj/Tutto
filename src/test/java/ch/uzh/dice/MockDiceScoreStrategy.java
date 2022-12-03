package ch.uzh.dice;

import java.util.Map;

public class MockDiceScoreStrategy implements DiceScoreStrategy {


    public int calculateDiceScore(Map<Integer, Integer> dice) {
        return  1;
    }
    
    // TODO isn't this the same as in Mocked class? Mocks shoudl not add complexity, keep it simple
    public Map<Integer, Integer> calculateScoredDices(Map<Integer, Integer> dice, Map<Integer, Integer> scoredDices) {
        for (int number : dice.keySet()) {
            if (number == 1 || number == 5) {
                if (scoredDices.containsKey(number) == true) {
                    scoredDices.put(number, dice.get(number) + scoredDices.get(number));
                } else {
                    scoredDices.put(number, dice.get(number));
                }
            }
            else if((dice.get(number) / 3) == 1 )
            {
                if (scoredDices.containsKey(number) == true) {
                    scoredDices.put(number, 3 + scoredDices.get(number));
                } else {
                    scoredDices.put(number, 3);
                }
            }
            else if ((dice.get(number) / 3) == 2) {
                scoredDices.put(number, 6);
            }
            else {
            }
        }
        return scoredDices;
    }

            

    public int calculateRemainingDice(Map<Integer, Integer> scoredDices, int initialDice) {
        int remainingDices = initialDice;
        for (int number : scoredDices.keySet()) {
            remainingDices -= scoredDices.get(number);
        }
        return remainingDices;
    }
        




    
}
