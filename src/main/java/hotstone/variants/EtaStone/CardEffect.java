package hotstone.variants.EtaStone;

import hotstone.framework.Card;
import hotstone.framework.Game;
import hotstone.framework.Player;
import hotstone.framework.Strategies.CardEffectStrategy;
import hotstone.framework.Strategies.RandomStrategy;
import hotstone.framework.Utility;
import hotstone.standard.CardImpl;
import hotstone.standard.GameConstants;
import hotstone.standard.HeroImpl;
import hotstone.standard.StandardHotStoneGame;

import java.util.ArrayList;

public class CardEffect implements CardEffectStrategy {


    private RandomStrategy randomStrategy;

    public CardEffect(RandomStrategy randomStrategy) {
        this.randomStrategy = randomStrategy;
    }

    @Override
    public void useEffect(Game game, Card card) {

        switch (card.getName()) {
            case GameConstants.BROWN_RICE_CARD:
                brownRiceEffect(game);
                break;
            case GameConstants.TOMATO_SALAD_CARD:
                tomatoSalat(game);
                break;
            case GameConstants.POKE_BOWL_CARD:
                pokeBall(game);
                break;
            case GameConstants.NOODLE_SOUP_CARD:
                noodleSoup(game);
                break;
            case GameConstants.CHICKEN_CURRY_CARD:
                chickenCurry(game);
                ;
            case GameConstants.BEEF_BURGER_CARD:
                beefBurger(game);
                ;
                break;
        }


    }


    private void brownRiceEffect(Game game) {
        HeroImpl opponentHero = (HeroImpl) game.getHero(Utility.computeOpponent(game.getPlayerInTurn()));
        opponentHero.changeHealth(-1);
    }

    private void tomatoSalat(Game game) {
        // - 1 because we dont want the card to increase its own attack, this card will always be in last index
        boolean onlyTomatoCardOnField = game.getFieldSize(game.getPlayerInTurn()) == 1;
        if (!onlyTomatoCardOnField) {
            int randomInt = randomStrategy.getRandom(game.getFieldSize(game.getPlayerInTurn())-1);
            CardImpl choosenCard = (CardImpl) game.getCardInField(game.getPlayerInTurn(), randomInt);
            choosenCard.changeAttack(1);

        }
    }

    private void pokeBall(Game game) {
        HeroImpl hero = (HeroImpl) game.getHero(game.getPlayerInTurn());
        hero.changeHealth(2);
    }

    private void noodleSoup(Game game) {
        StandardHotStoneGame game1 = (StandardHotStoneGame) game;
        game1.drawCard(game1.getPlayerInTurn());
    }

    private void chickenCurry(Game game) {
        Player opponent = Utility.computeOpponent(game.getPlayerInTurn());
        if (game.getFieldSize(opponent) != 0) {
            int randomInt = randomStrategy.getRandom(game.getFieldSize(opponent));
            ((ArrayList) game.getField(opponent)).remove(randomInt);
        }
    }

    private void beefBurger(Game game) {
        Player opponent = Utility.computeOpponent(game.getPlayerInTurn());
        if (game.getFieldSize(opponent) != 0) {
            int randomInt = randomStrategy.getRandom(game.getFieldSize(opponent));
            ((CardImpl) game.getCardInField(opponent, randomInt)).changeAttack(2);
        }
    }


}
