package ch.uzh.turn.turnStrategy;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.junit.jupiter.api.Test;

import ch.uzh.command.Command;
import ch.uzh.command.NullCommand;
import ch.uzh.turn.PlayerTurn;


public class TuttoBonusTurnTest 
{
    TuttoBonusTurn aBonusTurn = new TuttoBonusTurn(500);

    private Command invoke_evaluateTurn(TuttoBonusTurn aBonusTurn, PlayerTurn currentTurn) throws Throwable{
        Method method = TuttoBonusTurn.class.getDeclaredMethod("evaluateTurn", PlayerTurn.class);
        method.setAccessible(true);
        return (Command) method.invoke(aBonusTurn, currentTurn);
    }

    @Test
    void testEvaluateTurn_Tutto() {        
        try {
            Field isTutto = StoppableTurn.class.getDeclaredField("isTutto");
            isTutto.setAccessible(true);
            isTutto.set(aBonusTurn, true);
            Command aCommand = invoke_evaluateTurn(aBonusTurn, new PlayerTurn());
            assertEquals(500, aBonusTurn.getScore());
            assertTrue(aCommand instanceof NullCommand);
        } catch (Throwable e) {
            fail(e.getCause());
        }
    }

    @Test
    void testEvaluateTurn_noTutto() {
        try {
            Field isTutto = StoppableTurn.class.getDeclaredField("isTutto");
            isTutto.setAccessible(true);
            isTutto.set(aBonusTurn, false);
            Command aCommand = invoke_evaluateTurn(aBonusTurn, new PlayerTurn());
            assertEquals(0, aBonusTurn.getScore());
            assertTrue(aCommand instanceof NullCommand);
        } catch (Throwable e) {
            fail(e.getCause());
        }
    }

    @Test
    void testEvaluateTurn_hasLost() throws Throwable {
        try {
            Field isTutto = StoppableTurn.class.getDeclaredField("hasLost");
            isTutto.setAccessible(true);
            isTutto.set(aBonusTurn, true);
            Command aCommand = invoke_evaluateTurn(aBonusTurn, new PlayerTurn());
            assertTrue(aCommand instanceof NullCommand);
        } catch (Throwable e) {
            fail(e.getCause());
        }
    }

}
