package hotstone.variants;

import hotstone.Stub.RandomFixed;
import hotstone.framework.Card;
import hotstone.framework.Game;
import hotstone.framework.Player;
import hotstone.standard.CardImpl;
import hotstone.standard.GameConstants;
import hotstone.standard.StandardHotStoneGame;
import hotstone.variants.AlphaStone.WinAfter4Rounds;
import hotstone.variants.DeltaStone.SetManaTo7;
import hotstone.variants.EtaStone.CardEffect;
import hotstone.variants.EtaStone.EtaStoneDishDeck;
import hotstone.variants.GammaStone.DanishThaiChefs;
import hotstone.variants.GammaStone.HeroPowerThaiDanish;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestEtaStoneFixed {
    private Game game;

    /**
     * Fixture for GammaStone testing.
     */

    @BeforeEach
    public void setUp() {
        game = new StandardHotStoneGame(
                new SetManaTo7(), new WinAfter4Rounds(),
                new DanishThaiChefs(), new HeroPowerThaiDanish(),
                new EtaStoneDishDeck(), new CardEffect(new RandomFixed(0)));
    }

    @Test
    public void shouldAdd1AttackToIndex0(){
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
}
