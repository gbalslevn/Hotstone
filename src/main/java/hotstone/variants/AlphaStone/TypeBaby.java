package hotstone.variants.AlphaStone;

import hotstone.framework.Game;
import hotstone.framework.Player;
import hotstone.framework.Strategies.TypeStrategy;
import hotstone.standard.GameConstants;
import hotstone.standard.HeroImpl;

public class TypeBaby implements TypeStrategy {

    public void chooseType(Game game) {
        HeroImpl findusHero = (HeroImpl) game.getHero(Player.FINDUS);
        HeroImpl peddersenHero = (HeroImpl) game.getHero(Player.PEDDERSEN);
        chooseSettings(GameConstants.BABY_HERO_TYPE,"Cute",0,findusHero);
        chooseSettings(GameConstants.BABY_HERO_TYPE,"Cute",0,peddersenHero);
    }
    public void chooseSettings(String type, String description, int damage, HeroImpl hero){
        hero.setType(type);
        hero.setDescription(description);
        hero.setDamage(damage);
    }
}
