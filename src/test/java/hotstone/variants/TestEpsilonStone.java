package hotstone.variants;

import hotstone.framework.Game;
import hotstone.framework.Player;
import hotstone.standard.GameConstants;
import hotstone.standard.StandardHotStoneGame;
import hotstone.variants.AlphaStone.*;
import hotstone.variants.EpsilonStone.FrenchItalianChefs;
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
        game = new StandardHotStoneGame(new SetMana3(),new WinAfter4Rounds(), new FrenchItalianChefs(), new HeroPowerBaby(), new SpanishDeck());
    }

    @Test
    public void shouldFindusHeroBeFrenchChef(){
        assertThat(game.getHero(Player.FINDUS).getType(),is(GameConstants.FRENCH_CHEF_HERO_TYPE));
    }

}
