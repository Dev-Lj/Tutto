package ch.uzh.turn.turnStrategy;

import ch.uzh.command.Command;
import ch.uzh.command.MockCommand;
import ch.uzh.turn.PlayerTurn;
import ch.uzh.turn.TurnStrategy;

public class MockStrategy implements TurnStrategy {
    private boolean wasPlayed = false;

    @Override
    public Command playTurn(PlayerTurn currentTurn) {
        this.wasPlayed = true;
        return new MockCommand();
    }

    @Override
    public int getScore() {
        // TODO Auto-generated method stub
        return 0;
    }

    public boolean wasPlayed () {
        return this.wasPlayed;
    }
}
