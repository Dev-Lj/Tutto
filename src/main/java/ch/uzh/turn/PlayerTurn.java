package ch.uzh.turn;

import ch.uzh.Game;
import ch.uzh.command.Command;
import ch.uzh.deck.Card;
import ch.uzh.lobby.Player;

public class PlayerTurn {
    private int score;
    private boolean active = true;
    private final Player activePlayer;
    private final Game game;

    public PlayerTurn(Player activePlayer, Game game) {
        this.activePlayer = activePlayer;
        this.game = game;
    }

    public void playTurn() {
        while (active) {
            Card currentCard = game.getDeck().drawCard();
            TurnStrategy turnStrategy = currentCard.getStrategy();
            Command postTurnCommand = turnStrategy.playTurn();
            this.score += turnStrategy.getScore();
            postTurnCommand.execute(game, activePlayer);
            // TODO if active ask player if he wants to play once more
        }
    }

    public void endTurn() {
        this.active = false;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
