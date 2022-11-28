package ch.uzh.command;

import ch.uzh.Game;
import ch.uzh.lobby.Player;

public class EndTurnCommand implements Command{

    @Override
    public void execute(Game game, Player currentPlayer) {
        game.getCurrentTurn().endTurn();
    }
    
}
