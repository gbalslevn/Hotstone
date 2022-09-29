package hotstone.variants.EpsilonStone;

import hotstone.framework.Player;
import hotstone.framework.Strategies.PowerStrategy;
import hotstone.standard.CardImpl;
import hotstone.standard.StandardHotStoneGame;

import java.util.Random;

public class HeroPowerFrenchItalianFixed implements PowerStrategy {
    @Override
    public void useHeroPower(StandardHotStoneGame game) {
        // Findus plays FrenchChef which has an attack of 2 damage
        if(game.getPlayerInTurn() == Player.FINDUS){
            // gets a minion on the field index 1 and decreases health by 2.
            CardImpl choosenCard = (CardImpl) game.getCardInField(Player.PEDDERSEN, 1);
            choosenCard.changeHealth(-2);
        } else {
            // Not checked for there is card on the field
            CardImpl choosenCard = (CardImpl) game.getCardInField(Player.PEDDERSEN, 1);
            choosenCard.changeAttack(2);
        }
    }
}
