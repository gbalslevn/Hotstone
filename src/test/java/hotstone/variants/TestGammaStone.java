package hotstone.variants;

import hotstone.framework.Game;
import hotstone.framework.Player;
import hotstone.standard.GameConstants;
import hotstone.standard.StandardHotStoneGame;
import hotstone.variants.AlphaStone.NoEffect;
import hotstone.variants.AlphaStone.SpanishDeck;
import hotstone.variants.GammaStone.HeroPowerThaiDanish;
import hotstone.variants.GammaStone.DanishThaiChefs;
import hotstone.variants.AlphaStone.SetMana3;
import hotstone.variants.AlphaStone.WinAfter4Rounds;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestGammaStone {
    private Game game;

    /**
     * Fixture for GammaStone testing.
     */

    @BeforeEach
    public void setUp() {
        game = new StandardHotStoneGame(new SetMana3(), new WinAfter4Rounds(), new DanishThaiChefs(), new HeroPowerThaiDanish(), new SpanishDeck(), new NoEffect());
    }

    @Test
    public void shouldHaveTypeThaiChef(){
        assertThat(game.getHero(Player.FINDUS).getType(), is(GameConstants.THAI_CHEF_HERO_TYPE));
        assertThat(game.getHero(Player.PEDDERSEN).getType(), is(GameConstants.DANISH_CHEF_HERO_TYPE));
    }

    @Test
    public void shouldLooseTwoHealthWhenChiliPowerIsUsed(){
        int getHealthBefore = game.getHero(Player.PEDDERSEN).getHealth();
        game.usePower(Player.FINDUS);
        int getHealthAfter = game.getHero(Player.PEDDERSEN).getHealth();
        assertThat(getHealthAfter,is(getHealthBefore-2));
    }

    @Test
    public void shouldSpawnMinionOnFieldWhenSovsIsUsed(){
        game.endTurn();
        game.usePower(Player.PEDDERSEN);
        assertThat(game.getFieldSize(Player.PEDDERSEN), is(1));
    }

}
