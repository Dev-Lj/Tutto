package ch.uzh.dice;

import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Map;
import org.junit.jupiter.api.Test;


public class StraightDiceScoreStrategyTest {
    StraightDiceScoreStrategy SDSS = new StraightDiceScoreStrategy();


    @Test
    public void calculateDiceScore() {
        Map<Integer, Integer> dicees = new HashMap<Integer, Integer>();
        dicees.put(1, 6);
        assertEquals(0, SDSS.calculateDiceScore(dicees));
        dicees.clear();
        dicees.put(5, 6);
        assertEquals(0, SDSS.calculateDiceScore(dicees));
        dicees.clear();
        dicees.put(6, 6);
        assertEquals(0, SDSS.calculateDiceScore(dicees));
        dicees.clear();
        dicees.put(1, 4);
        assertEquals(0, SDSS.calculateDiceScore(dicees));
        dicees.clear();
        dicees.put(5, 4);
        assertEquals(0, SDSS.calculateDiceScore(dicees));
        dicees.clear();
        dicees.put(2, 2);
        dicees.put(3, 2);
        dicees.put(4, 2);
        assertEquals(0, SDSS.calculateDiceScore(dicees));
        dicees.clear();
        dicees.put(4, 3);
        assertEquals(0, SDSS.calculateDiceScore(dicees));
        dicees.clear();
        dicees.put(1, 1);
        assertEquals(0, SDSS.calculateDiceScore(dicees));
        dicees.clear();
        dicees.put(5, 1);
        assertEquals(0, SDSS.calculateDiceScore(dicees));
        dicees.clear();
        dicees.put(7, 1);
        assertEquals(0, SDSS.calculateDiceScore(dicees));
    }

    @Test
    public void calculateScoredDices() {
        Map<Integer, Integer> dicees = new HashMap<Integer, Integer>();
        dicees.put(1, 3);
        dicees.put(2, 1);
        dicees.put(3, 1);
        Map<Integer, Integer> scoredDicees = new HashMap<Integer, Integer>();
        scoredDicees.put(1, 1);
        Map<Integer, Integer> resultDicees = new HashMap<Integer, Integer>();
        resultDicees.put(1, 1);
        resultDicees.put(2, 1);
        resultDicees.put(3, 1);
        assertEquals(resultDicees, SDSS.calculateScoredDices(dicees, scoredDicees));
        dicees.clear();
        dicees.put(1, 1);
        dicees.put(2, 1);
        dicees.put(3, 1);
        dicees.put(4, 1);
        dicees.put(5, 1);
        dicees.put(6, 1);
        scoredDicees.clear();
        resultDicees.clear();
        resultDicees.put(1, 1);
        resultDicees.put(2, 1);
        resultDicees.put(3, 1);
        resultDicees.put(4, 1);
        resultDicees.put(5, 1);
        resultDicees.put(6, 1);
        assertEquals(resultDicees, SDSS.calculateScoredDices(dicees, scoredDicees));
    }

    @Test
    public void calculateRemainingDice() {
        Map<Integer, Integer> dicees = new HashMap<Integer, Integer>();
        dicees.put(1, 6);
        assertEquals(5, SDSS.calculateRemainingDice(dicees,6)); 
        dicees.clear();
        dicees.put(3, 6); 
        assertEquals(5, SDSS.calculateRemainingDice(dicees,6));
        dicees.clear();
        dicees.put(6, 6); 
        assertEquals(5, SDSS.calculateRemainingDice(dicees,6));
        dicees.clear();
        dicees.put(6, 5); 
        dicees.put(4, 1); 
        assertEquals(4, SDSS.calculateRemainingDice(dicees,6));
        dicees.clear();
        dicees.put(6, 4);
        dicees.put(1, 1);
        dicees.put(2, 1); 
        assertEquals(3, SDSS.calculateRemainingDice(dicees,6));
        dicees.clear();
        dicees.put(4, 1); 
        dicees.put(3, 1); 
        dicees.put(6, 2);
        dicees.put(1, 1);
        dicees.put(2, 1); 
        assertEquals(1, SDSS.calculateRemainingDice(dicees, 6));
        dicees.clear();
        dicees.put(1, 1); 
        dicees.put(2, 1); 
        dicees.put(3, 2);
        dicees.put(4, 2); 
        assertEquals(2, SDSS.calculateRemainingDice(dicees, 6));
        dicees.clear();
        dicees.put(6, 6); 
        assertEquals(5, SDSS.calculateRemainingDice(dicees,6));
        dicees.clear();
        dicees.put(5, 1);
        assertEquals(5, SDSS.calculateRemainingDice(dicees,6));
        dicees.clear();
        dicees.put(2, 2);
        dicees.put(3, 2);
        dicees.put(4, 2);
        assertEquals(3, SDSS.calculateRemainingDice(dicees,6));
        dicees.clear();
        dicees.put(1, 1);
        dicees.put(2, 1);
        dicees.put(3, 1);
        dicees.put(4, 1);
        dicees.put(5, 1);
        dicees.put(6, 1);
        assertEquals(0, SDSS.calculateRemainingDice(dicees, 6));
        dicees.clear();
        dicees.put(1, 1);
        dicees.put(2, 1);
        dicees.put(3, 1);
        assertEquals(0, SDSS.calculateRemainingDice(dicees,3));
    }

}
