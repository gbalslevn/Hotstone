package hotstone.variants.SemiStone;

import hotstone.framework.Player;
import hotstone.framework.Strategies.RandomStrategy;
import hotstone.framework.Strategies.TypeStrategy;
import hotstone.standard.GameConstants;
import hotstone.standard.HeroImpl;

public class RandomHeroType implements TypeStrategy {

    private RandomStrategy randomStrategy;

    public RandomHeroType(RandomStrategy randomStrategy) {
        this.randomStrategy = randomStrategy;
    }

    @Override
    public HeroImpl chooseType(Player who) {
        int random = randomStrategy.getRandom(4);
        switch (random) {
            case 1:
                return new HeroImpl(2, 3, GameConstants.HERO_MAX_HEALTH, true, who,
                        GameConstants.THAI_CHEF_HERO_TYPE, "opponent hero suffer (0,-2) on (mana, health)");
            case 2:
                return new HeroImpl(0, 3, GameConstants.HERO_MAX_HEALTH, true, who
                        , GameConstants.DANISH_CHEF_HERO_TYPE, "Field sovs");
            case 3:
                return new HeroImpl(2, 3, GameConstants.HERO_MAX_HEALTH, true,
                        who, GameConstants.FRENCH_CHEF_HERO_TYPE,
                        "Opponent minion suffer (0,-2) on (attack, health)");
            case 4:
                return new HeroImpl(0, 3, GameConstants.HERO_MAX_HEALTH, true, who
                        , GameConstants.ITALIAN_CHEF_HERO_TYPE, "Minion gets (+2,0) on (attack, health)");
        }
        return null;
    }
}

