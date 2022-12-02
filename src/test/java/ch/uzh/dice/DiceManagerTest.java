package ch.uzh.dice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class DiceManagerTest {
    /*FakeDiceScoreStrategy FDSS = new FakeDiceScoreStrategy();*/
    NormalDiceScoreStrategy FDSS=new NormalDiceScoreStrategy();
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();


   
    @Test
    public void initalScore() {
        DiceManager DM = new DiceManager(6, FDSS);
        FakeRandomNumber1 FRN = new FakeRandomNumber1();
        DM.setFakeRandom(FRN);
        assertEquals(0, DM.getScore());
        assertEquals(false, DM.hadNullTurn());
        assertEquals(false, DM.isTutto());

    }
    
    @Test
    public void rollDice1() {
        DiceManager DM = new DiceManager(6, FDSS);
        FakeRandomNumber1 FRN = new FakeRandomNumber1();
        DM.setFakeRandom(FRN);
        DM.rollDice();
        assertEquals(400, DM.getScore());
        assertEquals(true, DM.isTutto());
        assertEquals(false, DM.hadNullTurn());
    }

    @Test
    public void rollDice2() {
        DiceManager DM = new DiceManager(2, FDSS);
        FakeRandomNumber2 FRN = new FakeRandomNumber2();
        DM.setFakeRandom(FRN);
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
        DiceManager DM = new DiceManager(2, FDSS);
        FakeRandomNumber2 FRN = new FakeRandomNumber2();
        DM.setFakeRandom(FRN);
        DM.rollDice();
        DM.printScoredDices();
        assertEquals("Dices scored so far", outputStreamCaptor.toString()
                .trim());
    }
    
    @Test 
    public void printScoredDices2() {
    DiceManager DM = new DiceManager(5, FDSS);
    FakeRandomNumber2 FRN = new FakeRandomNumber2();
    DM.setFakeRandom(FRN);
    DM.rollDice();
    DM.printScoredDices();
    assertEquals("Dices scored so far"+ "\n" + "Number: 2 -> 3 times", outputStreamCaptor.toString()
            .trim());
    
}
}
