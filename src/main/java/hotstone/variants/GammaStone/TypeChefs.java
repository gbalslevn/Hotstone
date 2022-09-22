package hotstone.variants.GammaStone;

import hotstone.framework.Game;
import hotstone.framework.Player;
import hotstone.framework.Strategies.TypeStrategy;
import hotstone.standard.CardImpl;
import hotstone.standard.GameConstants;
import hotstone.standard.HeroImpl;

import java.util.ArrayList;

public class TypeChefs implements TypeStrategy {
    @Override
    public void chooseType(Game game) {
        HeroImpl findusHero = (HeroImpl) game.getHero(Player.FINDUS);
        findusHero.setType(GameConstants.THAI_CHEF_HERO_TYPE);
        findusHero.setDescription("opponent hero suffer (0,-2) on (mana, health)");
        findusHero.setDamage(2);
        HeroImpl peddersenHero = (HeroImpl) game.getHero(Player.PEDDERSEN);
        peddersenHero.setType(GameConstants.DANISH_CHEF_HERO_TYPE);
        peddersenHero.setDescription("Field sovs");
        peddersenHero.setDamage(0);
    }
}
