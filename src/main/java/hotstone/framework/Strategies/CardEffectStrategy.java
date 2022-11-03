package hotstone.framework.Strategies;

import hotstone.framework.Card;
import hotstone.standard.StandardHotStoneGame;

public interface CardEffectStrategy {
    void useEffect(StandardHotStoneGame game, Card card);
}
