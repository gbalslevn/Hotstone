package hotstone.variants.EpsilonStone;

import hotstone.framework.Card;
import hotstone.framework.Player;
import hotstone.framework.Strategies.PowerStrategy;
import hotstone.framework.Utility;
import hotstone.standard.CardImpl;
import hotstone.standard.HeroImpl;
import hotstone.standard.StandardHotStoneGame;
import java.util.Random;
import java.util.ArrayList;


public class HeroPowerFrenchItalian implements PowerStrategy {

    @Override
    public void useHeroPower(StandardHotStoneGame game) {
            Random random = new Random();
        // Findus plays FrenchChef which has an attack of 2 damage
        if(game.getPlayerInTurn() == Player.FINDUS){
            // gets a random minion on the field and decreases health by 2.
            CardImpl choosenCard = (CardImpl) game.getCardInField(Player.PEDDERSEN, random.nextInt(game.getFieldSize(Player.PEDDERSEN)));
            choosenCard.changeHealth(-2);
        } else {
            // Not checked for there is card on the field
            CardImpl choosenCard = (CardImpl) game.getCardInField(Player.PEDDERSEN, random.nextInt(game.getFieldSize(Player.PEDDERSEN)));
            choosenCard.changeAttack(2);
        }
    }
}
