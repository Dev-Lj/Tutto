package ch.uzh.dice;


public class MockDiceManager extends DiceManager {
    private boolean isTutto;
    public MockDiceManager(DiceScoreStrategy aDiceScoreStrategy) {
        super(6, aDiceScoreStrategy);
    }

    public void setIsTutto(boolean aTutto) {
        this.isTutto = aTutto;
    }

    public boolean isTutto() {
        return isTutto;
    }
}
