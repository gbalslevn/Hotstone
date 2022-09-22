package hotstone.variants.AlphaStone;

import hotstone.framework.Game;
import hotstone.framework.Player;
import hotstone.framework.Strategies.TypeStrategy;
import hotstone.standard.GameConstants;
import hotstone.standard.HeroImpl;

public class TypeBaby implements TypeStrategy {

    @Override
    public void chooseType(Game game) {
        HeroImpl findusHero = (HeroImpl) game.getHero(Player.FINDUS);
        findusHero.setType(GameConstants.BABY_HERO_TYPE);
        findusHero.setDescription("Cute");
        findusHero.setDamage(0);

        HeroImpl peddersenHero = (HeroImpl) game.getHero(Player.PEDDERSEN);
        peddersenHero.setType(GameConstants.BABY_HERO_TYPE);
        peddersenHero.setDescription("Cute");
        peddersenHero.setDamage(0);

    }
}
