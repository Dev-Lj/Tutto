package ch.uzh.command;

import java.util.Iterator;

import ch.uzh.Game;
import ch.uzh.lobby.Player;

public class PlusMinusCommand implements Command{

    @Override
    public void execute(Game game, Player currentPlayer) {
        Iterator<Player> players = game.getLobby().playerSortedByRank();
        
        boolean punishedAll = false;
        int winnerScore = 0;
        while (players.hasNext() && !punishedAll) {
            Player winnerPlayer = players.next();
            if(winnerPlayer.getScore() >= winnerScore) {
                winnerScore = winnerPlayer.getScore();
                if (!winnerPlayer.equals(currentPlayer)) {
                    winnerPlayer.reduceScore(1000);
                }
            } else {
                punishedAll = true;
            }
        }
        currentPlayer.addScore(1000);
    }
    
}
