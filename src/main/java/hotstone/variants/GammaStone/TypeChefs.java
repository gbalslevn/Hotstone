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
        HeroImpl peddersenHero = (HeroImpl) game.getHero(Player.PEDDERSEN);
        chooseSettings(GameConstants.THAI_CHEF_HERO_TYPE,
                "opponent hero suffer (0,-2) on (mana, health)",2,findusHero);
        chooseSettings(GameConstants.DANISH_CHEF_HERO_TYPE,"Field sovs",0,peddersenHero);
    }
    public void chooseSettings(String type, String description, int damage, HeroImpl hero){
        hero.setType(type);
        hero.setDescription(description);
        hero.setDamage(damage);
    }
}
