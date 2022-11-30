package ch.uzh.turn.turnStrategy;

import ch.uzh.command.Command;
import ch.uzh.command.DoubleTurnScoreCommand;
import ch.uzh.command.EndTurnCommand;
import ch.uzh.command.LooseTurnCommand;

public class TuttoDoubleTurn extends StoppableTurn{

    @Override
    public Command playTurn() {
        super.playStoppableTurn();
        if (super.hasLost()) {
            return new LooseTurnCommand();
        }

        if (super.isTutto()) {
            return new DoubleTurnScoreCommand();
        }

        return new EndTurnCommand();
    }
}
