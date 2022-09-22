package hotstone.variants.DeltaStone;

import hotstone.framework.Game;
import hotstone.framework.Strategies.ManaStrategy;

public class SetManaTo7 implements ManaStrategy {

    @Override
    public int calculateMana(Game game) {
        return 7;
    }
}
