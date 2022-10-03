package hotstone.variants.EpsilonStone;
import hotstone.framework.Strategies.RandomStrategy;

import java.util.Random;


public class RandomReal implements RandomStrategy{
    @Override
    public int getRandom(int number) {
        Random random = new Random();
        return random.nextInt(number);
    }
}
