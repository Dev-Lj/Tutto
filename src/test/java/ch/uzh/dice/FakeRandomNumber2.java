package ch.uzh.dice;

import java.util.Random;

public class FakeRandomNumber2 extends Random{
    @Override
    protected int next(int bits) {
        return 1;
    }
}
    

