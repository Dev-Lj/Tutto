package ch.uzh.turn.turnStrategy;

import ch.uzh.command.Command;
import ch.uzh.command.EndTurnCommand;
import ch.uzh.command.LooseTurnCommand;
import ch.uzh.command.NullCommand;

public class TuttoBonusTurn extends StoppableTurn{
    private final int bonus;

    public TuttoBonusTurn(int bonus) {
        this.bonus = bonus;
    }

    @Override
    public Command playTurn() {
        super.playStoppableTurn();
        if (super.hasLost()) {
            return new LooseTurnCommand();
        } else if (super.isTutto()) {
            return new NullCommand();
        }
        return new EndTurnCommand();
    }

    @Override
    public int getScore() {
        if (super.isTutto()) {
            return super.getScore() + bonus;
        }
        return super.getScore();
    }
    
}
