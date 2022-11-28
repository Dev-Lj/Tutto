package ch.uzh.lobby;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.InputMismatchException;    // TODO why not import java.util.*
import java.util.Scanner;   // TODO coherent use of scanners

import ch.uzh.App;

public class Lobby {
    private final static int MIN_PLAYERS = 2;
    private final static int MAX_PLAYERS = 4;
    private final List<Player> players;
    private Optional<Player> winner = Optional.empty();

    private Lobby(List<Player> players) {
        this.players = players;
        // TODO sort by name here
    }

    public static Lobby createNewLobby() {
        List<Player> players;
        if(App.DEBUG_MODE) {
            players = Arrays.asList(new Player("P1"), new Player("P2"));
        } else {
            int nrOfPlayers = readNumberOfPlayers();
            players = createPlayers(nrOfPlayers);
        }
        
        return new Lobby(players);
    }

    private static int readNumberOfPlayers() {
        boolean hasError = true;
        int playerCount = 0;
        Scanner scanner = new Scanner(System.in);
        while (hasError) {
            try {
                System.out.print("Number of players [2-4]: ");
                playerCount = scanner.nextInt();
                if(playerCount < MIN_PLAYERS || playerCount > MAX_PLAYERS) {
                    throw new InputMismatchException();
                }
                hasError = false;
            } catch(InputMismatchException e) {
                System.out.println("Invalid input.");
                scanner.nextLine();
                hasError = true;
            }
        }
        //scanner.close();
        return playerCount;
    }

    private static List<Player> createPlayers(int nrOfPlayers) {
        List<Player> players = new ArrayList<Player>();
        Scanner scanner = new Scanner(System.in);
        for(int i = 1; i <= nrOfPlayers; i++) {
            boolean hasError = true;
            while (hasError) {
                try {
                    System.out.print("Enter name of player " + i + "/" + nrOfPlayers + ": ");
                    String name = scanner.nextLine();
                    for(Player player : players) {
                        if(name.equals(player.getName()) || name.isBlank()) {
                            throw new InputMismatchException();
                        }
                    }
                    Player player = new Player(name);
                    players.add(player);
                    hasError = false;
                } catch(InputMismatchException e) {
                    System.out.println("Invalid input.");
                    hasError = true;
                }
            }
        }
        //scanner.close();
        return players;
    }

    public Player getNextPlayer() {
        // TODO implement circular getNext over List
        return null;
    }

    public Iterator<Player> playerSortedByRank() {
        // TODO sort a copy of Players and return. Winner is on top if not empty
        return this.players.iterator();
    }

    public void setWinner(Player winner) {
        this.winner = Optional.of(winner);
    }
}
