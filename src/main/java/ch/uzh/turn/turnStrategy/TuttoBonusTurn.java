package ch.uzh.turn.turnStrategy;

import ch.uzh.command.Command;
import ch.uzh.command.NullCommand;
import ch.uzh.turn.PlayerTurn;

public class TuttoBonusTurn extends StoppableTurn{
    private final int bonus;

    public TuttoBonusTurn(int bonus) {
        this.bonus = bonus;
    }

    @Override
    public Command playTurn(PlayerTurn currentTurn) {
        super.playStoppableTurn();
        return evaluateTurn(currentTurn);
        
    }

    private Command evaluateTurn(PlayerTurn currentTurn){
        if (super.hasLost()) {
            currentTurn.looseTurn();
        } else if (!super.isTutto()) {
            currentTurn.endTurn();
        }
        return new NullCommand();
    }


    @Override
    public int getScore() {
        if (super.isTutto()) {
            return super.getScore() + bonus;
        }
        return super.getScore();
    }
    
}
