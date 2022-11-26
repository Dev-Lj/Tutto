package ch.uzh.command;

import ch.uzh.Game;
import ch.uzh.lobby.Player;

/**
 * EndGameCommand
 */
public class EndGameCommand implements Command{

    @Override
    public void execute(Game game, Player currentPlayer) {
        game.end(currentPlayer);
    }

    
}