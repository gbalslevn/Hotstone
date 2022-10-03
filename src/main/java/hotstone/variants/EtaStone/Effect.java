package hotstone.variants.EtaStone;

import hotstone.framework.Card;
import hotstone.framework.Game;
import hotstone.framework.Player;
import hotstone.framework.Strategies.EffectStrategy;
import hotstone.framework.Strategies.RandomStrategy;
import hotstone.framework.Utility;
import hotstone.standard.CardImpl;
import hotstone.standard.GameConstants;
import hotstone.standard.HeroImpl;
import java.util.Random;

public class Effect implements EffectStrategy {


    private RandomStrategy randomStrategy;
    public Effect (RandomStrategy randomStrategy) {
        this.randomStrategy = randomStrategy;
    }

    @Override
    public void useEffect(Game game, Card card) {



        int randomInt = randomStrategy.getRandom(game.getFieldSize(game.getPlayerInTurn()));
        if(card.getName() == GameConstants.BROWN_RICE_CARD){
            HeroImpl opponentHero = (HeroImpl) game.getHero(Utility.computeOpponent(card.getOwner()));
            opponentHero.changeHealth(-1);
        }
        if(card.getName() == GameConstants.TOMATO_SALAD_CARD){
            // - 1 because we dont want the card to increase its own attack, this card will always be in last index
            CardImpl choosenCard = (CardImpl) game.getCardInField(game.getPlayerInTurn(), randomInt -1);
            choosenCard.changeAttack(1);
        }




    }
}
