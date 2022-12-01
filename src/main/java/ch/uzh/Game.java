package ch.uzh;

import java.util.Iterator;

import ch.uzh.command.Command;
import ch.uzh.deck.Card;
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

    private void playTurn(Player currentPlayer, PlayerTurn currentTurn) {
        while (currentTurn.isActive()) {
            Card card = deck.drawCard();
            Command postTurnCommand = currentTurn.playTurn(card);
            postTurnCommand.execute(this, currentPlayer);
            if (currentTurn.isActive()) {
                char playerInput = ConsoleInput.instance().getCharacterInput(new Character[] {'D', 'E'}, "Draw new Card (D) or end turn (E): ");
                if (playerInput == 'E') {
                    currentTurn.endTurn();
                }
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
            PlayerTurn currentTurn = new PlayerTurn();
            playTurn(currentPlayer, currentTurn);
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
}