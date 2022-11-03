package hotstone.variants;

import hotstone.framework.Card;
import hotstone.framework.Game;
import hotstone.framework.Player;
import hotstone.standard.StandardHotStoneGame;
import hotstone.variants.AbstractFactory.DeltaStoneFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestDeltaStone {
    private Game game;

    /**
     * Fixture for GammaStone testing.
     */

    @BeforeEach
    public void setUp() {
        game = new StandardHotStoneGame(new DeltaStoneFactory());
    }


    @Test
    public void shouldGet7manaEachRound(){
        // Checks if hero starts with 7 mana
        assertThat(game.getHero(Player.FINDUS).getMana(), is(7));
        // Card is played abd The turn ends
        Card chosenCard = game.getCardInHand(Player.FINDUS, 1);
        game.playCard(Player.FINDUS, chosenCard);
        game.endTurn();
        // should be mana amount before ended turn + 7
        assertThat(game.getHero(Player.FINDUS).getMana(), is(7));
    }

    @Test
    public void shouldBe24CardsInTheDeck(){
        // Deck should consist of 21 cards after handing out 3
        assertThat(game.getDeckSize(Player.FINDUS),is(21));
    }
    @Test
    public void shouldBeMaxCostOf1ManaForFirstCard(){
        assertThat(game.getCardInHand(Player.FINDUS, 2).getManaCost(), is(1));
    }
    @Test
    public void shouldBeMaxCost2ManaForSecondCard(){
        int cost = game.getCardInHand(Player.FINDUS, 1).getManaCost();
        assertThat(cost <= 2,is(true));
    }
    @Test
    public void shouldBeMaxCost4ManaForThirdCard(){
        int cost = game.getCardInHand(Player.FINDUS, 0).getManaCost();
        assertThat(cost <= 4,is(true));
    }


}
