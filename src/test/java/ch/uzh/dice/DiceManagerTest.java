package ch.uzh.dice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class DiceManagerTest {
    /*MockDiceScoreStrategy FDSS = new MockDiceScoreStrategy();*/
    NormalDiceScoreStrategy FDSS=new NormalDiceScoreStrategy();
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    MockRandomNumber1 FRN1 = new MockRandomNumber1();
    MockRandomNumber2 FRN2 = new MockRandomNumber2();

   
    @Test
    public void initalScore() {
        MockRandomNumber1 FRN = new MockRandomNumber1();
        DiceManager DM = new DiceManager(6, FDSS,FRN1);
        assertEquals(0, DM.getScore());
        assertEquals(false, DM.hadNullTurn());
        assertEquals(false, DM.isTutto());

    }
    
    @Test
    public void rollDice1() {
        DiceManager DM = new DiceManager(6, FDSS,FRN1);
        DM.rollDice();
        assertEquals(400, DM.getScore());
        assertEquals(true, DM.isTutto());
        assertEquals(false, DM.hadNullTurn());
    }

    @Test
    public void rollDice2() {
        DiceManager DM = new DiceManager(2, FDSS,FRN2);
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
        DiceManager DM = new DiceManager(2, FDSS,FRN1);
        DM.rollDice();
        DM.printScoredDices();
        assertEquals("Dices scored so far", outputStreamCaptor.toString()
                .trim());
    }
    
    @Test 
    public void printScoredDices2() {
    DiceManager DM = new DiceManager(5, FDSS,FRN2);
    DM.rollDice();
    DM.printScoredDices();
    assertEquals("Dices scored so far"+ "\n" + "Number: 2 -> 3 times", outputStreamCaptor.toString()
            .trim());
    
}
}
