package ch.uzh.dice;

import java.util.Random;

public class FakeRandomNumber1 extends Random{
    @Override
    protected int next(int bits) {
        return 1;
    }
}
    
