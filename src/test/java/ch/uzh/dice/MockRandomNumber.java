package ch.uzh.dice;

import java.util.Random;

public class MockRandomNumber extends Random {
    int reulsultNumber;

    @Override
    protected int next(int bits) {
        return 0;
    }

    public void setReulsultNumber(int number) {
        this.reulsultNumber = number - 1;
    }
}
