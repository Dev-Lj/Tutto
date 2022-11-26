package ch.uzh.turn.turnStrategy;

import ch.uzh.command.Command;
import ch.uzh.command.LooseTurnCommand;
import ch.uzh.turn.TurnStrategy;

public class StopTurn implements TurnStrategy{

    @Override
    public Command playTurn() {
        return new LooseTurnCommand();
    }

    @Override
    public int getScore() {
        return 0;
    }
    
}
