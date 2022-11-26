package ch.uzh.command;

import ch.uzh.Game;
import ch.uzh.lobby.Player;

public class NullCommand implements Command{

    @Override
    public void execute(Game game, Player currentPlayer) {
        return;
    }
    
}
