package hotstone.variants;

import hotstone.Stub.RandomFixed;
import hotstone.framework.Card;
import hotstone.framework.Game;
import hotstone.framework.Player;
import hotstone.standard.CardImpl;
import hotstone.standard.GameConstants;
import hotstone.standard.StandardHotStoneGame;
import hotstone.utility.TestHelper;
import hotstone.variants.AlphaStone.WinAfter4Rounds;
import hotstone.variants.DeltaStone.SetManaTo7;
import hotstone.variants.EpsilonStone.RandomReal;
import hotstone.variants.EtaStone.CardEffect;
import hotstone.variants.EtaStone.EtaStoneDishDeck;
import hotstone.variants.GammaStone.DanishThaiChefs;
import hotstone.variants.GammaStone.HeroPowerThaiDanish;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestEtaStone {
    private Game game;

    /**
     * Fixture for GammaStone testing.
     */

    @BeforeEach
    public void setUp() {
        game = new StandardHotStoneGame(new SetManaTo7(), new WinAfter4Rounds(), new DanishThaiChefs(), new HeroPowerThaiDanish(), new EtaStoneDishDeck(), new CardEffect(new RandomReal()));
    }

    @Test
    public void shouldBrownRiceDamage1ToOpponentHero() {
        Card card = new CardImpl(GameConstants.BROWN_RICE_CARD, 1, 1, 1, false, Player.FINDUS);
        int healthBefore = game.getHero(Player.PEDDERSEN).getHealth();
        game.playCard(Player.FINDUS, card);
        int healthAfter = game.getHero(Player.PEDDERSEN).getHealth();
        assertThat(healthAfter, is(healthBefore - 1));
    }

    @Test
    public void shouldAdd1AttackToOwnRandomMinion() {
        Card card1 = new CardImpl(GameConstants.BROWN_RICE_CARD, 1, 1, 1, false, Player.FINDUS);
        game.playCard(Player.FINDUS, card1);
        game.endTurn();
        game.endTurn();
        int attackBefore = card1.getAttack();
        CardImpl card = new CardImpl(GameConstants.TOMATO_SALAD_CARD, 2, 2, 2, false, Player.FINDUS);
        game.playCard(Player.FINDUS, card);
        int attackAfter = card1.getAttack();
        assertThat(attackAfter, is(attackBefore + 1));
    }

    @Test
    public void shouldDoNothingIfNoCardsAreOnTheField() {
        CardImpl card = new CardImpl(GameConstants.TOMATO_SALAD_CARD, 2, 2, 2, false, Player.FINDUS);
        int attackBefore = card.getAttack();
        game.playCard(Player.FINDUS, card);
        int attackAfter = card.getAttack();
        assertThat(attackAfter, is(attackBefore));
    }

    @Test
    public void shouldGive2HealthToOwnHero() {
        CardImpl card = new CardImpl(GameConstants.POKE_BOWL_CARD, 3, 2, 3, false, Player.FINDUS);
        int healthBefore = game.getHero(Player.FINDUS).getHealth();
        game.playCard(Player.FINDUS, card);
        int healthAfter = game.getHero(Player.FINDUS).getHealth();
        assertThat(healthAfter, is(healthBefore +2));
    }

    @Test
    public void shouldDrawCardWhenNoodlesoupCardIsPlayed() {
        CardImpl card = new CardImpl(GameConstants.NOODLE_SOUP_CARD, 4, 5, 3, false, Player.FINDUS);
        int handSizeBefore = game.getHandSize(Player.FINDUS);
        game.playCard(Player.FINDUS, card);
        int handSizeAfter = game.getHandSize(Player.FINDUS);
        assertThat(handSizeAfter, is(handSizeBefore +1));
    }

    @Test
    public void shouldKillRandomOppMinionWhenChickenCurryIsUsed() {
        game.endTurn();
        CardImpl card1 = new CardImpl(GameConstants.NOODLE_SOUP_CARD, 4, 5, 3, false, Player.PEDDERSEN);
        game.playCard(Player.PEDDERSEN, card1);
        game.endTurn();
        CardImpl card = new CardImpl(GameConstants.CHICKEN_CURRY_CARD, 6, 4, 4, false, Player.FINDUS);
        int fieldSizeBefore = game.getFieldSize(Player.PEDDERSEN);
        game.playCard(Player.FINDUS, card);
        int fieldSizeAfter = game.getFieldSize(Player.PEDDERSEN);
        assertThat(fieldSizeAfter, is(fieldSizeBefore - 1));
    }
    @Test
    public void shouldAdd2AttackToOpponentMinion(){
        game.endTurn();
        CardImpl card1 = new CardImpl(GameConstants.NOODLE_SOUP_CARD, 4, 5, 3, false, Player.PEDDERSEN);
        game.playCard(Player.PEDDERSEN, card1);
        game.endTurn();
        CardImpl card = new CardImpl(GameConstants.BEEF_BURGER_CARD, 6, 8, 6, false, Player.FINDUS);
        int damageBefore = card1.getAttack();
        game.playCard(Player.FINDUS, card);
        int damageAfter = card1.getAttack();
        assertThat(damageAfter, is(damageBefore +2 ));
    }

/*
    public void drawAllCards(Player who) {
        StandardHotStoneGame game1 = (StandardHotStoneGame) game;
        for (int i = 0; i <= 21; i++) {
            game1.drawCard(who);
        }
    }*/


}
