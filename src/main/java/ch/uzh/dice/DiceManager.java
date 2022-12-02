package ch.uzh.dice;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class DiceManager {
    private final DiceScoreStrategy scoreStrategy;
    private boolean hadNullTurn = false;
    private int score = 0;
    private int initialDice;
    private int remainingDice;
    private Random rand;
    private Map<Integer, Integer> scoredDices = new HashMap<Integer, Integer>();
   
    public DiceManager(int availableDice, DiceScoreStrategy scoreStrategy) {
        this.initialDice = availableDice;
        this.remainingDice = availableDice;
        this.scoreStrategy = scoreStrategy;
        this.rand = new Random();
    }

    public DiceManager(int availableDice, DiceScoreStrategy scoreStrategy, Random random) {
        this.initialDice = availableDice;
        this.remainingDice = availableDice;
        this.scoreStrategy = scoreStrategy;
        this.rand = random;
    }

    public void rollDice() {
        Map<Integer, Integer> dice = new HashMap<>();
        for (int i = 1; i <= this.remainingDice; i++) {
            int number = rand.nextInt(6) + 1;
            if (dice.containsKey(number)) {
                dice.put(number, dice.get(number) + 1);
            } else {
                dice.put(number, 1);
            }
        }
        /* changed here from throw score to remaining dice since for straight the score does not increase */
        this.score += scoreStrategy.calculateDiceScore(dice);
        this.scoredDices = scoreStrategy.calculateScoredDices(dice, this.scoredDices);
        if (scoreStrategy.calculateRemainingDice(this.scoredDices, this.initialDice) == this.remainingDice) {
            this.hadNullTurn = true;
        }
        this.remainingDice = scoreStrategy.calculateRemainingDice(this.scoredDices, this.initialDice);
    }


    public void printScoredDices() {
        System.out.print("Dices scored so far");
        System.out.println();
        for (int key : this.scoredDices.keySet()){
            System.out.print("Number: ");
            System.out.print(key);
            System.out.print(" -> ");
            System.out.print(scoredDices.get(key));
            System.out.print(" times");
            System.out.println();
        }
    }

    public int getScore() {
        return this.score;
    }

    public boolean isTutto() {
        return remainingDice == 0;
    }

    public boolean hadNullTurn() {
        return hadNullTurn;
    }

    
}
