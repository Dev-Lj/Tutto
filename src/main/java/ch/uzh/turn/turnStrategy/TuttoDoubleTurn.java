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
            currentTurn.looseTurn();
        } else if (super.isTutto()) {
            currentTurn.setScore(currentTurn.getScore()*2);
        } else {
            currentTurn.endTurn();
        }
        return new NullCommand();
    }

}
