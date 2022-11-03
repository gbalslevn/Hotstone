package hotstone.variants.GammaStone;

import hotstone.framework.Player;
import hotstone.framework.Strategies.TypeStrategy;
import hotstone.standard.GameConstants;
import hotstone.standard.HeroImpl;

public class DanishThaiChefs implements TypeStrategy {
    @Override
    public HeroImpl chooseType(Player who) {
        if (who == Player.FINDUS) {
            return new HeroImpl(2, 3, GameConstants.HERO_MAX_HEALTH, true,
                    Player.FINDUS, GameConstants.THAI_CHEF_HERO_TYPE, "opponent hero suffer (0,-2) on (mana, health)");
        } else {
            return new HeroImpl(0, 3, GameConstants.HERO_MAX_HEALTH, true, Player.PEDDERSEN
                    , GameConstants.DANISH_CHEF_HERO_TYPE, "Field sovs");
        }

    }

}
