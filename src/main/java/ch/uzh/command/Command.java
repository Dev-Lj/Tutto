package ch.uzh.command;

import ch.uzh.Game;
import ch.uzh.lobby.Player;

public interface Command {
    public void execute(Game game, Player currentPlayer);
}
