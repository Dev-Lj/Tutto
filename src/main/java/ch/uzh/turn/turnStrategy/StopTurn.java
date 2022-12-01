package ch.uzh.turn.turnStrategy;

import ch.uzh.command.Command;
import ch.uzh.command.NullCommand;
import ch.uzh.turn.PlayerTurn;
import ch.uzh.turn.TurnStrategy;

public class StopTurn implements TurnStrategy{

    @Override
    public Command playTurn(PlayerTurn currentTurn) {
        currentTurn.looseTurn();
        return new NullCommand();
    }

    @Override
    public int getScore() {
        return 0;
    }
    
}
