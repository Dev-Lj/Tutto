package ch.uzh.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import ch.uzh.Game;
import ch.uzh.TestingGame;
import ch.uzh.deck.Deck;
import ch.uzh.deck.TestingDeck;
import ch.uzh.lobby.Lobby;
import ch.uzh.lobby.MockLobby;
import ch.uzh.lobby.Player;

public class EndGameCommandTest {
    @Test
    void testExecute() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        List<Player> players = Arrays.asList(new Player[]{new Player("a"), new Player("b")});
        Lobby testLobby = MockLobby.create(players);
        Game mockGame = TestingGame.create(TestingDeck.create(Deck.getDefaultCards()), testLobby , 12);
        EndGameCommand command = new EndGameCommand();
        command.execute(mockGame, players.get(0));
        assertEquals(players.get(0), testLobby.getPlayersSortedByRank().next());
        assertEquals(true, mockGame.isFinished());
    }
}
