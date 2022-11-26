package ch.uzh.command;

import ch.uzh.Game;
import ch.uzh.lobby.Player;
import ch.uzh.turn.PlayerTurn;

public class DoubleTurnScoreCommand implements Command{

    @Override
    public void execute(Game game, Player currentPlayer) {
        PlayerTurn currentTurn = game.getCurrentTurn();
        currentTurn.setScore(currentTurn.getScore()*2);
    }
    
}
