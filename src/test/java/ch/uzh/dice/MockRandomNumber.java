package ch.uzh.dice;

import java.util.Random;

public class MockRandomNumber extends Random {
    int resultNumber;

    @Override
    protected int next(int bits) {
        return this.resultNumber;
    }

    public void setReulsultNumber(int number) {
        this.resultNumber = number - 1;
    }
}
