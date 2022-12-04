package ch.uzh.turn.turnStrategy;

import ch.uzh.command.Command;
import ch.uzh.command.NullCommand;
import ch.uzh.turn.PlayerTurn;

public class TuttoDoubleTurn extends StoppableTurn{

    @Override
    public Command playTurn(PlayerTurn currentTurn) {
        super.playStoppableTurn();
        return evaluateTurn(currentTurn);
    }

    private Command evaluateTurn(PlayerTurn currentTurn){
        if (super.hasLost()) {
            //Player has rolled a Null
            currentTurn.looseTurn();
        } else if (super.isTutto()) {
            //Player achieved Tutto -> Points during Turn are doubled
            currentTurn.setScore(currentTurn.getScore()*2);
        } else {
            //Player ends Turn voluntarily
            currentTurn.endTurn();
        }
        return new NullCommand();
    }

    @Override
    public int getScore() {
        if (super.isTutto()) {
            return super.getScore()*2;
        }
        return super.getScore();
    }

}
