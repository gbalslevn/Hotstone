package hotstone.framework.Strategies;

import hotstone.framework.Card;
import hotstone.framework.Game;

public interface EffectStrategy {
    void useEffect(Game game, Card card);
}
