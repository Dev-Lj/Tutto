package ch.uzh.turn.turnStrategy;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.InvocationTargetException;
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
        CloverleafTurn aCloverleafTurn = new CloverleafTurn();
        PlayerTurn currentTurn = new PlayerTurn();

        try {
            Command aCommand = invoke_evaluateTurn(aCloverleafTurn, currentTurn);
            assertTrue(aCommand instanceof EndGameCommand);
        } catch (Throwable e) {
            fail("Test failed because Exception was raised.");
        }
    }

    @Test
    void testEvaluateTurn_notActive() {
        CloverleafTurn aCloverleafTurn = new CloverleafTurn();
        PlayerTurn currentTurn = new PlayerTurn();

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
    void testComputeTurn_NullTurn() throws Throwable {
        CloverleafTurn aCloverleafTurn = new CloverleafTurn();
        PlayerTurn currentTurn = new PlayerTurn();
        DiceManager aDiceManager = new DiceManager(6, new NormalDiceScoreStrategy());
        
        try {
            Field active = DiceManager.class.getDeclaredField("hadNullTurn");
            active.setAccessible(true);
            active.set(aDiceManager, true);
            invoke_computeTurn(aCloverleafTurn, currentTurn, aDiceManager);

            assertEquals(false, currentTurn.isActive());
        } catch (Throwable e) {
            fail("Test failed because Exception was raised.");
        }
    }

    @Test
    void testComputeTurn_NoNullTurn() throws Throwable {
        CloverleafTurn aCloverleafTurn = new CloverleafTurn();
        PlayerTurn currentTurn = new PlayerTurn();

        invoke_computeTurn(aCloverleafTurn, currentTurn, new DiceManager(6, null));

        assertEquals(true, currentTurn.isActive());
    }

    @Test
    void testturnLoop_hadNullTurn() throws Throwable {
        DiceManager aDiceManager = new DiceManager(6, new NormalDiceScoreStrategy());
        Field field = DiceManager.class.getDeclaredField("hadNullTurn");
        field.setAccessible(true);
        field.set(aDiceManager, true);

        DiceManager pDiceManager = (DiceManager) invoke_turnLoop(new CloverleafTurn(), aDiceManager, ConsoleInput.instance());

        assertEquals(aDiceManager, pDiceManager);
    }

    
    @Test
    void testturnLoop_isTutto() throws Throwable {
        MockDiceManager StubDiceManager = new MockDiceManager( new NormalDiceScoreStrategy());
        StubDiceManager.setIsTutto(true);

        DiceManager pDiceManager = (DiceManager) invoke_turnLoop(new CloverleafTurn(), StubDiceManager, ConsoleInput.instance());

        assertEquals(StubDiceManager, pDiceManager);
    }

    @Test
    void testplayTurn_notActive() throws Throwable {
        CloverleafTurn aCloverleafTurn = new CloverleafTurn();
        PlayerTurn currentTurn = new PlayerTurn();

        Field active = PlayerTurn.class.getDeclaredField("active");
        active.setAccessible(true);
        active.set(currentTurn, false);

        Command aCommand = aCloverleafTurn.playTurn(currentTurn);

        assertTrue(aCommand instanceof NullCommand);
    }
    
    @Test
    void testturnLoop_playTurn()  {
        String[] aStrings = {"R", "R", "R", "R", "R", "R"};
        try {
            ConsoleInput aTestingConsoleInput = TestingConsoleInput.createFakeScannerInstance(aStrings);
            DiceManager aDiceManager = invoke_turnLoop(new CloverleafTurn(), new DiceManager(6, new NormalDiceScoreStrategy()), aTestingConsoleInput);
            assertTrue(aDiceManager.hadNullTurn() || aDiceManager.isTutto());
        } catch (Throwable e) {
            fail("Test failed because Exception was raised.");
        }
        
    }
    
}
