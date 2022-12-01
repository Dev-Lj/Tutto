package ch.uzh.turn.turnStrategy;

import org.junit.jupiter.api.Test;

import ch.uzh.ConsoleInput;
import ch.uzh.command.Command;
import ch.uzh.command.NullCommand;
import ch.uzh.dice.DiceManager;
import ch.uzh.dice.MockDiceManager;
import ch.uzh.dice.NormalDiceScoreStrategy;
import ch.uzh.dice.StraightDiceScoreStrategy;
import ch.uzh.turn.PlayerTurn;

import static org.junit.jupiter.api.Assertions.*;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;



/**
 * Unit test for simple App.
 */
public class StraightTurnTest 
{
    
    private Command invoke_evaluateTurn(PlayerTurn currentTurn, DiceManager aDiceManager) throws Throwable{
        Method method = StraightTurn.class.getDeclaredMethod("evaluateTurn", PlayerTurn.class, DiceManager.class);
        method.setAccessible(true);
        try {
            return (Command) method.invoke(new StraightTurn(), new PlayerTurn(), aDiceManager);
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
        
        MockDiceManager StubDiceManager = new MockDiceManager( new StraightDiceScoreStrategy());
        StubDiceManager.setIsTutto(true);

        

        Command aCommand = invoke_evaluateTurn(new PlayerTurn(), (DiceManager) StubDiceManager);

        assert(aCommand instanceof NullCommand);
    }

    /**
     * @throws Throwable
     */
    @Test
    void testEvaluateTurn_noTutto() throws Throwable {
        
        MockDiceManager StubDiceManager = new MockDiceManager( new StraightDiceScoreStrategy());
        StubDiceManager.setIsTutto(true);

        Command aCommand = invoke_evaluateTurn(new PlayerTurn(), StubDiceManager);


        assert(aCommand instanceof NullCommand);
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

}
