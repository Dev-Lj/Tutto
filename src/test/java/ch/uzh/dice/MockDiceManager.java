package ch.uzh.dice;


public class MockDiceManager extends DiceManager {
    private boolean isTutto;
    private boolean hadNullTurn;
    public MockDiceManager(DiceScoreStrategy aDiceScoreStrategy) {
        super(6, aDiceScoreStrategy);
    }

    public void setHadNullTurn(boolean ahadNullTurn) {
        this.hadNullTurn = ahadNullTurn;
    }

    public boolean hadNullTurn() {
        return hadNullTurn;
    }

    public void setIsTutto(boolean aTutto) {
        this.isTutto = aTutto;
    }

    public boolean isTutto() {
        return isTutto;
    }
}