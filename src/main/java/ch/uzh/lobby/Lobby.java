package ch.uzh.lobby;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import ch.uzh.App;

public class Lobby {
    private final List<Player> players;

    private Lobby(List<Player> players) {
        this.players = players;
        // TODO sort by name here
    }

    public static Lobby createNewLobby() {
        List<Player> players;
        if(App.DEBUG_MODE) {
            players = Arrays.asList(new Player[]{
                new Player("P1"),
                new Player("P2")
            });
        } else {
            // TODO implement with Input
            players = new ArrayList<Player>();
        }
        
        return new Lobby(players);
    }

    public Player getNextPlayer() {
        // TODO implement circular getNext over List
        return null;
    }

    public Iterator<Player> playerSortedByRank() {
        // TODO sort a copy of Players and return
        return this.players.iterator();
    }
}
