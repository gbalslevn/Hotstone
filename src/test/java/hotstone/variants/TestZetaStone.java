package hotstone.variants;

import hotstone.framework.Card;
import hotstone.framework.Game;
import hotstone.framework.Player;
import hotstone.standard.GameConstants;
import hotstone.standard.HeroImpl;
import hotstone.standard.StandardHotStoneGame;
import hotstone.standard.Stats;
import hotstone.variants.AbstractFactory.ZetaStoneFactory;
import hotstone.variants.AlphaStone.*;
import hotstone.variants.BetaStone.WinWhenHealthIs0;
import hotstone.variants.EpsilonStone.WinAfter7DamageOutput;
import hotstone.variants.ZetatStone.AlternatingWinner;
import hotstone.variants.ZetatStone.CincoDeck;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestZetaStone {
    private Game game;

    /**
     * Fixture for ZetaStone testing.
     */

    @BeforeEach
    public void setUp() {
        game = new StandardHotStoneGame(new ZetaStoneFactory());
    }

    @Test
    public void deckShouldContainOnlyCincos() {
        for (Card c : ((StandardHotStoneGame) game).getDeck(Player.FINDUS)) {
            assertThat(c.getName(), is(GameConstants.CINCO_CARD));
        }
    }

    @Test
    public void findusShouldWinWithWhenOpponentHeroHealthIs0InRound3() {
        //Set Pedderson hero to 0 hp
        HeroImpl heroPedderson = (HeroImpl) game.getHero(Player.PEDDERSEN);
        heroPedderson.changeHealth(-21);
        assertThat(game.getWinner(), is(Player.FINDUS));
    }

    @Test
    public void findusShouldWinWith20DamageOutputInRound10() {
        // resets the damageOutput
        Stats.setDamageOutput(Player.FINDUS, 0, game.getTurnNumber());
        Stats.changeDamageOutput(Player.FINDUS, 5, game.getTurnNumber());
        game.endTurn();
        game.endTurn();
        game.endTurn();
        game.endTurn();
        game.endTurn();
        game.endTurn();
        game.endTurn();
        game.endTurn();
        game.endTurn();
        game.endTurn();
        game.endTurn();
        game.endTurn();
        game.getWinner();
        game.endTurn();
        game.endTurn();
        game.endTurn();
        game.endTurn();
        game.endTurn();
        game.endTurn();
        game.endTurn();
        game.endTurn();
        game.endTurn();
        game.endTurn();
        game.endTurn();
        game.endTurn();
        game.endTurn();
        game.endTurn();
        Stats.changeDamageOutput(Player.FINDUS, 20, game.getTurnNumber());
        assertThat(Stats.getDamageOutput(Player.FINDUS, game.getTurnNumber()), is(20));
        assertThat(game.getWinner(), is(Player.FINDUS));
    }

    @Test
    public void shouldNotWinIfAttackOutputIs20ButRoundIs5(){
        // resets the damageOutput
        Stats.setDamageOutput(Player.FINDUS, 0, game.getTurnNumber());
        game.endTurn();
        game.endTurn();
        Stats.changeDamageOutput(Player.FINDUS, 20, game.getTurnNumber());
        assertThat(Stats.getDamageOutput(Player.FINDUS, game.getTurnNumber()), is(20));
        assertThat(game.getWinner(), not(Player.FINDUS));
        assertThat(game.getWinner(), not(Player.PEDDERSEN));
    }
}
