package ch.uzh.dice;

import java.util.Random;

public class MockRandomNumber1 extends Random{
    @Override
    protected int next(int bits) {
        return 1;
    }
}
    
