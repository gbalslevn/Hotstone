package hotstone.variants.EtaStone;

import hotstone.framework.*;
import hotstone.framework.Strategies.CardEffectStrategy;
import hotstone.framework.Strategies.RandomStrategy;
import hotstone.standard.GameConstants;
import hotstone.standard.StandardHotStoneGame;

import java.util.ArrayList;

public class CardEffect implements CardEffectStrategy {


    private RandomStrategy randomStrategy;

    public CardEffect(RandomStrategy randomStrategy) {
        this.randomStrategy = randomStrategy;
    }

    @Override
    public void useEffect(StandardHotStoneGame game, Card card) {

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
            case GameConstants.BEEF_BURGER_CARD:
                beefBurger(game);
                break;
        }
    }


    private void brownRiceEffect(StandardHotStoneGame game) {
        MutableHero opponentHero = (MutableHero) game.getHero(Utility.computeOpponent(game.getPlayerInTurn()));
        game.changeHealthHero(opponentHero,-1);
    }

    private void tomatoSalat(StandardHotStoneGame game) {
        // - 1 because we dont want the card to increase its own attack, this card will always be in last index
        boolean onlyTomatoCardOnField = game.getFieldSize(game.getPlayerInTurn()) == 1;
        if (!onlyTomatoCardOnField) {
            int randomInt = randomStrategy.getRandom(game.getFieldSize(game.getPlayerInTurn())-1);
            MutableCard choosenCard = (MutableCard) game.getCardInField(game.getPlayerInTurn(), randomInt);
            game.changeAttackCard(choosenCard,1);
        }
    }

    private void pokeBall(StandardHotStoneGame game) {
        MutableHero hero = (MutableHero) game.getHero(game.getPlayerInTurn());
        game.changeHealthHero(hero,2);

    }

    private void noodleSoup(StandardHotStoneGame game) {
        MutableGame game1 = (MutableGame) game;
        game1.drawCard(game1.getPlayerInTurn());
    }

    private void chickenCurry(StandardHotStoneGame game) {
        Player opponent = Utility.computeOpponent(game.getPlayerInTurn());
        if (game.getFieldSize(opponent) != 0) {
            int randomInt = randomStrategy.getRandom(game.getFieldSize(opponent));
            ((ArrayList) game.getField(opponent)).remove(randomInt);
        }
    }

    private void beefBurger(StandardHotStoneGame game) {
        Player opponent = Utility.computeOpponent(game.getPlayerInTurn());
        if (game.getFieldSize(opponent) != 0) {
            int randomInt = randomStrategy.getRandom(game.getFieldSize(opponent));
            game.changeAttackCard(((MutableCard) game.getCardInField(opponent, randomInt)),2);

        }
    }
}
