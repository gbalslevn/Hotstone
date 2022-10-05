package hotstone.variants.EpsilonStone;

import hotstone.framework.Player;
import hotstone.framework.Strategies.PowerStrategy;
import hotstone.framework.Strategies.RandomStrategy;
import hotstone.standard.CardImpl;
import hotstone.standard.StandardHotStoneGame;
import java.util.Random;


public class HeroPowerFrenchItalian implements PowerStrategy {

    private RandomStrategy randomStrategy;

    public HeroPowerFrenchItalian(RandomStrategy randomStrategy) {
        this.randomStrategy = randomStrategy;
    }

    @Override
    public void useHeroPower(StandardHotStoneGame game) {
        int newRandom = randomStrategy.getRandom(game.getFieldSize(Player.PEDDERSEN));
        // Findus plays FrenchChef which has an attack of 2 damage
        if (game.getPlayerInTurn() == Player.FINDUS) {
            // gets a random minion on the field and decreases health by 2.
            CardImpl choosenCard = (CardImpl) game.getCardInField(Player.PEDDERSEN, newRandom);
            choosenCard.changeHealth(-2);
        } else {
            // increase attack by 2 on random minion on own field
            CardImpl choosenCard = (CardImpl) game.getCardInField(Player.PEDDERSEN, newRandom);
            choosenCard.changeAttack(2);
        }
    }
}
