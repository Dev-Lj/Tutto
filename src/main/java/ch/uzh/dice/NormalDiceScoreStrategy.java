package ch.uzh.dice;

import java.util.Map;

/**
 * NormalDiceScoreStrategy
 */
public class NormalDiceScoreStrategy implements DiceScoreStrategy{

    @Override
    public int calculateDiceScore(Map<Integer, Integer> dice) {
        int score = 0;
        int frequencyNumber;
        for (int number : dice.keySet()) {
            frequencyNumber = dice.get(number);
            if (frequencyNumber >= 3) {
                if (number != 1) {
                    score += frequencyNumber/3 * number * 100;
                } else {
                    score += 1000 * (frequencyNumber / 3);
                    score += (frequencyNumber % 3) * 100;
                }
                if (number == 5) {
                    score += (frequencyNumber % 3) * 50;
                }
            } else {
                if (number == 1) {
                    score += frequencyNumber % 3 * 100;
                } else if (number == 5) {
                    score += frequencyNumber % 3 * 50;
                }
            }
        }
        return score;
    }

    @Override
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
    
    @Override
    public int calculateRemainingDice(Map<Integer, Integer> scoredDices, int initialDice) {
        int remainingDices = initialDice;
        for (int number : scoredDices.keySet()) {
            remainingDices -= scoredDices.get(number);
        }
        return remainingDices;
    }

    
}
