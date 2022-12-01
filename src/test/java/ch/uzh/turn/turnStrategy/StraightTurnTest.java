package ch.uzh.turn.turnStrategy;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.junit.jupiter.api.Test;

import ch.uzh.turn.TurnStrategy;

/**
 * Unit test for simple App.
 */
public class StraightTurnTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    void testGetScore()
    {
        StraightTurn aTurn = new StraightTurn();
        Method scoreField = StraightTurn.class.getDeclaredMethod("getScore");
        
        scoreField.setAccessible(true);


        assertEquals(100, aTurn.getScore());
    }
}
