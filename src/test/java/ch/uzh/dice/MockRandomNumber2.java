package ch.uzh.dice;

import java.util.Random;

public class MockRandomNumber2 extends Random{
    @Override
    protected int next(int bits) {
        return 1;
    }
}
    

