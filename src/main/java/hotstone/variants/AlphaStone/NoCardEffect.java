package hotstone.variants.AlphaStone;

import hotstone.framework.Card;
import hotstone.framework.Strategies.CardEffectStrategy;
import hotstone.standard.StandardHotStoneGame;

public class NoCardEffect implements CardEffectStrategy {
    @Override
    public void useEffect(StandardHotStoneGame game, Card card) {
        // should do nothing
    }
}
