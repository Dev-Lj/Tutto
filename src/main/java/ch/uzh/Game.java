package ch.uzh;

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
        int requiredScoreToWin = ConsoleInput.instance().getNumberInput(1, 10000, "Amount of points to win the game: ");
        return new Game(Deck.createDefaultDeck(), Lobby.createNewLobby() ,requiredScoreToWin);
    }

    private void printPlayerRanking() {
        System.out.println(this.lobby.getPlayerRankingString());
    }

    /**
     * @pre consoleInput!=null
     * @param consoleInput
     */
    private void awaitRollDice(ConsoleInput consoleInput) {
        assert consoleInput != null;
        boolean rolledDice = false;
        while (!rolledDice) {
            char input = consoleInput.getCharacterInput(new Character[]{'R', 'D'}, "Roll the dice or display current scores? (R/D)");
            if (input == 'D') {
                printPlayerRanking();
            } else {
                rolledDice = true;
            }
        }
    }

    /**
     * 
     * @param card
     * @param currentTurn
     * @param currentPlayer
     * @param input
     * @pre card != null && currentTurn!=null && currentPlayer != null && input != null
     */
    private void playCardTurn(Card card, PlayerTurn currentTurn, Player currentPlayer, ConsoleInput input) {
        assert card != null && currentTurn!=null && currentPlayer != null && input != null;
        Command postTurnCommand = currentTurn.playTurn(card);
        postTurnCommand.execute(this, currentPlayer);
        if (currentTurn.isActive()) {
            char playerInput = input.getCharacterInput(new Character[] {'D', 'E'}, "Draw new Card (D) or end turn (E): ");
            if (playerInput == 'E') {
                currentTurn.endTurn();
            }
        }
    }

    private void playPlayerTurn(PlayerTurn currentTurn, Player currentPlayer, Deck myDeck, ConsoleInput input) {
        System.out.println(String.format("Hey, %s it is your turn.", currentPlayer.getName()));
        awaitRollDice(ConsoleInput.instance());
        while (currentTurn.isActive()) {
            Card card = myDeck.drawCard();
            playCardTurn(card, currentTurn, currentPlayer, input);
        }
        currentPlayer.addScore(currentTurn.getScore());
    }

    private void checkForWinner(Player currentPlayer, int requiredScoreToWin) {
        if (currentPlayer.getScore() >= requiredScoreToWin) {
            // As Appendix A states, that if there is a winner the game is immediately finished
            // this is executed here instead of ensuring all players have played the same amount of turns
            this.end(currentPlayer);
        }
    }

    /**
     * Main game loop
     */
    public void play() {
        while (!finished) {
            Player currentPlayer = lobby.getNextPlayer();
            PlayerTurn currentTurn = new PlayerTurn();
            playPlayerTurn(currentTurn, currentPlayer, deck, ConsoleInput.instance());
            checkForWinner(currentPlayer, requiredScoreToWin);
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

    public boolean isFinished() {
        return finished;
    }
}