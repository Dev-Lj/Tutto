package ch.uzh.lobby;

import java.util.*;

import ch.uzh.App;
import ch.uzh.ConsoleInput;

public class Lobby {
    private final static int MIN_PLAYERS = 2;
    private final static int MAX_PLAYERS = 4;
    private final List<Player> players;
    private int currentPlayerIndex = -1;
    private Optional<Player> winner = Optional.empty();

    private Lobby(List<Player> players) {
        this.players = players;
        Collections.sort(this.players);
    }

    public static Lobby createNewLobby() {
        return createNewLobby(ConsoleInput.instance());
    }

    private static Lobby createNewLobby(ConsoleInput input) {
        int nrOfPlayers = readNumberOfPlayers(input);
        List<Player> players = createPlayers(input, nrOfPlayers);
        return new Lobby(players);
    }


    private static int readNumberOfPlayers(ConsoleInput input) {
        return input.getNumberInput(MIN_PLAYERS, MAX_PLAYERS, String.format("Number of players [%d-%d]: ", MIN_PLAYERS, MAX_PLAYERS));
    }

    private static List<Player> createPlayers(ConsoleInput input, int nrOfPlayers) {
        List<Player> players = new ArrayList<>();
        for(int i = 1; i <= nrOfPlayers; i++) {
            String name = readPlayerName(input, i, nrOfPlayers);
            Player player = new Player(name);
            players.add(player);
        }
        return players;
    }

    private static String readPlayerName(ConsoleInput input, int currentPlayer, int totalPlayers) {
        return input.getStringInput(1, 20, String.format("Enter name of player %d/%d: ", currentPlayer, totalPlayers));
    }

    public Player getNextPlayer() {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        return players.get(currentPlayerIndex);
    }

    public Iterator<Player> getPlayersSortedByRank() {
        List<Player> playersSortedByRank = new ArrayList<>(players);
        Collections.sort(playersSortedByRank, Player.RankComparator);
        if(winner.isPresent()) {
            playersSortedByRank.remove(winner.get());
            playersSortedByRank.add(0, winner.get());
        }
        return playersSortedByRank.iterator();
    }

    public String getPlayerRankingString() {
        Iterator<Player> rankedPlayers = getPlayersSortedByRank();
        int rank = 1;
        List<String> output = new ArrayList<String>();
        while (rankedPlayers.hasNext()) {
            Player currentRankPlayer = rankedPlayers.next();
            output.add(currentRankPlayer.toRankingString(rank++));
        }
        return String.join("\n", output);
    }

    public void setWinner(Player winner) {
        this.winner = Optional.of(winner);
    }
}
