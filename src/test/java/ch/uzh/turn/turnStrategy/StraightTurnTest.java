package ch.uzh.turn.turnStrategy;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import ch.uzh.ConsoleInput;
import ch.uzh.TestingConsoleInput;
import ch.uzh.command.Command;
import ch.uzh.command.NullCommand;
import ch.uzh.dice.DiceManager;
import ch.uzh.dice.MockDiceManager;
import ch.uzh.dice.StraightDiceScoreStrategy;
import ch.uzh.turn.PlayerTurn;

import java.lang.reflect.Method;



/**
 * Unit test for simple App.
 */
public class StraightTurnTest 
{
    StraightTurn aStraightTurn = new StraightTurn();
    MockDiceManager aMockDiceManager = new MockDiceManager( new StraightDiceScoreStrategy());
    
    private Command invoke_evaluateTurn(StraightTurn aStraightTurn, PlayerTurn currentTurn, DiceManager aDiceManager) throws Throwable{
        Method method = StraightTurn.class.getDeclaredMethod("evaluateTurn", PlayerTurn.class, DiceManager.class);
        method.setAccessible(true);
        return (Command) method.invoke(aStraightTurn, currentTurn, aDiceManager);
    }

    private DiceManager invoke_turnLoop(StraightTurn aStraightTurn, DiceManager aDiceManager, ConsoleInput aConsoleInput) throws Throwable{
        Method method = StraightTurn.class.getDeclaredMethod("turnLoop", DiceManager.class, ConsoleInput.class);
        method.setAccessible(true);
        return (DiceManager) method.invoke(aStraightTurn, aDiceManager, aConsoleInput);
    }


    @Test
    void testEvaluateTurn_Tutto() {
        aMockDiceManager.setIsTutto(true);

        try {
            Command aCommand = invoke_evaluateTurn(aStraightTurn, new PlayerTurn(), aMockDiceManager);
            assertEquals(2000, aStraightTurn.getScore());
            assertTrue(aCommand instanceof NullCommand);
        } catch (Throwable e) {
            fail(e.getCause());
        }       
    }

    @Test
    void testEvaluateTurn_noTutto() {
        aMockDiceManager.setIsTutto(false);

        try {
            Command aCommand = invoke_evaluateTurn(aStraightTurn, new PlayerTurn(), aMockDiceManager);
            assertEquals(0, aStraightTurn.getScore());
            assertTrue(aCommand instanceof NullCommand);
        } catch (Throwable e) {
            fail(e.getCause());
        } 
    }

    @Test
    void testturnLoop_hadNullTurn() {
        aMockDiceManager.setHadNullTurn(true);

        try {
            DiceManager pDiceManager = (DiceManager) invoke_turnLoop(aStraightTurn, aMockDiceManager, ConsoleInput.instance());
            assertEquals(aMockDiceManager, pDiceManager);
        } catch (Throwable e) {
            fail(e.getCause());
        }         
    }

    @Test
    void testturnLoop_isTutto()  {
        aMockDiceManager.setIsTutto(true);

        try {
            DiceManager pDiceManager = (DiceManager) invoke_turnLoop(aStraightTurn, aMockDiceManager, ConsoleInput.instance());
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
            DiceManager aDiceManager = invoke_turnLoop(aStraightTurn, new DiceManager(6, new StraightDiceScoreStrategy()), aTestingConsoleInput);
            assertEquals(aDiceManager.getScore(), aStraightTurn.getScore());
        } catch (Throwable e) {
            fail(e.getCause());
        }    
    }
    
    
    
    
}


