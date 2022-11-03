package hotstone.variants.SemiStone;

import hotstone.framework.Strategies.PowerStrategy;
import hotstone.standard.GameConstants;
import hotstone.standard.StandardHotStoneGame;

public class AlternatingHeroPower implements PowerStrategy {

    private PowerStrategy gammaVersion, epsilonVersion, currentVersion;

    public AlternatingHeroPower(PowerStrategy gammaVersion, PowerStrategy epsilonVersion) {
        this.gammaVersion = gammaVersion;
        this.epsilonVersion = epsilonVersion;
        this.currentVersion = null;
    }


    @Override
    public void useHeroPower(StandardHotStoneGame standardHotStoneGame) {
        String heroType = standardHotStoneGame.getHero(standardHotStoneGame.getPlayerInTurn()).getType();
        if (heroType.equals(GameConstants.FRENCH_CHEF_HERO_TYPE) || heroType.equals(GameConstants.ITALIAN_CHEF_HERO_TYPE)){
            currentVersion = epsilonVersion;
        }else if (heroType.equals(GameConstants.DANISH_CHEF_HERO_TYPE) || heroType.equals(GameConstants.THAI_CHEF_HERO_TYPE)){
            currentVersion = gammaVersion;
        }
        currentVersion.useHeroPower(standardHotStoneGame);
    }
}
