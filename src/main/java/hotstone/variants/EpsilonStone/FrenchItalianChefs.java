package hotstone.variants.EpsilonStone;

import hotstone.framework.Player;
import hotstone.framework.Strategies.TypeStrategy;
import hotstone.standard.GameConstants;
import hotstone.standard.HeroImpl;

public class FrenchItalianChefs implements TypeStrategy {

    @Override
    public HeroImpl chooseType(Player who) {
        if (who == Player.FINDUS) {
            return new HeroImpl(2, 3, GameConstants.HERO_MAX_HEALTH, true,
                    Player.FINDUS, GameConstants.FRENCH_CHEF_HERO_TYPE,
                    "Opponent minion suffer (0,-2) on (attack, health)");
        } else {
            return new HeroImpl(0, 3, GameConstants.HERO_MAX_HEALTH, true, Player.PEDDERSEN
                    , GameConstants.ITALIAN_CHEF_HERO_TYPE, "Minion gets (+2,0) on (attack, health)");
        }
    }
}
