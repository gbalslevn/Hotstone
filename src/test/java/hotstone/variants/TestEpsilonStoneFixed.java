package hotstone.variants;

import hotstone.framework.Card;
import hotstone.framework.Game;
import hotstone.framework.Player;
import hotstone.standard.StandardHotStoneGame;
import hotstone.variants.AbstractFactory.EpsilonStoneFactoryFixed;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestEpsilonStoneFixed {
    private Game game;

    /**
     * Fixture for ZetaStone testing.
     */

    @BeforeEach
    public void setUp() {
        game = new StandardHotStoneGame(new EpsilonStoneFactoryFixed());
    }

    @Test
    public void shouldLoose2HealthOnIndex1(){
        game.endTurn();
        // Dos card - Plays it to have one card on field
        Card card = game.getCardInHand(Player.PEDDERSEN, 1);
        game.playCard(Player.PEDDERSEN, card);
        game.endTurn();
        game.endTurn();
        // Tres card - Plays it to get 2 cards on the field and gets health
        Card card1 = game.getCardInHand(Player.PEDDERSEN, 0);
        int cardHealthBefore = card1.getHealth();
        game.playCard(Player.PEDDERSEN, card1);
        game.endTurn();
        game.usePower(Player.FINDUS);
        int cardHealthAfter = card1.getHealth();
        assertThat(cardHealthAfter, is(cardHealthBefore-2));
    }

    @Test
    public void shouldGain2AttackOnInde3(){
        game.endTurn();
        Card card = game.getCardInHand(Player.PEDDERSEN, 0);
        game.playCard(Player.PEDDERSEN, card);
        game.endTurn();
        game.endTurn();
        Card card1 = game.getCardInHand(Player.PEDDERSEN, 3);
        int attackBefore = card1.getAttack();
        game.playCard(Player.PEDDERSEN, card1);
        game.usePower(Player.PEDDERSEN);
        int attackAfter = card.getAttack();
        assertThat(attackAfter, is(attackBefore+2));
    }
}
