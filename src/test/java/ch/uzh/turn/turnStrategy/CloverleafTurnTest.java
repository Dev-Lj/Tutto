package ch.uzh.turn.turnStrategy;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Method;
import java.lang.reflect.Field;

import org.junit.jupiter.api.Test;

import ch.uzh.ConsoleInput;
import ch.uzh.TestingConsoleInput;
import ch.uzh.command.Command;
import ch.uzh.command.EndGameCommand;
import ch.uzh.command.NullCommand;
import ch.uzh.dice.DiceManager;
import ch.uzh.dice.MockDiceManager;
import ch.uzh.dice.NormalDiceScoreStrategy;
import ch.uzh.turn.PlayerTurn;


public class CloverleafTurnTest 
{
    CloverleafTurn aCloverleafTurn = new CloverleafTurn();
    PlayerTurn currentTurn = new PlayerTurn();
    MockDiceManager aMockDiceManager = new MockDiceManager(new NormalDiceScoreStrategy());

    private Command invoke_evaluateTurn(CloverleafTurn aCloverleafTurn, PlayerTurn currentTurn) throws Throwable{
        Method method = CloverleafTurn.class.getDeclaredMethod("evaluateTurn", PlayerTurn.class);
        method.setAccessible(true);
        return (Command) method.invoke(aCloverleafTurn, currentTurn);
    }

    private void invoke_computeTurn(CloverleafTurn aCloverleafTurn, PlayerTurn currentTurn, DiceManager aDiceManager) throws Throwable{
        Method method = CloverleafTurn.class.getDeclaredMethod("computeTurn", PlayerTurn.class, DiceManager.class);
        method.setAccessible(true);
        method.invoke(aCloverleafTurn, currentTurn, aDiceManager);
    }

    private DiceManager invoke_turnLoop(CloverleafTurn aCloverleafTurn, DiceManager aDiceManager, ConsoleInput aConsoleInput) throws Throwable{
        Method method = CloverleafTurn.class.getDeclaredMethod("turnLoop", DiceManager.class, ConsoleInput.class);
        method.setAccessible(true);
        return (DiceManager) method.invoke(aCloverleafTurn, aDiceManager, aConsoleInput);
    }

    @Test
    void testEvaluateTurn_active() {
        try {
            Command aCommand = invoke_evaluateTurn(aCloverleafTurn, currentTurn);
            assertTrue(aCommand instanceof EndGameCommand);
        } catch (Throwable e) {
            fail("Test failed because Exception was raised.");
        }
    }

    @Test
    void testEvaluateTurn_notActive() {
        try {
            Field active = PlayerTurn.class.getDeclaredField("active");
            active.setAccessible(true);
            active.set(currentTurn, false);
            Command aCommand = invoke_evaluateTurn(aCloverleafTurn, currentTurn);
            assertTrue(aCommand instanceof NullCommand);
        } catch (Throwable e) {
            fail("Test failed because Exception was raised.");
        }
    }
    
    @Test
    void testComputeTurn_NullTurn() {
        aMockDiceManager.setHadNullTurn(true);

        try {
            invoke_computeTurn(aCloverleafTurn, currentTurn, aMockDiceManager);
            assertEquals(false, currentTurn.isActive());
        } catch (Throwable e) {
            fail("Test failed because Exception was raised.");
        }
    }

    @Test
    void testComputeTurn_NoNullTurn() {
        try {
            invoke_computeTurn(aCloverleafTurn, currentTurn, new DiceManager(6, null));
            assertEquals(true, currentTurn.isActive());
        } catch (Throwable e) {
            fail("Test failed because Exception was raised.");
        }
    }

    @Test
    void testturnLoop_hadNullTurn() {
        aMockDiceManager.setHadNullTurn(true);
        
        try {
            DiceManager pDiceManager = (DiceManager) invoke_turnLoop(aCloverleafTurn, aMockDiceManager, ConsoleInput.instance());
            assertEquals(aMockDiceManager, pDiceManager);
        } catch (Throwable e) {
            fail("Test failed because Exception was raised.");
        }
    }

    
    @Test
    void testturnLoop_isTutto() {
        aMockDiceManager.setIsTutto(true);

        try {
            DiceManager pDiceManager = (DiceManager) invoke_turnLoop(aCloverleafTurn, aMockDiceManager, ConsoleInput.instance());
            assertEquals(aMockDiceManager, pDiceManager);
        } catch (Throwable e) {
            fail("Test failed because Exception was raised.");
        }
    }
    /*
    void testturnLoop_playTurn()  {
        String[] aStrings = {"R", "R", "R", "R", "R", "R", "R", "R", "R", "R", "R", "R"};
        try {
            ConsoleInput aTestingConsoleInput = TestingConsoleInput.createFakeScannerInstance(aStrings);
            DiceManager aDiceManager = invoke_turnLoop(aCloverleafTurn, aMockDiceManager, aTestingConsoleInput);
            assertTrue(aDiceManager.hadNullTurn() || aDiceManager.isTutto());
        } catch (Throwable e) {
            fail("Test failed because Exception was raised.");
        }
        
    }
    */

    @Test
    void testplayTurn_notActive() {
        try {
            Field active = PlayerTurn.class.getDeclaredField("active");
            active.setAccessible(true);
            active.set(currentTurn, false);
            Command aCommand = aCloverleafTurn.playTurn(currentTurn);
            assertTrue(aCommand instanceof NullCommand);
        } catch (Throwable e) {
            fail("Test failed because Exception was raised.");
        }
    }
    
    
}
