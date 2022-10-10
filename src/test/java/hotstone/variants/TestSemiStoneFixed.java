package hotstone.variants;

import hotstone.framework.Game;
import hotstone.framework.Player;
import hotstone.standard.GameConstants;
import hotstone.standard.StandardHotStoneGame;
import hotstone.variants.AbstractFactory.SemiStoneFactoryFixed;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestSemiStoneFixed {

    private Game game;


    @BeforeEach
    public void setUp(){
        game = new StandardHotStoneGame(new SemiStoneFactoryFixed());
    }

    @Test
    public void shouldPeddersonGetThaichefHeroType(){
        assertThat(game.getHero(Player.PEDDERSEN).getType(), is(GameConstants.THAI_CHEF_HERO_TYPE));
    }
    @Test
    public void shouldPeddersonDamageOpponentHero2(){
        game.endTurn();
        int healthBefore = game.getHero(Player.FINDUS).getHealth();
        game.usePower(Player.PEDDERSEN);
        int healthAfter = game.getHero(Player.FINDUS).getHealth();
        assertThat(healthAfter, is (healthBefore - 2 ));
    }
}
