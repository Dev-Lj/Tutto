package ch.uzh.turn.turnStrategy;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeoutException;

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

public class FireworksTurnTest
{
    private DiceManager invoke_turnLoop(FireworksTurn aFireworksTurn, DiceManager aDiceManager, ConsoleInput aConsoleInput) throws Throwable{
        try {
            Method method = FireworksTurn.class.getDeclaredMethod("turnLoop", DiceManager.class, ConsoleInput.class);
            method.setAccessible(true);
            return (DiceManager) method.invoke(aFireworksTurn, aDiceManager, aConsoleInput);
        } catch (Exception e) {
            throw e.getCause();
        }
    }

    /**
     * @throws Throwable
     */
    
    @Test
    void testturnLoop_hadNullTurn() throws Throwable {
        try {
            DiceManager aDiceManager = new DiceManager(6, new StraightDiceScoreStrategy());
            Field field = DiceManager.class.getDeclaredField("hadNullTurn");
            field.setAccessible(true);
            field.set(aDiceManager, true);

            DiceManager pDiceManager = (DiceManager) invoke_turnLoop(new FireworksTurn(), aDiceManager, ConsoleInput.instance());
            assertEquals(aDiceManager, pDiceManager);
        } catch (Throwable e) {
            fail();
        }
    }

    /**
     * @throws Throwable
     */
    
    @Test
    void testturnLoop_isTutto() throws Throwable {
        try {
            MockDiceManager StubDiceManager = new MockDiceManager( new StraightDiceScoreStrategy());
            StubDiceManager.setIsTutto(true);
            DiceManager pDiceManager = (DiceManager) invoke_turnLoop(new FireworksTurn(), StubDiceManager, ConsoleInput.instance());

            assertEquals(StubDiceManager, pDiceManager);
        } catch (Throwable e) {
            fail();
        }
    }


    @Test
    void turnLoop_playTurn() {
        String[] aStrings = {"R", "R", "R", "R", "R", "R"};
        try {
            ConsoleInput aTestingConsoleInput = TestingConsoleInput.createInstance(aStrings);
            DiceManager aDiceManager = invoke_turnLoop(new FireworksTurn(), new DiceManager(6, new NormalDiceScoreStrategy()), aTestingConsoleInput);
            assertTrue(aDiceManager.hadNullTurn() || aDiceManager.isTutto());
        } catch (Throwable e) {
            fail();
        }
        
    }
}

