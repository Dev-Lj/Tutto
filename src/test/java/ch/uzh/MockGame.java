package ch.uzh;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import ch.uzh.deck.Deck;
import ch.uzh.lobby.Lobby;
import ch.uzh.turn.PlayerTurn;

public class MockGame {
    private MockGame(){}

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
}
