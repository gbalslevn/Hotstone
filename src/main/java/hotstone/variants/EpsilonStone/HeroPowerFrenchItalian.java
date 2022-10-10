package hotstone.variants.EpsilonStone;

import hotstone.framework.Game;
import hotstone.framework.Hero;
import hotstone.framework.Player;
import hotstone.framework.Strategies.PowerStrategy;
import hotstone.framework.Strategies.RandomStrategy;
import hotstone.framework.Utility;
import hotstone.standard.CardImpl;
import hotstone.standard.GameConstants;
import hotstone.standard.StandardHotStoneGame;

import java.util.Random;


public class HeroPowerFrenchItalian implements PowerStrategy {

    private RandomStrategy randomStrategy;

    public HeroPowerFrenchItalian(RandomStrategy randomStrategy) {
        this.randomStrategy = randomStrategy;
    }

    @Override
    public void useHeroPower(StandardHotStoneGame game) {
        Player opponent = Utility.computeOpponent(game.getPlayerInTurn());
        String currentHero = game.getHero(game.getPlayerInTurn()).getType();
        // FrenchChef gets a random minion on the opponent field and decreases health by 2.
        if (currentHero == GameConstants.FRENCH_CHEF_HERO_TYPE) {
            // Does nothing if 0 cards are on opponents field
            if (game.getFieldSize(opponent) <= 0) return;
            int opponentRandom = randomStrategy.getRandom(game.getFieldSize(opponent));
            CardImpl choosenCard = (CardImpl) game.getCardInField(opponent, opponentRandom);
            choosenCard.changeHealth(-2);
        } else if (currentHero == GameConstants.ITALIAN_CHEF_HERO_TYPE){
            // ItalianChef increases attack by 2 on random minion on own field
            if (game.getFieldSize(game.getPlayerInTurn()) <= 0) return;
            int ownRandom = randomStrategy.getRandom(game.getFieldSize(game.getPlayerInTurn()));
            CardImpl choosenCard = (CardImpl) game.getCardInField(game.getPlayerInTurn(), ownRandom);
            choosenCard.changeAttack(2);
        }
    }
}
