package ch.uzh.turn;

import ch.uzh.command.Command;

public interface TurnStrategy {
    public Command playTurn(PlayerTurn currentTurn);
    public int getScore();
}
