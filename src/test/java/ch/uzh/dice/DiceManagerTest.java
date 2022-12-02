package ch.uzh.dice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DiceManagerTest {
    /* MockDiceScoreStrategy FDSS = new MockDiceScoreStrategy(); */
    NormalDiceScoreStrategy FDSS = new NormalDiceScoreStrategy();
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    MockRandomNumber FRN = new MockRandomNumber();

    @Test
    public void initalScore() {
        FRN.setReulsultNumber(1);
        DiceManager DM = new DiceManager(6, FDSS, FRN);
        assertEquals(0, DM.getScore());
        assertEquals(false, DM.hadNullTurn());
        assertEquals(false, DM.isTutto());

    }

    @Test
    public void rollDice1() {
        FRN.setReulsultNumber(1);
        DiceManager DM = new DiceManager(6, FDSS, FRN);
        DM.rollDice();
        assertEquals(2000, DM.getScore());
        assertEquals(true, DM.isTutto());
        assertEquals(false, DM.hadNullTurn());
    }

    @Test
    public void rollDice2() {
        FRN.setReulsultNumber(2);
        DiceManager DM = new DiceManager(2, FDSS, FRN);
        DM.rollDice();
        assertEquals(0, DM.getScore());
        assertEquals(false, DM.isTutto());
        assertEquals(true, DM.hadNullTurn());
    }

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void printScoredDices1() {
        FRN.setReulsultNumber(1);
        DiceManager DM = new DiceManager(2, FDSS, FRN);
        DM.rollDice();
        DM.printScoredDices();
        assertEquals("Dices scored so far" + "\n" + "Number: 1 -> 2 times", outputStreamCaptor.toString()
                .trim());
    }

    @Test
    public void printScoredDices2() {
        FRN.setReulsultNumber(2);
        DiceManager DM = new DiceManager(5, FDSS, FRN);
        DM.rollDice();
        DM.printScoredDices();
        assertEquals("Dices scored so far" + "\n" + "Number: 2 -> 3 times", outputStreamCaptor.toString()
                .trim());

    }
}
