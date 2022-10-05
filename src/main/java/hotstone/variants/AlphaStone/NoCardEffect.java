package hotstone.variants.AlphaStone;

import hotstone.framework.Card;
import hotstone.framework.Game;
import hotstone.framework.Strategies.CardEffectStrategy;

public class NoCardEffect implements CardEffectStrategy {
    @Override
    public void useEffect(Game game, Card card) {
        // should do nothing
    }
}
