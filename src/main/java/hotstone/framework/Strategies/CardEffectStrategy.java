package hotstone.framework.Strategies;

import hotstone.framework.Card;
import hotstone.framework.Game;

public interface CardEffectStrategy {
    void useEffect(Game game, Card card);
}
