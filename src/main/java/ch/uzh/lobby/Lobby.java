package ch.uzh.lobby;

import java.util.*;

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
            String name = getUniquePlayerName(players, input, i, nrOfPlayers);
            Player player = new Player(name);
            players.add(player);
        }
        return players;
    }

    private static String getUniquePlayerName(List<Player> players, ConsoleInput input, int currentPlayerNr, int nrOfPlayers) {
        List<String> playerNames = new ArrayList<>();
        for(Player player : players) {
            playerNames.add(player.getName());
        }
        String name = null;
        do {
            if (name != null) {
                System.out.println("Name " + name + " is already taken.");
            }
            name = readPlayerName(input, currentPlayerNr, nrOfPlayers);
        } while(playerNames.contains(name));
        return name;
    }

    private static String readPlayerName(ConsoleInput input, int currentPlayerNr, int nrOfPlayers) {
        return input.getStringInput(1, 20, String.format("Enter name of player %d/%d: ", currentPlayerNr, nrOfPlayers));
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
        List<String> output = new ArrayList<>();
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
