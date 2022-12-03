package ch.uzh.turn.turnStrategy;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.junit.jupiter.api.Test;

import ch.uzh.command.Command;
import ch.uzh.command.NullCommand;
import ch.uzh.turn.PlayerTurn;

/**
 * Unit test for simple App.
 */
public class TuttoDoubleTurnTest 
{
    TuttoDoubleTurn aTuttoDoubleTurn = new TuttoDoubleTurn();
    PlayerTurn aPlayerTurn = new PlayerTurn();

    private Command invoke_evaluateTurn(TuttoDoubleTurn aTuttoDoubleTurn, PlayerTurn currentTurn) throws Throwable{
        Method method = TuttoDoubleTurn.class.getDeclaredMethod("evaluateTurn", PlayerTurn.class);
        method.setAccessible(true);
        return (Command) method.invoke(aTuttoDoubleTurn, currentTurn);
    }

    @Test
    void testEvaluateTurn_hasLost()  {
        try {
            Field hasLost = StoppableTurn.class.getDeclaredField("hasLost");
            hasLost.setAccessible(true);
            hasLost.set(aTuttoDoubleTurn, true);

            Command aCommand = invoke_evaluateTurn(aTuttoDoubleTurn, new PlayerTurn());
            assertTrue(aCommand instanceof NullCommand);
        } catch (Throwable e) {
            fail(e.getCause());
        }

        
    }

    @Test
    void testEvaluateTurn_Tutto() {
        try {
            Field isTutto = StoppableTurn.class.getDeclaredField("isTutto");
            isTutto.setAccessible(true);
            isTutto.set(aTuttoDoubleTurn, true);

            Command aCommand = invoke_evaluateTurn(aTuttoDoubleTurn, aPlayerTurn);

            assertEquals(true, aPlayerTurn.isActive());
            assertTrue(aCommand instanceof NullCommand);
        } catch (Throwable e) {
            fail(e.getCause());
        }
    }

    @Test
    void testEvaluateTurn_noTutto() throws Throwable {
        try {
            Field isTutto = StoppableTurn.class.getDeclaredField("isTutto");
            isTutto.setAccessible(true);
            isTutto.set(aTuttoDoubleTurn, false);

            Command aCommand = invoke_evaluateTurn(aTuttoDoubleTurn, aPlayerTurn);

            assertEquals(false, aPlayerTurn.isActive());
            assertTrue(aCommand instanceof NullCommand);
        } catch (Throwable e) {
            fail(e.getCause());
        }
    }


}
