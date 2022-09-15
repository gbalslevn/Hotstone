package hotstone.variants.AlphaStone;

import hotstone.framework.Game;
import hotstone.framework.Strategies.ManaStrategy;

public class SetMana3 implements ManaStrategy {
    @Override
    public int calculateMana(Game game) {
        return 3;
    }
}
