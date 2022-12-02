package ch.uzh.dice;


public class MockDiceManager extends DiceManager {
    private boolean MockisTutto;
    private boolean MockHadNullTurn;
    public MockDiceManager(DiceScoreStrategy aDiceScoreStrategy) {
        super(6, aDiceScoreStrategy);
    }

    public void setHadNullTurn(boolean aMockHadNullTurn) {
        this.MockHadNullTurn = aMockHadNullTurn;
    }

    public boolean hadNullTurn() {
        return MockHadNullTurn;
    }

    public void setIsTutto(boolean aTutto) {
        this.MockisTutto = aTutto;
    }

    public boolean isTutto() {
        return MockisTutto;
    }
}