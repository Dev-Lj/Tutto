package ch.uzh.turn.turnStrategy;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Method;

import org.junit.jupiter.api.Test;

import ch.uzh.ConsoleInput;
import ch.uzh.TestingConsoleInput;
import ch.uzh.dice.DiceManager;
import ch.uzh.dice.MockDiceManager;
import ch.uzh.dice.NormalDiceScoreStrategy;


public class StoppableTurnTest 
{
    StoppableTurn aStoppableTurn = new TuttoDoubleTurn();
    MockDiceManager aMockDiceManager = new MockDiceManager(new NormalDiceScoreStrategy());

    private void invoke_computeTurn(StoppableTurn aStoppableTurn, DiceManager aDiceManager) throws Throwable{
        Method method = StoppableTurn.class.getDeclaredMethod("computeTurn", DiceManager.class);
        method.setAccessible(true);
        method.invoke(aStoppableTurn, aDiceManager);
    }

    private DiceManager invoke_turnLoop(StoppableTurn aStoppableTurn, DiceManager aDiceManager, ConsoleInput aConsoleInput) throws Throwable{
        Method method = StoppableTurn.class.getDeclaredMethod("turnLoop", DiceManager.class, ConsoleInput.class);
        method.setAccessible(true);
        return (DiceManager) method.invoke(aStoppableTurn, aDiceManager, aConsoleInput);
    }

    @Test
    void testEvaluateTurn_NullTurn() {
        aMockDiceManager.setHadNullTurn(true);

        try {
            invoke_computeTurn(aStoppableTurn, aMockDiceManager);
            assertEquals(true, aStoppableTurn.hasLost());
        } catch (Throwable e) {
            fail(e.getCause());
        }
    }

    @Test
    void testEvaluateTurn_NoNullTurn() {
        aMockDiceManager.setHadNullTurn(false);

        try {
            invoke_computeTurn(aStoppableTurn, aMockDiceManager);
            assertEquals(false, aStoppableTurn.hasLost());
        } catch (Throwable e) {
            fail(e.getCause());
        }
    }


    @Test
    void testturnLoop_hadNullTurn() {
        aMockDiceManager.setHadNullTurn(true);
        try {
            DiceManager pDiceManager = (DiceManager) invoke_turnLoop(aStoppableTurn, aMockDiceManager, ConsoleInput.instance());
            assertEquals(aMockDiceManager, pDiceManager);
        } catch (Throwable e) {
            fail(e.getCause());
        }
    }

    @Test
    void testturnLoop_isTutto() { 
        aMockDiceManager.setIsTutto(true);
        try {
            DiceManager pDiceManager = (DiceManager) invoke_turnLoop(aStoppableTurn, aMockDiceManager, ConsoleInput.instance());
            assertEquals(aMockDiceManager, pDiceManager);
        } catch (Throwable e) {
            fail(e.getCause());
        }
    }
    
    @Test
    void turnLoop_playTurn() {
        String[] aStrings = {"R", "E"};

        try {
            ConsoleInput aTestingConsoleInput = TestingConsoleInput.createFakeScannerInstance(aStrings);
            DiceManager aDiceManager = invoke_turnLoop(aStoppableTurn, aMockDiceManager, aTestingConsoleInput);
            assertEquals(aDiceManager.getScore(), aStoppableTurn.getScore());
        } catch (Throwable e) {
            fail(e.getCause());
        }
    }
    
    @Test
    void turnLoop_playerStopsTurn() {
        String[] aStrings = {"E"};
        
        try {
            ConsoleInput aTestingConsoleInput = TestingConsoleInput.createFakeScannerInstance(aStrings);
            DiceManager aDiceManager = invoke_turnLoop(aStoppableTurn, aMockDiceManager, aTestingConsoleInput);
            assertTrue(!aDiceManager.hadNullTurn() && !aDiceManager.isTutto());
        } catch (Throwable e) {
            fail(e.getCause());
        }
    }
    
}
