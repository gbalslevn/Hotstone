package hotstone.variants.BetaStone;

import hotstone.framework.Game;
import hotstone.framework.Strategies.ManaStrategy;

public class IncreaseManaUntil7 implements ManaStrategy {
    @Override
    public int calculateMana(Game game) {
        int turnNumber = game.getTurnNumber();
        // +2, because it rounds down
        return turnNumber >= 14 ? 7 : (turnNumber + 2) / 2;
    }
}
