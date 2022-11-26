package ch.uzh.turn.turnStrategy;

import ch.uzh.turn.TurnStrategy;

public abstract class StoppableTurn implements TurnStrategy{
    private int score = 0;
    private boolean isTutto = false;
    private boolean hasLost = false;

    protected final void playStoppableTurn() {
        // TODO implement
    }

    @Override
    public int getScore() {
        return this.score;
    }

    protected final boolean isTutto() {
        return isTutto;
    }

    protected boolean hasLost() {
        return this.hasLost;
    }

}
