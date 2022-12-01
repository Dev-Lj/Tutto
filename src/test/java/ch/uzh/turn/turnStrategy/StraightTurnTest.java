package ch.uzh.turn.turnStrategy;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;




/**
 * Unit test for simple App.
 */
public class StraightTurnTest 
{
    /**
     * Rigorous Test :-)
     * @throws NoSuchFieldException
     */
    @Test
    void testGetScore() throws NoSuchFieldError, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException {
        StraightTurn aTurn = new StraightTurn();
        Field field = aTurn.getClass().getDeclaredField("score");
        field.setAccessible(true);
        field.set(aTurn, 100);
        assertEquals(100, aTurn.getScore());
    }
}
