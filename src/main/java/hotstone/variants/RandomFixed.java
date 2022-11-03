package hotstone.variants;

import hotstone.framework.Strategies.RandomStrategy;

public class RandomFixed implements RandomStrategy {
    private int index;

    public RandomFixed (int index){
        this.index = index;
    }
    @Override
    public int getRandom(int number1) {
        return index;
    }

    public void setRandom(int newIndex) {
        index = newIndex;
    }
}
