package ch.uzh.command;

import ch.uzh.Game;
import ch.uzh.lobby.Player;

public class MockCommand implements Command {

    private boolean wasExecuted = false;

    @Override
    public void execute(Game game, Player currentPlayer) {
        this.wasExecuted = true;
    }

    public boolean wasExecuted() {
        return wasExecuted;
    }
    
}
