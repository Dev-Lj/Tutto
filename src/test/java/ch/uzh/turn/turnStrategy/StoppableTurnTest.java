package ch.uzh.turn.turnStrategy;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.jupiter.api.Test;

import ch.uzh.ConsoleInput;
import ch.uzh.TestingConsoleInput;
import ch.uzh.dice.DiceManager;
import ch.uzh.dice.MockDiceManager;
import ch.uzh.dice.NormalDiceScoreStrategy;
import ch.uzh.dice.StraightDiceScoreStrategy;

/**
 * Unit test for simple App.
 */
public class StoppableTurnTest 
{
    private void invoke_computeTurn(StoppableTurn aStoppableTurn, DiceManager aDiceManager) throws Throwable{
        Method method = StoppableTurn.class.getDeclaredMethod("computeTurn", DiceManager.class);
        method.setAccessible(true);
        try {
            method.invoke(aStoppableTurn, aDiceManager);
        } catch (InvocationTargetException e) {
            throw e.getCause();
        }
    }

    private DiceManager invoke_turnLoop(StoppableTurn aStoppableTurn, DiceManager aDiceManager, ConsoleInput aConsoleInput) throws Throwable{
        Method method = StoppableTurn.class.getDeclaredMethod("turnLoop", DiceManager.class, ConsoleInput.class);
        method.setAccessible(true);
        try {
            return (DiceManager) method.invoke(aStoppableTurn, aDiceManager, aConsoleInput);
        } catch (InvocationTargetException e) {
            throw e.getCause();
        }
    }

    @Test
    void testEvaluateTurn_NullTurn() throws Throwable {
        StoppableTurn aStoppableTurn = new TuttoDoubleTurn();

        DiceManager aDiceManager = new DiceManager(6, new NormalDiceScoreStrategy());

        Field hadNullTurn = DiceManager.class.getDeclaredField("hadNullTurn");
        hadNullTurn.setAccessible(true);
        hadNullTurn.set(aDiceManager, true);

        invoke_computeTurn(aStoppableTurn, aDiceManager);

        assertEquals(true, aStoppableTurn.hasLost());
    }

    @Test
    void testEvaluateTurn_NoNullTurn() throws Throwable {
        StoppableTurn aStoppableTurn = new TuttoDoubleTurn();

        DiceManager aDiceManager = new DiceManager(6, new NormalDiceScoreStrategy());

        Field hadNullTurn = DiceManager.class.getDeclaredField("hadNullTurn");
        hadNullTurn.setAccessible(true);
        hadNullTurn.set(aDiceManager, false);

        invoke_computeTurn(aStoppableTurn, aDiceManager);

        assertEquals(false, aStoppableTurn.hasLost());
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

        DiceManager pDiceManager = (DiceManager) invoke_turnLoop(new TuttoDoubleTurn(), aDiceManager, ConsoleInput.instance());

        assertEquals(aDiceManager, pDiceManager);
    }

    /**
     * @throws Throwable
     */
    @Test
    void testturnLoop_isTutto() throws Throwable {
        MockDiceManager StubDiceManager = new MockDiceManager( new StraightDiceScoreStrategy());
        StubDiceManager.setIsTutto(true);

        DiceManager pDiceManager = (DiceManager) invoke_turnLoop(new TuttoDoubleTurn(), StubDiceManager, ConsoleInput.instance());

        assertEquals(StubDiceManager, pDiceManager);
    }
    /*
    @Test
    void turnLoop_playTurn() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, Throwable {
        String[] aStrings = {"R", "R", "R", "R", "R", "R"};
        ConsoleInput aTestingConsoleInput = TestingConsoleInput.createInstance(aStrings);
        
        DiceManager aDiceManager = invoke_turnLoop(new TuttoDoubleTurn(), new DiceManager(6, new NormalDiceScoreStrategy()), aTestingConsoleInput);
        assertTrue(aDiceManager.hadNullTurn() || aDiceManager.isTutto());
    }
    
    @Test
    void turnLoop_playerStopsTurn() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, Throwable {
        String[] aStrings = {"E"};
        ConsoleInput aTestingConsoleInput = TestingConsoleInput.createInstance(aStrings);
        
        DiceManager aDiceManager = invoke_turnLoop(new TuttoDoubleTurn(), new DiceManager(6, new NormalDiceScoreStrategy()), aTestingConsoleInput);
        assertTrue(!aDiceManager.hadNullTurn() && !aDiceManager.isTutto());
    }
    */
}
