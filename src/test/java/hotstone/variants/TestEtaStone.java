package hotstone.variants;

import hotstone.framework.Card;
import hotstone.framework.Game;
import hotstone.framework.Player;
import hotstone.standard.CardImpl;
import hotstone.standard.GameConstants;
import hotstone.standard.StandardHotStoneGame;
import hotstone.utility.TestHelper;
import hotstone.variants.AlphaStone.NoEffect;
import hotstone.variants.AlphaStone.WinAfter4Rounds;
import hotstone.variants.DeltaStone.DishDeck;
import hotstone.variants.DeltaStone.SetManaTo7;
import hotstone.variants.EtaStone.Effect;
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
        game = new StandardHotStoneGame(new SetManaTo7(), new WinAfter4Rounds(), new DanishThaiChefs(), new HeroPowerThaiDanish(), new DishDeck(), new Effect());
    }

    @Test
    public void shouldBrownRiceDamage1ToOpponentHero() {
        //drawAllCards(Player.FINDUS);
        Card card = new CardImpl(GameConstants.BROWN_RICE_CARD, 1, 1, 2, false, Player.FINDUS);
        int healthBefore = game.getHero(Player.PEDDERSEN).getHealth();
        //Card card = game.getCardInHand(Player.FINDUS, 2);
        game.playCard(Player.FINDUS, card);
        int healthAfter = game.getHero(Player.PEDDERSEN).getHealth();
        assertThat(healthAfter, is(healthBefore - 1));
    }

    //@Test
    //public void shouldAdd1AttackToOwnRandomMinion() {
        //drawAllCards(Player.FINDUS);
        //TestHelper.printGameState(game);
        //Card card1 = new CardImpl(GameConstants.BROWN_RICE_CARD, 1, 1, 2, false, Player.FINDUS);
        //game.playCard(Player.FINDUS, card1);
        //game.endTurn();
        //game.endTurn();
        //CardImpl card = new CardImpl(GameConstants.TOMATO_SALAD_CARD, 2, 3, 2, false, Player.FINDUS);
        //int attackBefore = card1.getAttack();
        //game.playCard(Player.FINDUS, card);
        //int attackAfter = card1.getAttack();
        //assertThat(attackBefore + 1, is(attackAfter));
    //}

    public void drawAllCards(Player who) {
        StandardHotStoneGame game1 = (StandardHotStoneGame) game;
        for (int i = 0; i <= 21; i++) {
            game1.drawCard(who);
        }
    }


}
