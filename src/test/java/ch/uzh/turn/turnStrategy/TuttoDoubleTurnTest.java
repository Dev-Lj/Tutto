package ch.uzh.turn.turnStrategy;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
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
    private Command invoke_evaluateTurn(TuttoDoubleTurn aBonusTurn, PlayerTurn currentTurn) throws Throwable{
        Method method = TuttoDoubleTurn.class.getDeclaredMethod("evaluateTurn", PlayerTurn.class);
        method.setAccessible(true);
        try {
            return (Command) method.invoke(aBonusTurn, currentTurn);
        } catch (InvocationTargetException e) {
            throw e.getCause();
        }
    }

    /**
     * @throws Throwable
     */
    @Test
    void testEvaluateTurn_hasLost() throws Throwable {
        TuttoDoubleTurn aBonusTurn = new TuttoDoubleTurn();

        Field hasLost = StoppableTurn.class.getDeclaredField("hasLost");
        hasLost.setAccessible(true);
        hasLost.set(aBonusTurn, true);

        Command aCommand = invoke_evaluateTurn(aBonusTurn, new PlayerTurn());
        assertTrue(aCommand instanceof NullCommand);
    }


    /**
     * @throws Throwable
     */
    @Test
    void testEvaluateTurn_Tutto() throws Throwable {
        TuttoDoubleTurn aBonusTurn = new TuttoDoubleTurn();

        Field isTutto = StoppableTurn.class.getDeclaredField("isTutto");
        isTutto.setAccessible(true);
        isTutto.set(aBonusTurn, true);

        PlayerTurn aPlayerTurn = new PlayerTurn();
        Field score = PlayerTurn.class.getDeclaredField("score");
        score.setAccessible(true);
        score.set(aPlayerTurn, 100);



        Command aCommand = invoke_evaluateTurn(aBonusTurn, aPlayerTurn);

        //assertEquals(200, aBonusTurn.getScore());
        assertTrue(aCommand instanceof NullCommand);
    }


    /**
     * @throws Throwable
     */
    @Test
    void testEvaluateTurn_noTutto() throws Throwable {
        TuttoDoubleTurn aBonusTurn = new TuttoDoubleTurn();

        Field isTutto = StoppableTurn.class.getDeclaredField("isTutto");
        isTutto.setAccessible(true);
        isTutto.set(aBonusTurn, false);

        PlayerTurn aPlayerTurn = new PlayerTurn();
        Command aCommand = invoke_evaluateTurn(aBonusTurn, aPlayerTurn);

        //assertEquals(200, aBonusTurn.getScore());
        assertTrue(aCommand instanceof NullCommand);
    }


}
