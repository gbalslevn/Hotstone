package hotstone.variants;

import hotstone.framework.Card;
import hotstone.framework.Game;
import hotstone.framework.Player;
import hotstone.standard.GameConstants;
import hotstone.standard.StandardHotStoneGame;
import hotstone.variants.AbstractFactory.EpsilonStoneFactory;
import hotstone.variants.AlphaStone.*;
import hotstone.variants.EpsilonStone.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;


import hotstone.variants.AlphaStone.SpanishDeck;

public class TestEpsilonStone {

    private Game game;

    /**
     * Fixture for GammaStone testing.
     */
    @BeforeEach
    public void setUp() {
        game = new StandardHotStoneGame(new EpsilonStoneFactory());
    }

    @Test
    public void shouldFindusHeroBeFrenchChef() {
        assertThat(game.getHero(Player.FINDUS).getType(), is(GameConstants.FRENCH_CHEF_HERO_TYPE));
    }

    @Test
    public void shouldLoose2HealthWhenRedwinePowerIsUsed() {
        game.endTurn();
        Card card = game.getCardInHand(Player.PEDDERSEN, 0);
        int cardHealthBefore = card.getHealth();
        game.playCard(Player.PEDDERSEN, card);
        game.endTurn();
        game.usePower(Player.FINDUS);
        int cardHealthAfter = card.getHealth();
        assertThat(cardHealthAfter, is(cardHealthBefore - 2));
    }

    @Test
    public void shouldIncrease2AttackWhenPastaPowerIsUsed() {
        game.endTurn();
        Card card = game.getCardInHand(Player.PEDDERSEN, 0);
        int attackBefore = card.getAttack();
        game.playCard(Player.PEDDERSEN, card);
        game.usePower(Player.PEDDERSEN);
        int attackAfter = card.getAttack();
        assertThat(attackAfter, is(attackBefore + 2));
    }

    @Test
    public void shoudFindusWinAfterDealing7DamageFromMinionToMinion() {
        twoFieldsWith4ActiveCardsEach();
        int attack = 0;
        Card card1F = game.getCardInField(Player.FINDUS, 0);
        attack += card1F.getAttack();
        Card card2F = game.getCardInField(Player.FINDUS, 1);
        attack += card2F.getAttack();
        Card card3F = game.getCardInField(Player.FINDUS, 2);
        attack += card3F.getAttack();
        Card card4F = game.getCardInField(Player.FINDUS, 3);
        attack += card4F.getAttack();

        Card card1P = game.getCardInField(Player.PEDDERSEN, 0);
        Card card2P = game.getCardInField(Player.PEDDERSEN, 1);
        Card card3P = game.getCardInField(Player.PEDDERSEN, 2);
        Card card4P = game.getCardInField(Player.PEDDERSEN, 3);


        game.attackCard(Player.FINDUS, card1F, card1P);
        //assertThat(Stats.getDamageOutput(Player.FINDUS), is(3));
        game.attackCard(Player.FINDUS, card2F, card2P);
        //assertThat(Stats.getDamageOutput(Player.FINDUS), is(5));
        game.attackCard(Player.FINDUS, card3F, card3P);
        //assertThat(Stats.getDamageOutput(Player.FINDUS), is(6));
        game.attackCard(Player.FINDUS, card4F, card4P);
        //assertThat(Stats.getDamageOutput(Player.FINDUS), is(9));

        //assertThat(attack, is(7));
        assertThat(game.getWinner(), is(Player.FINDUS));
    }

    public void twoFieldsWith4ActiveCardsEach() {
        // Findus plays first card
        Card card = game.getCardInHand(Player.FINDUS, 0);
        game.playCard(Player.FINDUS, card);
        game.endTurn();
        // Peddersen plays first card
        Card card1 = game.getCardInHand(Player.PEDDERSEN, 0);
        game.playCard(Player.PEDDERSEN, card1);
        game.endTurn();
        // Findus plays second card
        Card card2 = game.getCardInHand(Player.FINDUS, 0);
        game.playCard(Player.FINDUS, card2);
        game.endTurn();
        // Peddersen plays second card
        Card card3 = game.getCardInHand(Player.PEDDERSEN, 0);
        game.playCard(Player.PEDDERSEN, card3);
        game.endTurn();
        // Findus plays third card
        Card card4 = game.getCardInHand(Player.FINDUS, 0);
        game.playCard(Player.FINDUS, card4);
        game.endTurn();
        // Peddersen plays third card
        Card card5 = game.getCardInHand(Player.PEDDERSEN, 0);
        game.playCard(Player.PEDDERSEN, card5);
        game.endTurn();


        StandardHotStoneGame game1 = (StandardHotStoneGame) game;
        // Findus draws a card and plays it - the fourth card
        game1.drawCard(Player.FINDUS);
        Card card6 = game.getCardInHand(Player.FINDUS, 0);
        game.playCard(Player.FINDUS, card6);
        game.endTurn();
        // Peddersen draws a card and plays it - the fourth card
        game1.drawCard(Player.PEDDERSEN);
        Card card7 = game.getCardInHand(Player.PEDDERSEN, 0);
        game.playCard(Player.PEDDERSEN, card7);
        game.endTurn();
    }


}
