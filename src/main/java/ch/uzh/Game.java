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
            requiredScoreToWin = ConsoleInput.instance().getNumberInput(0, 10000, "Amount of points to win the game: ");
        }
        return new Game(Deck.createDefaultDeck(), Lobby.createNewLobby() ,requiredScoreToWin);
    }

    private void printPlayerRanking() {
        Iterator<Player> rankedPlayers = this.lobby.getPlayersSortedByRank();
        int rank = 1;
        while (rankedPlayers.hasNext()) {
            Player currentRankPlayer = rankedPlayers.next();
            System.out.println(String.format("%d. %s: %d Points", rank++, currentRankPlayer.getName(), currentRankPlayer.getScore()));
        }
    }

    private void awaitRollDice() {
        boolean rolledDice = false;
        while (!rolledDice) {
            char input = ConsoleInput.instance().getCharacterInput(new Character[]{'R', 'D'}, "Roll the dice or display current scores? (R/D)");
            if (input == 'D') {
                printPlayerRanking();
            } else {
                rolledDice = true;
            }

        }
    }

    /**
     * Main game loop
     */
    public void play() {
        while (!finished) {
            Player currentPlayer = lobby.getNextPlayer();
            System.out.println(String.format("Hey, %s it is your turn.", currentPlayer.getName()));
            awaitRollDice();
            // TODO Don't like this, tight coupling, improve
            currentTurn = new PlayerTurn(currentPlayer, this);
            currentTurn.playTurn();
            currentPlayer.addScore(currentTurn.getScore());
            if (currentPlayer.getScore() >= requiredScoreToWin) {
                // TODO finish round
                this.end(currentPlayer);
            }
        }
        printPlayerRanking();
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