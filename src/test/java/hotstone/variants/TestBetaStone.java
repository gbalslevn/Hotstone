package hotstone.variants;

import hotstone.framework.*;
import hotstone.standard.HeroImpl;
import hotstone.variants.BetaStone.IncreaseManaUntil7;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import hotstone.framework.Game;
import hotstone.standard.StandardHotStoneGame;
import hotstone.variants.BetaStone.WinWhenHealthIs0;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestBetaStone {
    private Game game;

    /**
     * Fixture for AlphaStone testing.
     */
    /* increaseManaUntil7 is default for BetaStone*/
    @BeforeEach
    public void setUp() {
        game = new StandardHotStoneGame(new IncreaseManaUntil7(), new WinWhenHealthIs0());
    }
    @Test
    public void shouldManaBe2InRound2() {
        game.endTurn();
        game.endTurn();
        assertThat(game.getHero(Player.FINDUS).getMana(), is(2));
    }

    @Test
    public void shouldManaBe7InRound10(){
        for (int i = 0; i < 18; i++) {
            game.endTurn();
        }
        assertThat(game.getHero(Player.FINDUS).getMana(), is(7));
    }
    @Test
    public void shouldWinWhenEnemyHeroIsAt0Health(){
        //Set Pedderson hero to 0 hp
        HeroImpl heroPedderson = (HeroImpl) game.getHero(Player.PEDDERSEN);
        heroPedderson.changeHealth(-21);
        assertThat(game.getWinner(),is(Player.FINDUS));
    }
}
