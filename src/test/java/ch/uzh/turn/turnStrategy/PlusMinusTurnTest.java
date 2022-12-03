package ch.uzh.turn.turnStrategy;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import ch.uzh.ConsoleInput;
import ch.uzh.TestingConsoleInput;
import ch.uzh.command.Command;
import ch.uzh.command.NullCommand;
import ch.uzh.command.PlusMinusCommand;
import ch.uzh.dice.DiceManager;
import ch.uzh.dice.MockDiceManager;
import ch.uzh.dice.NormalDiceScoreStrategy;
import ch.uzh.dice.StraightDiceScoreStrategy;




import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Unit test for simple App.
 */
public class PlusMinusTurnTest 
{
    private Command invoke_evaluateTurn(PlusMinusTurn aPlusMinusTurn, DiceManager aDiceManager) throws Throwable{
        Method method = PlusMinusTurn.class.getDeclaredMethod("evaluateTurn", DiceManager.class);
        method.setAccessible(true);
        return (Command) method.invoke(aPlusMinusTurn, aDiceManager);

    }

    private DiceManager invoke_turnLoop(PlusMinusTurn aPlusMinusTurn, DiceManager aDiceManager, ConsoleInput aConsoleInput) throws Throwable{
        Method method = PlusMinusTurn.class.getDeclaredMethod("turnLoop", DiceManager.class, ConsoleInput.class);
        method.setAccessible(true);
        try {
            return (DiceManager) method.invoke(aPlusMinusTurn, aDiceManager, aConsoleInput);
        } catch (InvocationTargetException e) {
            throw e.getCause();
        }
    }

    /**
     * @throws Throwable
     */
    @Test
    void testEvaluateTurn_Tutto() throws Throwable {
        PlusMinusTurn aPlusMinusTurn = new PlusMinusTurn();
        MockDiceManager StubDiceManager = new MockDiceManager( new StraightDiceScoreStrategy());
        StubDiceManager.setIsTutto(true);

        Command aCommand = invoke_evaluateTurn(aPlusMinusTurn, (DiceManager) StubDiceManager);

        assertEquals(1000, aPlusMinusTurn.getScore());
        assertTrue(aCommand instanceof PlusMinusCommand);
    }

    /**
     * @throws Throwable
     */
    @Test
    void testEvaluateTurn_noTutto() throws Throwable {
        PlusMinusTurn aPlusMinusTurn = new PlusMinusTurn();
        MockDiceManager StubDiceManager = new MockDiceManager( new StraightDiceScoreStrategy());
        StubDiceManager.setIsTutto(false);

        Command aCommand = invoke_evaluateTurn(aPlusMinusTurn, (DiceManager) StubDiceManager);

        assertEquals(0, aPlusMinusTurn.getScore());
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

        DiceManager pDiceManager = (DiceManager) invoke_turnLoop(new PlusMinusTurn(), aDiceManager, ConsoleInput.instance());

        assertEquals(aDiceManager, pDiceManager);
    }

    /**
     * @throws Throwable
     */
    @Test
    void testturnLoop_isTutto() throws Throwable {
        MockDiceManager StubDiceManager = new MockDiceManager( new StraightDiceScoreStrategy());
        StubDiceManager.setIsTutto(true);

        DiceManager pDiceManager = (DiceManager) invoke_turnLoop(new PlusMinusTurn(), StubDiceManager, ConsoleInput.instance());

        assertEquals(StubDiceManager, pDiceManager);
    }
     /* 
    @Test
    void turnLoop_playTurn() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, Throwable {
        String[] aStrings = {"R", "R", "R", "R", "R", "R"};
        ConsoleInput aTestingConsoleInput = TestingConsoleInput.createInstance(aStrings);
        
        DiceManager aDiceManager = invoke_turnLoop(new PlusMinusTurn(), new DiceManager(6, new NormalDiceScoreStrategy()), aTestingConsoleInput);
        assertTrue(aDiceManager.hadNullTurn() || aDiceManager.isTutto());
    }
    */
}
