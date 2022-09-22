package hotstone.variants.AlphaStone;

import hotstone.framework.Game;
import hotstone.framework.Strategies.PowerStrategy;
import hotstone.framework.Utility;
import hotstone.standard.HeroImpl;
import hotstone.standard.StandardHotStoneGame;

public class HeroPower implements PowerStrategy {
    @Override
    public void useHeroPower(StandardHotStoneGame game) {
        ((HeroImpl) game.getHero(Utility.computeOpponent(game.getPlayerInTurn()))).changeHealth(((HeroImpl)
                game.getHero(game.getPlayerInTurn())).getDamage());
    }
}
