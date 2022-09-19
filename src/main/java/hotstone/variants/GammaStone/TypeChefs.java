package hotstone.variants.GammaStone;

import hotstone.framework.Game;
import hotstone.framework.Player;
import hotstone.framework.Strategies.TypeStrategy;
import hotstone.standard.GameConstants;

public class TypeChefs implements TypeStrategy {
    @Override
    public String chooseType(Player who) {
        return  who == Player.FINDUS ?  GameConstants.THAI_CHEF_HERO_TYPE : GameConstants.DANISH_CHEF_HERO_TYPE;
    }
}
