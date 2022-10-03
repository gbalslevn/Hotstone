package hotstone.variants.AlphaStone;

import hotstone.framework.Card;
import hotstone.framework.Game;
import hotstone.framework.Strategies.EffectStrategy;

public class NoEffect implements EffectStrategy {
    @Override
    public void useEffect(Game game, Card card) {
        // should do nothing
    }
}
