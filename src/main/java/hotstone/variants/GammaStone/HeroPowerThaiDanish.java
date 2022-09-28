package hotstone.variants.GammaStone;

import hotstone.framework.Player;
import hotstone.framework.Strategies.PowerStrategy;
import hotstone.standard.CardImpl;
import hotstone.standard.HeroImpl;
import hotstone.standard.StandardHotStoneGame;

import java.util.ArrayList;

public class HeroPowerThaiDanish implements PowerStrategy {
    @Override
    public void useHeroPower(StandardHotStoneGame game) {
        // Findus plays ThaiChef which has an attack of 2 damage
        if(game.getPlayerInTurn() == Player.FINDUS){
            // decreases Pedersons hero health by 2
            ((HeroImpl) game.getHero(Player.PEDDERSEN)).changeHealth(-2);
        } else {
            // Pedersen plays DanishChef which spawns a minion with Attack/Damage = 1/1. It adds it to the field.
            ((ArrayList) game.getField(Player.PEDDERSEN)).add(0, new CardImpl
                    ("Sovs", 0, 1, 1, false, Player.PEDDERSEN));
        }
    }
}
