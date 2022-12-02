package ch.uzh.lobby;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LobbyTest {
    public LobbyTest() throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
    }

    void setUserInput(String input) {
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
    }

    private final Player p1 = new Player("P1");
    private final Player p2 = new Player("P2");
    private final Player p3 = new Player("P3");
    Lobby lobby = MockLobby.create(Arrays.asList(p1, p2, p3));

    @Test
    void testCreateNewLobby() throws Throwable {
        setUserInput("3\nNataell\nJan\nDavid\n");   // TODO what should I do here ...
        Lobby lobby = Lobby.createNewLobby();
        Field playersField = Lobby.class.getDeclaredField("players");
        playersField.setAccessible(true);
        List<Player> players = (List<Player>) playersField.get(lobby);
        assertEquals(3, players.size());
    }

    @Test
    void testGetNextPlayer() {
        lobby.getNextPlayer();
        Player player = lobby.getNextPlayer();
        assertSame(player, p2);
    }

    @Test
    void testGetNextPlayer_wrapAround() {
        lobby.getNextPlayer();
        lobby.getNextPlayer();
        lobby.getNextPlayer();
        Player player = lobby.getNextPlayer();
        assertSame(player, p1);
    }

    @Test
    void testGetPlayersSortedByRank() {
        p1.addScore(100);
        p2.addScore(300);
        p3.addScore(200);
        Iterator<Player> sortedPlayers = lobby.getPlayersSortedByRank();
        assertEquals(sortedPlayers.next(), p2);
        assertEquals(sortedPlayers.next(), p3);
        assertEquals(sortedPlayers.next(), p1);
    }

    @Test
    void testGetPlayersSortedByRank_manuallySetWinner() {
        p1.addScore(100);
        p2.addScore(300);
        p3.addScore(200);
        lobby.setWinner(p1);
        Iterator<Player> sortedPlayers = lobby.getPlayersSortedByRank();
        assertEquals(sortedPlayers.next(), p1);
        assertEquals(sortedPlayers.next(), p2);
        assertEquals(sortedPlayers.next(), p3);
    }
}