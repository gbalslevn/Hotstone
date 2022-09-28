package hotstone.variants;

import hotstone.framework.Card;
import hotstone.framework.Game;
import hotstone.framework.Player;
import hotstone.standard.CardImpl;
import hotstone.standard.GameConstants;
import hotstone.standard.HeroImpl;
import hotstone.standard.StandardHotStoneGame;
import hotstone.variants.AlphaStone.*;
import hotstone.variants.EpsilonStone.FrenchItalianChefs;
import hotstone.variants.EpsilonStone.HeroPowerFrenchItalian;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;


import hotstone.variants.AlphaStone.HeroPowerBaby;
import hotstone.variants.AlphaStone.SpanishDeck;

public class TestEpsilonStone {

    private Game game;

    /**
     * Fixture for GammaStone testing.
     */
    @BeforeEach
    public void setUp() {
        game = new StandardHotStoneGame(new SetMana3(),new WinAfter4Rounds(), new FrenchItalianChefs(), new HeroPowerFrenchItalian(), new SpanishDeck());
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

}
