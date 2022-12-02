package ch.uzh.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import ch.uzh.Game;
import ch.uzh.TestingGame;
import ch.uzh.deck.Deck;
import ch.uzh.deck.MockDeck;
import ch.uzh.lobby.Lobby;
import ch.uzh.lobby.MockLobby;
import ch.uzh.lobby.Player;

public class PlusMinusCommandTest {
    PlusMinusCommand testCommand = new PlusMinusCommand();
    private List<Player> players;
    private Lobby mockLobby;
    private Game mockGame;
    

    public PlusMinusCommandTest() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        players = Arrays.asList(new Player[]{new Player("a"), new Player("b"), new Player("c")});
        mockLobby = MockLobby.create(players);
        mockGame = TestingGame.create(MockDeck.create(Deck.getDefaultCards()), mockLobby, 2000);
    }



    @Test
    void testExecute() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        players.get(0).addScore(0);
        players.get(1).addScore(1000);
        players.get(2).addScore(0);
        testCommand.execute(mockGame, players.get(0));
        assertEquals(1000, players.get(0).getScore());
        assertEquals(0, players.get(1).getScore());
        assertEquals(0, players.get(2).getScore());
    }

    @Test
    void testExecute_CurrentPlayerIsWinner() {
        players.get(0).addScore(1000);
        players.get(1).addScore(500);
        players.get(2).addScore(500);
        testCommand.execute(mockGame, players.get(0));
        assertEquals(2000, players.get(0).getScore());
        assertEquals(500, players.get(1).getScore());
        assertEquals(500, players.get(2).getScore());
    }

    @Test
    void testExecute_MultipleWinners() {
        players.get(0).addScore(1000);
        players.get(1).addScore(1000);
        players.get(2).addScore(1000);
        testCommand.execute(mockGame, players.get(0));
        assertEquals(2000, players.get(0).getScore());
        assertEquals(0, players.get(1).getScore());
        assertEquals(0, players.get(2).getScore());
    }


}
