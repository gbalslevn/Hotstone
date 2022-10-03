package hotstone.variants.EtaStone;

import hotstone.framework.Card;
import hotstone.framework.Game;
import hotstone.framework.Player;
import hotstone.framework.Strategies.EffectStrategy;
import hotstone.framework.Utility;
import hotstone.standard.CardImpl;
import hotstone.standard.GameConstants;
import hotstone.standard.HeroImpl;
import java.util.Random;

public class Effect implements EffectStrategy {
    @Override
    public void useEffect(Game game, Card card) {
        Random random = new Random();
        if(card.getName() == GameConstants.BROWN_RICE_CARD){
            HeroImpl opponentHero = (HeroImpl) game.getHero(Utility.computeOpponent(card.getOwner()));
            opponentHero.changeHealth(-1);
        }
        if(card.getName() == GameConstants.TOMATO_SALAD_CARD){
            // - 1 because we dont want the card to increase its own attack, this card will always be in last index
            CardImpl choosenCard = (CardImpl) game.getCardInField(game.getPlayerInTurn(), random.nextInt(game.getFieldSize(game.getPlayerInTurn()) -1));
            choosenCard.changeAttack(1);
        }

    }
}
