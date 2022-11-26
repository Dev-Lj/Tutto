package ch.uzh;

import java.util.Scanner;

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

    private static int readRequiredScore() {
        boolean hasError = false;
        int requiredScoreToWin = 0;
        Scanner myInput = new Scanner( System.in );
        while (hasError) {
            try {
                System.out.print( "Amount of points to win the game: " );
                requiredScoreToWin = myInput.nextInt();
                hasError = false;
            } catch (Exception e) {
                System.out.println("Invalid Input");
                hasError = true;
            }
        }
        myInput.close();
        return requiredScoreToWin;
    }

    public static Game createNewGame() {
        int requiredScoreToWin;
        if (App.DEBUG_MODE) {
            requiredScoreToWin = 6000;
        } else {
            requiredScoreToWin = readRequiredScore();
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
        // TODO add finished output
    }

    public void end(Player winner) {
        // TODO set winner
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