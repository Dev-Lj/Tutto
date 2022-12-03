package ch.uzh.turn.turnStrategy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import static org.junit.jupiter.api.Assertions.*;

import ch.uzh.ConsoleInput;
import ch.uzh.command.Command;
import ch.uzh.command.NullCommand;
import ch.uzh.dice.DiceManager;
import ch.uzh.dice.MockDiceManager;
import ch.uzh.dice.NormalDiceScoreStrategy;
import ch.uzh.dice.StraightDiceScoreStrategy;
import ch.uzh.turn.PlayerTurn;




import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;



/**
 * Unit test for simple App.
 */
public class StraightTurnTest 
{
    
    private Command invoke_evaluateTurn(StraightTurn aStraightTurn, PlayerTurn currentTurn, DiceManager aDiceManager) throws Throwable{
        Method method = StraightTurn.class.getDeclaredMethod("evaluateTurn", PlayerTurn.class, DiceManager.class);
        method.setAccessible(true);
        try {
            return (Command) method.invoke(aStraightTurn, new PlayerTurn(), aDiceManager);
        } catch (InvocationTargetException e) {
            throw e.getCause();
        }
    }

    private DiceManager invoke_turnLoop(DiceManager aDiceManager, ConsoleInput aConsoleInput) throws Throwable{
        Method method = StraightTurn.class.getDeclaredMethod("turnLoop", DiceManager.class, ConsoleInput.class);
        method.setAccessible(true);
        try {
            return (DiceManager) method.invoke(new StraightTurn(), aDiceManager, aConsoleInput);
        } catch (InvocationTargetException e) {
            throw e.getCause();
        }
    }

    @Test
    void testGetScore() throws NoSuchFieldError, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException {
        StraightTurn aTurn = new StraightTurn();
        Field field = aTurn.getClass().getDeclaredField("score");
        field.setAccessible(true);
        field.set(aTurn, 100);
        assertEquals(100, aTurn.getScore());
    }

    /**
     * @throws Throwable
     */
    @Test
    void testEvaluateTurn_Tutto() throws Throwable {
        StraightTurn aStraightTurn = new StraightTurn();
        MockDiceManager StubDiceManager = new MockDiceManager( new StraightDiceScoreStrategy());
        StubDiceManager.setIsTutto(true);

        Command aCommand = invoke_evaluateTurn(aStraightTurn, new PlayerTurn(), (DiceManager) StubDiceManager);

        assertEquals(2000, aStraightTurn.getScore());
        assertTrue(aCommand instanceof NullCommand);
    }

    /**
     * @throws Throwable
     */
    @Test
    void testEvaluateTurn_noTutto() throws Throwable {
        StraightTurn aStraightTurn = new StraightTurn();
        MockDiceManager StubDiceManager = new MockDiceManager( new StraightDiceScoreStrategy());
        StubDiceManager.setIsTutto(false);

        Command aCommand = invoke_evaluateTurn(aStraightTurn, new PlayerTurn(), StubDiceManager);


        assertEquals(0, aStraightTurn.getScore());
        assertTrue(aCommand instanceof NullCommand);
    }

    /**
     * @throws Throwable
     */
    @Test
    void testturnLoop_hadNullTurn() throws Throwable {
        DiceManager aDiceManager = new DiceManager(6, new StraightDiceScoreStrategy());
        Field field = DiceManager.class.getDeclaredField("hadNullTurn");
        field.setAccessible(true);
        field.set(aDiceManager, true);

        DiceManager pDiceManager = (DiceManager) invoke_turnLoop(aDiceManager, ConsoleInput.instance());

        assertEquals(aDiceManager, pDiceManager);
    }

    /**
     * @throws Throwable
     */
    @Test
    void testturnLoop_isTutto() throws Throwable {
        MockDiceManager StubDiceManager = new MockDiceManager( new StraightDiceScoreStrategy());
        StubDiceManager.setIsTutto(true);

        DiceManager pDiceManager = (DiceManager) invoke_turnLoop(StubDiceManager, ConsoleInput.instance());

        assertEquals(StubDiceManager, pDiceManager);
    }
     
    @Test
    void testTurnLoop_endlessLoop() throws Throwable{
        Exception exception = assertThrows(TimeoutException.class, () -> {
            turnLoop_endlessLoop();
        });
        assertTrue(exception instanceof TimeoutException);
    }
    
    @Timeout(3)
    void turnLoop_endlessLoop() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, Throwable, TimeoutException {
        // fails with timeout
        MockDiceManager StubDiceManager = new MockDiceManager( new StraightDiceScoreStrategy());
        StubDiceManager.setIsTutto(false);
        StubDiceManager.setHadNullTurn(false);
        
        invoke_turnLoop(StubDiceManager, ConsoleInput.instance());
        return;
    }

    
}


