package hotstone.Stub;

import hotstone.framework.Strategies.RandomStrategy;
import hotstone.standard.StandardHotStoneGame;

public class RandomFixed implements RandomStrategy {
    private int number;
    @Override
    public int getRandom(int number) {
        return number - 1 ;
    }

    public void setRandom(int newNumber) {
        number = newNumber;
    }
}
