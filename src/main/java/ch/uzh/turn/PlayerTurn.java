package ch.uzh.turn;

import ch.uzh.command.Command;
import ch.uzh.deck.Card;

public class PlayerTurn {
    private int score;
    private boolean active = true;

    public Command playTurn(Card currentCard) {
        System.out.println(String.format("You have drawn: %s", currentCard.getName()));
        TurnStrategy turnStrategy = currentCard.getStrategy();
        Command postTurnCommand = turnStrategy.playTurn(this);
        this.score += turnStrategy.getScore();
        return postTurnCommand;
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

    public boolean isActive() {
        return active;
    }

    public void looseTurn() {
        this.endTurn();
        this.setScore(0);
    }
}
