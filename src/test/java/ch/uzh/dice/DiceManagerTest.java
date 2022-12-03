package ch.uzh.dice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DiceManagerTest {
    MockDiceScoreStrategy FDSS = new MockDiceScoreStrategy();
    /* NormalDiceScoreStrategy FDSS = new NormalDiceScoreStrategy(); */
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

    // TODO please give the tests a descriptive name -> if a test fails and only the name is of the test is printed, it should be clear what went wrong

    @Test
    public void rollDice1() {
        FRN.setReulsultNumber(1);
        DiceManager DM = new DiceManager(6, FDSS, FRN);
        DM.rollDice();
        assertEquals(1, DM.getScore());
        assertEquals(true, DM.isTutto());
        assertEquals(false, DM.hadNullTurn());
    }

    @Test
    public void rollDice2() {
        FRN.setReulsultNumber(2);
        DiceManager DM = new DiceManager(2, FDSS, FRN);
        DM.rollDice();
        assertEquals(1, DM.getScore());
        assertEquals(false, DM.isTutto());
        assertEquals(true, DM.hadNullTurn());
    }

    @Test
    public void rollDice3() {
        FRN.setReulsultNumber(2);
        DiceManager DM = new DiceManager(4, FDSS, FRN);
        DM.rollDice();
        assertEquals(1, DM.getScore());
        assertEquals(false, DM.isTutto());
        assertEquals(false, DM.hadNullTurn());
        DM.rollDice();
        assertEquals(2, DM.getScore());
        assertEquals(true, DM.hadNullTurn());
        assertEquals(false, DM.isTutto());
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
