package ch.uzh.lobby;

import ch.uzh.deck.Deck;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LobbyTest {
    /*List<Player> players = Arrays.asList(new Player("")); // TODO is it okay to use real players...?
    Lobby lobby = MockLobby.create();
    Lobby createLobby() {
        Player p1 = new Player("Ahmed");
        Player p2 = new Player("");
    }*/


    void setUserInput(String input) {
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
    }

    // TODO how to test because userInput always gets tested along...???
    @Test
    void testCreateNewLobby() throws NoSuchFieldException {
        setUserInput("2\nMichel\nDavid\n");
        Lobby lobby = Lobby.createNewLobby();
        Field playersField = Lobby.class.getDeclaredField("players");
        //Metaprogramming set nrOfPlayers
    }

    @Test
    void testGetNextPlayer() {
        // use mock lobby
    }

    @Test
    void testGetPlayersSortedByRank() { // TODO WHAT TO DO HEREEEEEE :(
        // use mock lobby
    }

    @Test
    void testSetWinner() {
        // use mock lobby?
    }

    @Test
    void getPlayerRankingString() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        List<Player> players = Arrays.asList(new Player[]{new Player("A"), new Player("B"), new Player("C")});
        players.get(0).addScore(1);
        players.get(1).addScore(2);
        players.get(2).addScore(3);
        Lobby lobby = TestingLobby.create(players);
        String expected = "1. C: 3 Points\n2. B: 2 Points\n3. A: 1 Points";
        assertEquals(expected, lobby.getPlayerRankingString());
    }
}