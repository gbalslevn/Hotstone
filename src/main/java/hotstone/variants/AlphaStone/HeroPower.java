package hotstone.variants.AlphaStone;

import hotstone.framework.Game;
import hotstone.framework.Player;
import hotstone.framework.Strategies.PowerStrategy;
import hotstone.framework.Utility;
import hotstone.standard.HeroImpl;
import hotstone.standard.StandardHotStoneGame;

public class HeroPower implements PowerStrategy {
    @Override
    public void useHeroPower(StandardHotStoneGame game) {
        Player playerInTurn = game.getPlayerInTurn();
        HeroImpl opponentHero = (HeroImpl) game.getHero(Utility.computeOpponent(playerInTurn));
        int attackingHeroDamage = ((HeroImpl) game.getHero(playerInTurn)).getDamage();
        opponentHero.changeHealth(attackingHeroDamage);
    }
}
