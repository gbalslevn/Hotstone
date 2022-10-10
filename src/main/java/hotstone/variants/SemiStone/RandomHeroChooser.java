package hotstone.variants.SemiStone;

import hotstone.framework.Player;
import hotstone.framework.Strategies.RandomStrategy;
import hotstone.framework.Strategies.TypeStrategy;
import hotstone.standard.HeroImpl;

import java.lang.reflect.Type;

public class RandomHeroChooser implements TypeStrategy {

    private TypeStrategy gammeVersion, epsilonVersion, currentState;
    private RandomStrategy randomStrategy;

    public RandomHeroChooser(TypeStrategy gammaVersion, TypeStrategy epsilonVersion, RandomStrategy randomStrategy) {
        this.gammeVersion = gammaVersion;
        this.epsilonVersion = epsilonVersion;
        this.currentState = currentState;
        this.randomStrategy = randomStrategy;
    }
    @Override
    public HeroImpl chooseType(Player who) {
        return null;
    }
}
