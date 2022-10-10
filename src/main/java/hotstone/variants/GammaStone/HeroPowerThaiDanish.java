package hotstone.variants.GammaStone;

import hotstone.framework.Player;
import hotstone.framework.Strategies.PowerStrategy;
import hotstone.framework.Utility;
import hotstone.standard.CardImpl;
import hotstone.standard.GameConstants;
import hotstone.standard.HeroImpl;
import hotstone.standard.StandardHotStoneGame;

import java.util.ArrayList;

public class HeroPowerThaiDanish implements PowerStrategy {
    @Override
    public void useHeroPower(StandardHotStoneGame game) {
        String currentHero = game.getHero(game.getPlayerInTurn()).getType();
        Player opponent = Utility.computeOpponent(game.getPlayerInTurn());
        // Findus plays ThaiChef which has an attack of 2 damage
        if(currentHero == GameConstants.THAI_CHEF_HERO_TYPE){
            // decreases opponent hero health by 2
            ((HeroImpl) game.getHero(opponent)).changeHealth(-2);
        } else if (currentHero == GameConstants.DANISH_CHEF_HERO_TYPE){
            //DanishChef spawns a minion with Attack/Damage = 1/1. It adds it to the field.
            ((ArrayList) game.getField(game.getPlayerInTurn())).add(0, new CardImpl
                    ("Sovs", 0, 1, 1, false, game.getPlayerInTurn()));
        }
    }
}
