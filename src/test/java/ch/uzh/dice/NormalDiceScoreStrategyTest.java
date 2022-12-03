package ch.uzh.dice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Map;
import java.util.HashMap;
import org.junit.jupiter.api.Test;

public class NormalDiceScoreStrategyTest {
    NormalDiceScoreStrategy NDSS = new NormalDiceScoreStrategy();
    Map<Integer, Integer> dicees = new HashMap<Integer, Integer>();

    // TODO please split up your tests, so that each function only tests one
    // expected behaviour
    @Test
    public void Dice6ScoreTrump1() {
        dicees.clear();
        dicees.put(1, 6);
        assertEquals(2000, NDSS.calculateDiceScore(dicees));
    }

    @Test
    public void Dice6ScoreTrump5() {
        dicees.clear();
        dicees.put(5, 6);
        assertEquals(1000, NDSS.calculateDiceScore(dicees));
    }

    @Test
    public void Dice6ScoreNotTrump6() {
        dicees.clear();
        dicees.put(6, 6);
        assertEquals(1200, NDSS.calculateDiceScore(dicees));
    }

    @Test
    public void Dice4ScoreNotTrump1() {
        dicees.clear();
        dicees.put(1, 4);
        assertEquals(1100, NDSS.calculateDiceScore(dicees));
    }

    @Test
    public void Dice4ScoreNotTrump5() {
        dicees.clear();
        dicees.put(5, 4);
        assertEquals(550, NDSS.calculateDiceScore(dicees));
    }

    @Test
    public void Dice6ScoreNotTrumpMultiple() {
        dicees.clear();
        dicees.put(2, 2);
        dicees.put(3, 2);
        dicees.put(4, 1);
        dicees.put(6, 1);
        assertEquals(0, NDSS.calculateDiceScore(dicees));
    }

    @Test
    public void Dice2ScoreTrump1() {
        dicees.clear();
        dicees.put(1, 2);
        assertEquals(200, NDSS.calculateDiceScore(dicees));
    }

    @Test
    public void Dice2ScoreTrump5() {
        dicees.clear();
        dicees.put(5, 2);
        assertEquals(100, NDSS.calculateDiceScore(dicees));
    }

    @Test
    public void Dice2ScoreNotTrump4() {
        dicees.clear();
        dicees.put(4, 2);
        assertEquals(0, NDSS.calculateDiceScore(dicees));
    }

    @Test
    public void calculateScoredDices() {
        Map<Integer, Integer> dicees = new HashMap<Integer, Integer>();
        dicees.put(1, 1);
        dicees.put(2, 1);
        dicees.put(3, 2);
        Map<Integer, Integer> scoredDicees = new HashMap<Integer, Integer>();
        scoredDicees.put(1, 1);
        scoredDicees.put(2, 3);
        Map<Integer, Integer> resultDicees = new HashMap<Integer, Integer>();
        resultDicees.put(1, 2);
        resultDicees.put(2, 3);
        assertEquals(resultDicees, NDSS.calculateScoredDices(dicees, scoredDicees));
        dicees.clear();
        dicees.put(1, 1);
        dicees.put(2, 1);
        dicees.put(3, 2);
        scoredDicees.clear();
        scoredDicees.put(1, 1);
        scoredDicees.put(2, 3);
        resultDicees.clear();
        resultDicees.put(1, 2);
        resultDicees.put(2, 3);
        assertEquals(resultDicees, NDSS.calculateScoredDices(dicees, scoredDicees));
        dicees.clear();
        dicees.put(4, 3);
        scoredDicees.clear();
        scoredDicees.put(1, 1);
        resultDicees.clear();
        resultDicees.put(4, 3);
        resultDicees.put(1, 1);
        assertEquals(resultDicees, NDSS.calculateScoredDices(dicees, scoredDicees));
        dicees.clear();
        dicees.put(4, 3);
        scoredDicees.clear();
        scoredDicees.put(4, 3);
        resultDicees.clear();
        resultDicees.put(4, 6);
        assertEquals(resultDicees, NDSS.calculateScoredDices(dicees, scoredDicees));
        dicees.clear();
        dicees.put(6, 6);
        scoredDicees.clear();
        resultDicees.clear();
        resultDicees.put(6, 6);
        assertEquals(resultDicees, NDSS.calculateScoredDices(dicees, scoredDicees));
        dicees.clear();
        scoredDicees.clear();
        resultDicees.clear();
        assertEquals(resultDicees, NDSS.calculateScoredDices(dicees, scoredDicees));
        dicees.clear();
        dicees.put(5, 2);
        dicees.put(6, 2);
        dicees.put(1, 2);
        scoredDicees.clear();
        resultDicees.clear();
        resultDicees.put(5, 2);
        resultDicees.put(1, 2);
        assertEquals(resultDicees, NDSS.calculateScoredDices(dicees, scoredDicees));

    }

    @Test
    public void calculateRemainingDice() {
        Map<Integer, Integer> dicees = new HashMap<Integer, Integer>();
        dicees.put(1, 6);
        assertEquals(0, NDSS.calculateRemainingDice(dicees, 6));
        dicees.clear();
        dicees.put(3, 6);
        assertEquals(0, NDSS.calculateRemainingDice(dicees, 6));
        dicees.clear();
        dicees.put(5, 1);
        assertEquals(5, NDSS.calculateRemainingDice(dicees, 6));
        dicees.clear();
        dicees.put(2, 2);
        dicees.put(3, 2);
        dicees.put(4, 1);
        dicees.put(6, 1);
        assertEquals(0, NDSS.calculateRemainingDice(dicees, 6));
        dicees.clear();
        dicees.put(4, 3);
        assertEquals(0, NDSS.calculateRemainingDice(dicees, 3));
        dicees.clear();
        dicees.put(1, 1);
        assertEquals(4, NDSS.calculateRemainingDice(dicees, 5));
        dicees.clear();
        dicees.put(5, 3);
        dicees.put(4, 3);
        assertEquals(0, NDSS.calculateRemainingDice(dicees, 6));
    }

}
