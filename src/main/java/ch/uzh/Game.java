package ch.uzh;

import java.util.Iterator;

import ch.uzh.deck.Deck;
import ch.uzh.lobby.Lobby;
import ch.uzh.lobby.Player;
import ch.uzh.turn.PlayerTurn;

/**
 * Game
 */
public class Game {
    private final Deck deck;
    private final Lobby lobby;
    private final int requiredScoreToWin;
    private boolean finished = false;
    private PlayerTurn currentTurn;

    private Game(Deck deck, Lobby lobby, int requiredScoreToWin) {
        this.deck = deck;
        this.lobby = lobby;
        this.requiredScoreToWin = requiredScoreToWin;
    }

    public static Game createNewGame() {
        int requiredScoreToWin;
        if (App.DEBUG_MODE) {
            requiredScoreToWin = 6000;
        } else {
            requiredScoreToWin = Player.getNumberInput(0, 10000, "Amount of points to win the game: ");
        }
        return new Game(Deck.createDefaultDeck(), Lobby.createNewLobby() ,requiredScoreToWin);
    }

    /**
     * Main game loop
     */
    public void play() {
        while (!finished) {
            Player currentPlayer = lobby.getNextPlayer();
            // TODO Don't like this, tight coupling, improve
            currentTurn = new PlayerTurn(currentPlayer, this);
            currentTurn.playTurn();
            currentPlayer.addScore(currentTurn.getScore());
            if (currentPlayer.getScore() >= requiredScoreToWin) {
                // TODO finish round
                this.end(currentPlayer);
            }
        }
        Iterator<Player> rankedPlayers = this.lobby.getPlayersSortedByRank();
        int rank = 1;
        while (rankedPlayers.hasNext()) {
            Player currentRankPlayer = rankedPlayers.next();
            System.out.println(String.format("%d. %s: %d Points", rank++, currentRankPlayer.getName(), currentRankPlayer.getScore()));
        }
    }

    public void end(Player winner) {
        this.lobby.setWinner(winner);
        this.finished = true;
    }

    public Deck getDeck() {
        return deck;
    }

    public Lobby getLobby() {
        return lobby;
    }

    public PlayerTurn getCurrentTurn() {
        return currentTurn;
    }
}