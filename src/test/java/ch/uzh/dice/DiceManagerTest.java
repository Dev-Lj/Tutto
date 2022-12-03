package ch.uzh.dice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DiceManagerTest {
    MockDiceScoreStrategy FDSS = new MockDiceScoreStrategy();
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    MockRandomNumber FRN = new MockRandomNumber();

    @Test
    public void initalStats() {
        FRN.setReulsultNumber(1);
        DiceManager DM = new DiceManager(6, FDSS, FRN);
        assertEquals(0, DM.getScore());
        assertEquals(false, DM.hadNullTurn());
        assertEquals(false, DM.isTutto());

    }

    @Test
    public void rollSixStatsTrump() {
        FRN.setReulsultNumber(1);
        DiceManager DM = new DiceManager(6, FDSS, FRN);
        DM.rollDice();
        assertEquals(1, DM.getScore());
        assertEquals(true, DM.isTutto());
        assertEquals(false, DM.hadNullTurn());
    }

    @Test
    public void rollTwoStatsNotTrump() {
        FRN.setReulsultNumber(2);
        DiceManager DM = new DiceManager(2, FDSS, FRN);
        DM.rollDice();
        assertEquals(1, DM.getScore());
        assertEquals(false, DM.isTutto());
        assertEquals(true, DM.hadNullTurn());
    }

    @Test
    public void rollThreeStatsNotTrump() {
        FRN.setReulsultNumber(2);
        DiceManager DM = new DiceManager(4, FDSS, FRN);
        DM.rollDice();
        assertEquals(1, DM.getScore());
        assertEquals(false, DM.isTutto());
        assertEquals(true, DM.hadNullTurn());
    }

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void printsTrumpDice() {
        FRN.setReulsultNumber(1);
        DiceManager DM = new DiceManager(2, FDSS, FRN);
        DM.rollDice();
        DM.printScoredDices();
        assertEquals("Dices scored so far" + "\n" + "Number: 1 -> 2 times", outputStreamCaptor.toString()
                .trim());
    }

    @Test
    public void printNotTrump() {
        FRN.setReulsultNumber(2);
        DiceManager DM = new DiceManager(5, FDSS, FRN);
        DM.rollDice();
        DM.printScoredDices();
        assertEquals("Dices scored so far", outputStreamCaptor.toString()
                .trim());

    }
}
