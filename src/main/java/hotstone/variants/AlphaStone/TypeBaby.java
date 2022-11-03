package hotstone.variants.AlphaStone;

import hotstone.framework.Player;
import hotstone.framework.Strategies.TypeStrategy;
import hotstone.standard.GameConstants;
import hotstone.standard.HeroImpl;

public class TypeBaby implements TypeStrategy {

    public HeroImpl chooseType(Player who) {
        return new HeroImpl(0, 3, GameConstants.HERO_MAX_HEALTH, true,
                who, "Baby", "Cute");
    }

}
