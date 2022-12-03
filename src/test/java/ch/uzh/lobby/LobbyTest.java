package ch.uzh.lobby;

import ch.uzh.ConsoleInput;
import ch.uzh.TestingConsoleInput;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

public class LobbyTest {
    public LobbyTest() throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
    }

    private final Player p1 = new Player("P1");
    private final Player p2 = new Player("P2");
    private final Player p3 = new Player("P3");
    Lobby lobby = TestingLobby.create(Arrays.asList(p1, p2, p3));

    @Test
    void testCreateNewLobby() throws Throwable {
        ConsoleInput input = TestingConsoleInput.createFakeScannerInstance(new String[]{"3", "P1", "P2", "P3"});
        Method createLobbyMethod = Lobby.class.getDeclaredMethod("createNewLobby", ConsoleInput.class);
        createLobbyMethod.setAccessible(true);
        Lobby lobby = (Lobby) createLobbyMethod.invoke(null, input);
        Field playersField = Lobby.class.getDeclaredField("players");
        playersField.setAccessible(true);
        List<Player> players = (List<Player>) playersField.get(lobby);
        assertEquals(3, players.size());
    }

    @Test
    void testGetUniquePlayerName() throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        String newName = "P4";
        ConsoleInput input = TestingConsoleInput.createFakeScannerInstance(new String[]{p1.getName(), p2.getName(), newName});
        Method testUniquePlayerNameMethod = Lobby.class.getDeclaredMethod("getUniquePlayerName", List.class, ConsoleInput.class, int.class, int.class);
        testUniquePlayerNameMethod.setAccessible(true);
        List<Player> players = Arrays.asList(p1, p2, p3);
        String name = (String) testUniquePlayerNameMethod.invoke(null, players, input, 3, 4);
        assertEquals(newName, name);
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

    @Test
    void getPlayerRankingString() throws IllegalArgumentException, SecurityException {
        p1.addScore(300);
        p2.addScore(200);
        p3.addScore(100);
        String expected = "1. P1: 300 Points\n2. P2: 200 Points\n3. P3: 100 Points";
        assertEquals(expected, lobby.getPlayerRankingString());
    }
}