package hotstone.variants;

import hotstone.framework.Card;
import hotstone.framework.Game;
import hotstone.framework.Player;
import hotstone.standard.GameConstants;
import hotstone.standard.StandardHotStoneGame;
import hotstone.utility.TestHelper;
import hotstone.variants.AlphaStone.*;
import hotstone.variants.EpsilonStone.FrenchItalianChefs;
import hotstone.variants.EpsilonStone.HeroPowerFrenchItalian;
import hotstone.variants.EpsilonStone.WinAfter7DamageOutput;
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
        game = new StandardHotStoneGame(new SetMana3(),new WinAfter7DamageOutput(), new FrenchItalianChefs(), new HeroPowerFrenchItalian(), new SpanishDeck(), new NoEffect());
    }

    @Test
    public void shouldFindusHeroBeFrenchChef(){
        assertThat(game.getHero(Player.FINDUS).getType(),is(GameConstants.FRENCH_CHEF_HERO_TYPE));
    }

    @Test
    public void shouldLoose2HealthWhenRedwinePowerIsUsed(){
        game.endTurn();
        Card card = game.getCardInHand(Player.PEDDERSEN, 0);
        int cardHealthBefore = card.getHealth();
        game.playCard(Player.PEDDERSEN, card);
        game.endTurn();
        game.usePower(Player.FINDUS);
        int cardHealthAfter = card.getHealth();
        assertThat(cardHealthAfter, is(cardHealthBefore-2));
    }

    @Test
    public void shouldIncrease2AttackWhenPastaPowerIsUsed(){
        game.endTurn();
        Card card = game.getCardInHand(Player.PEDDERSEN, 0);
        int attackBefore = card.getAttack();
        game.playCard(Player.PEDDERSEN, card);
        game.usePower(Player.PEDDERSEN);
        int attackAfter = card.getAttack();
        assertThat(attackAfter, is(attackBefore+2));
    }
    @Test
    public void shoudFindusWinAfterDealing7DamageFromMinionToMinion(){
        Card card = game.getCardInHand(Player.FINDUS, 0);
        game.playCard(Player.FINDUS,card);
        game.endTurn();
        Card card1 = game.getCardInHand(Player.PEDDERSEN, 0);
        game.playCard(Player.PEDDERSEN,card1);
        game.endTurn();
        Card card2 = game.getCardInHand(Player.FINDUS, 0);
        game.playCard(Player.FINDUS,card2);
        game.endTurn();
        Card card3 = game.getCardInHand(Player.PEDDERSEN, 0);
        game.playCard(Player.PEDDERSEN,card3);
        game.endTurn();
        Card card4 = game.getCardInHand(Player.FINDUS, 0);
        game.playCard(Player.FINDUS,card4);
        game.endTurn();
        Card card5 = game.getCardInHand(Player.PEDDERSEN, 0);
        game.playCard(Player.PEDDERSEN,card5);
        game.endTurn();
        StandardHotStoneGame game1 = (StandardHotStoneGame) game;
        game1.drawCard(Player.FINDUS);
        Card card6 = game.getCardInHand(Player.FINDUS, 0);
        game.playCard(Player.FINDUS,card6);
        game.endTurn();
        game1.drawCard(Player.PEDDERSEN);
        Card card7 = game.getCardInHand(Player.PEDDERSEN, 0);
        game.playCard(Player.PEDDERSEN,card7);
        game.endTurn();
        game.attackCard(Player.FINDUS,card,card1);
        game.attackCard(Player.FINDUS,card2,card3);
        game.attackCard(Player.FINDUS,card4,card5);
        game.attackCard(Player.FINDUS,card6,card7);
        assertThat(game.getWinner(),is(Player.FINDUS));
    }


}
