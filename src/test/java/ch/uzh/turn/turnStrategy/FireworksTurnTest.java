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

public class FireworksTurnTest
{
    MockDiceManager aMockDiceManager = new MockDiceManager( new StraightDiceScoreStrategy());

    private DiceManager invoke_turnLoop(FireworksTurn aFireworksTurn, DiceManager aDiceManager, ConsoleInput aConsoleInput) throws Throwable{
        Method method = FireworksTurn.class.getDeclaredMethod("turnLoop", DiceManager.class, ConsoleInput.class);
        method.setAccessible(true);
        return (DiceManager) method.invoke(aFireworksTurn, aDiceManager, aConsoleInput);
    }

    @Test
    void testturnLoop_hadNullTurn() {
        aMockDiceManager.setHadNullTurn(true);

        try {
            DiceManager pDiceManager = (DiceManager) invoke_turnLoop(new FireworksTurn(), aMockDiceManager, ConsoleInput.instance());
            assertEquals(aMockDiceManager, pDiceManager);
        } catch (Throwable e) {
            fail(e.getCause());
        }
    }
    
    @Test
    void testturnLoop_isTutto() {
        aMockDiceManager.setIsTutto(true);

        try {
            DiceManager pDiceManager = (DiceManager) invoke_turnLoop(new FireworksTurn(), aMockDiceManager, ConsoleInput.instance());
            assertEquals(aMockDiceManager, pDiceManager);
        } catch (Throwable e) {
            fail(e.getCause());
        }
    }

    @Test
    void turnLoop_playTurn() {
        String[] aStrings = {"R", "R", "R", "R", "R", "R"};
        
        try {
            ConsoleInput aTestingConsoleInput = TestingConsoleInput.createFakeScannerInstance(aStrings);
            DiceManager aDiceManager = invoke_turnLoop(new FireworksTurn(), new DiceManager(6, new NormalDiceScoreStrategy()), aTestingConsoleInput);
            assertTrue(aDiceManager.hadNullTurn() || aDiceManager.isTutto());
        } catch (Throwable e) {
            fail(e.getCause());
        }
        
    }
    
}

