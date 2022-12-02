package ch.uzh;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import ch.uzh.deck.Card;
import ch.uzh.deck.Deck;
import ch.uzh.lobby.Lobby;
import ch.uzh.lobby.Player;
import ch.uzh.turn.PlayerTurn;

public class TestingGame {
    private TestingGame(){}

    public static Game create(Deck deck, Lobby lobby, int requiredScoreToWin) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        Constructor<Game> constructor = Game.class.getDeclaredConstructor(Deck.class, Lobby.class, Integer.TYPE);
        constructor.setAccessible(true);
        Game game = constructor.newInstance(deck, lobby, requiredScoreToWin);
        return game;
    }
    
    public static void setCurrentTurn(Game game, PlayerTurn currentTurn) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        Field currentTurnField = game.getClass().getDeclaredField("currentTurn");
        currentTurnField.setAccessible(true);
        currentTurnField.set(game, currentTurn);
    }

    public static void awaitRollDice(Game game, ConsoleInput consoleInput) throws Throwable {
        Method method = game.getClass().getDeclaredMethod("awaitRollDice", consoleInput.getClass());
        method.setAccessible(true);
        try {
            method.invoke(game, consoleInput);
        } catch (InvocationTargetException e) {
            throw e.getCause();
        }
    }

    public static void playCardTurn(Game game, Card card, PlayerTurn currentTurn, Player currentPlayer, ConsoleInput input) throws Throwable {
        Method method = game.getClass().getDeclaredMethod("playCardTurn", Card.class, PlayerTurn.class, Player.class, ConsoleInput.class);
        method.setAccessible(true);
        try {
            method.invoke(game, card, currentTurn, currentPlayer, input);
        } catch (InvocationTargetException e) {
            throw e.getCause();
        }
    }

    public static void checkForWinner(Game game, Player currentPlayer, int requiredScoreToWin) throws Throwable {
        Method method = game.getClass().getDeclaredMethod("checkForWinner", Player.class, Integer.TYPE);
        method.setAccessible(true);
        try {
            method.invoke(game, currentPlayer, requiredScoreToWin);
        } catch (InvocationTargetException e) {
            throw e.getCause();
        }
    }
}
