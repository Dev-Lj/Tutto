package ch.uzh.lobby;

import ch.uzh.deck.Deck;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Arrays;

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
}