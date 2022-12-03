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

import java.lang.reflect.Method;


public class PlusMinusTurnTest 
{
    MockDiceManager aMockDiceManager = new MockDiceManager( new StraightDiceScoreStrategy());
    PlusMinusTurn aPlusMinusTurn = new PlusMinusTurn();

    private Command invoke_evaluateTurn(PlusMinusTurn aPlusMinusTurn, DiceManager aDiceManager) throws Throwable{
        Method method = PlusMinusTurn.class.getDeclaredMethod("evaluateTurn", DiceManager.class);
        method.setAccessible(true);
        return (Command) method.invoke(aPlusMinusTurn, aDiceManager);

    }

    private DiceManager invoke_turnLoop(PlusMinusTurn aPlusMinusTurn, DiceManager aDiceManager, ConsoleInput aConsoleInput) throws Throwable{
        Method method = PlusMinusTurn.class.getDeclaredMethod("turnLoop", DiceManager.class, ConsoleInput.class);
        method.setAccessible(true);
        return (DiceManager) method.invoke(aPlusMinusTurn, aDiceManager, aConsoleInput);
    }

    @Test
    void testEvaluateTurn_Tutto() {
        aMockDiceManager.setIsTutto(true);

        try {
            Command aCommand = invoke_evaluateTurn(aPlusMinusTurn, (DiceManager) aMockDiceManager);
            assertEquals(1000, aPlusMinusTurn.getScore());
            assertTrue(aCommand instanceof PlusMinusCommand);
        } catch (Throwable e) {
            fail("Test failed because Exception was raised.");
        }
    }

    @Test
    void testEvaluateTurn_noTutto() {
        aMockDiceManager.setIsTutto(false);
        
        try {
            Command aCommand = invoke_evaluateTurn(aPlusMinusTurn, (DiceManager) aMockDiceManager);
            assertEquals(0, aPlusMinusTurn.getScore());
            assertTrue(aCommand instanceof NullCommand);
        } catch (Throwable e) {
            fail("Test failed because Exception was raised.");
        }       
    }

    @Test
    void testturnLoop_hadNullTurn() {
        aMockDiceManager.setHadNullTurn(true);

        try {
            DiceManager pDiceManager = (DiceManager) invoke_turnLoop(aPlusMinusTurn, aMockDiceManager, ConsoleInput.instance());
            assertEquals(aMockDiceManager, pDiceManager);
        } catch (Throwable e) {
            fail("Test failed because Exception was raised.");
        }
    }

    @Test
    void testturnLoop_isTutto() {
        aMockDiceManager.setIsTutto(true);

        try {
            DiceManager pDiceManager = (DiceManager) invoke_turnLoop(aPlusMinusTurn, aMockDiceManager, ConsoleInput.instance());
            assertEquals(aMockDiceManager, pDiceManager);
        } catch (Throwable e) {
            fail("Test failed because Exception was raised.");
        } 
    }
     
    @Test
    void turnLoop_playTurn() {
        String[] aStrings = {"R", "R", "R", "R", "R", "R"};

        try {
            ConsoleInput aTestingConsoleInput = TestingConsoleInput.createFakeScannerInstance(aStrings);
        
            DiceManager aDiceManager = invoke_turnLoop(aPlusMinusTurn, new DiceManager(6, new NormalDiceScoreStrategy()), aTestingConsoleInput);
            assertTrue(aDiceManager.hadNullTurn() || aDiceManager.isTutto());
        } catch (Throwable e) {
            fail("Test failed because Exception was raised.");
        }
    }
    
}
