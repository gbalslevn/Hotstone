package hotstone.variants.AlphaStone;

import hotstone.framework.Player;
import hotstone.framework.Strategies.TypeStrategy;
import hotstone.standard.GameConstants;

public class TypeBaby implements TypeStrategy {

    @Override
    public String chooseType(Player who) {
        return GameConstants.BABY_HERO_TYPE;
    }
}
