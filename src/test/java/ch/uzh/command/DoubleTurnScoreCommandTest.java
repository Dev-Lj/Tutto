package ch.uzh.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import ch.uzh.Game;
import ch.uzh.MockGame;
import ch.uzh.deck.Deck;
import ch.uzh.deck.MockDeck;
import ch.uzh.lobby.MockLobby;
import ch.uzh.lobby.Player;
import ch.uzh.turn.PlayerTurn;

public class DoubleTurnScoreCommandTest {
    DoubleTurnScoreCommand testCommand = new DoubleTurnScoreCommand();

    @Test
    void testExecute() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, NoSuchFieldException {
        List<Player> players = Arrays.asList(new Player("test 1"), new Player("test 2"));
        Game testGame = MockGame.create(MockDeck.create(Deck.getDefaultCards()), MockLobby.create(players), 10);
        PlayerTurn currentTurn = new PlayerTurn(players.get(0), testGame);
        MockGame.setCurrentTurn(testGame, currentTurn);
        currentTurn.setScore(1);
        testCommand.execute(testGame, players.get(0));
        assertEquals(2, currentTurn.getScore());
    }
}
