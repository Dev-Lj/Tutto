package ch.uzh.turn.turnStrategy;

import ch.uzh.command.Command;
import ch.uzh.command.MockCommand;
import ch.uzh.turn.PlayerTurn;
import ch.uzh.turn.TurnStrategy;

public class MockStrategy implements TurnStrategy {
    private boolean wasPlayed = false;
    private boolean hasLost = false;
    private int score = 0;
    private MockCommand mockCommand = new MockCommand();

    @Override
    public Command playTurn(PlayerTurn currentTurn) {
        this.wasPlayed = true;
        if (hasLost) {
            currentTurn.looseTurn();
        }
        return mockCommand;
    }

    @Override
    public int getScore() {
        return score;
    }

    public boolean wasPlayed () {
        return this.wasPlayed;
    }

    public MockCommand getMockCommand() {
        return mockCommand;
    }

    public void setHasLost(boolean hasLost) {
        this.hasLost = hasLost;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
