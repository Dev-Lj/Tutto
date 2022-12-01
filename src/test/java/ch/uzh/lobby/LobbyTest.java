package ch.uzh.lobby;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

public class LobbyTest {
    void setUserInput(String input) {
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
    }

    @Test
    void testCreateNewLobby() {
        setUserInput("2\nMichel\nDavid\n");
        Lobby lobby = Lobby.createNewLobby();
        //Metaprogramming set nrOfPlayers
    }

    @Test
    void testGetNextPlayer() {
    }

    @Test
    void testGetPlayersSortedByRank() {
    }

    @Test
    void testSetWinner() {
    }
}