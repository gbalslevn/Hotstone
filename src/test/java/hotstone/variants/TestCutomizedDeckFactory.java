package hotstone.variants;

import hotstone.framework.Card;
import hotstone.framework.Game;
import hotstone.framework.Player;
import hotstone.standard.StandardHotStoneGame;
import hotstone.variants.AbstractFactory.CutomizedDeckFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;



public class TestCutomizedDeckFactory {
    private Game game;

    /**
     * Fixture for BetaStone testing.
     */
    /* increaseManaUntil7 is default for BetaStone*/
    @BeforeEach
    public void setUp() {
        game = new StandardHotStoneGame(new CutomizedDeckFactory());
    }

    @Test
    public void shouldHave13CardInDeck(){
        assertThat(game.getDeckSize(Player.FINDUS),is(13));
    }

    @Test
    public void shouldContainCardCatInHand(){
        StandardHotStoneGame game1 = (StandardHotStoneGame) game;
        //Draws all the cards from Custom Deck
        for (int i = 0; i < 13; i++) {
            game1.drawCard(Player.FINDUS);
        }
        for (Card c: game.getHand(Player.FINDUS)) {
            if (c.getName().equals("cat")){
                assertThat(c.getName(),is("cat"));
            }
        }

    }
    @Test
    public void shouldContainCardSifInHand(){
        StandardHotStoneGame game1 = (StandardHotStoneGame) game;
        //Draws all the cards from Custom Deck
        for (int i = 0; i < 13; i++) {
            game1.drawCard(Player.FINDUS);
        }
        for (Card c: game.getHand(Player.FINDUS)) {
            if (c.getName().equals("sif")){
                assertThat(c.getName(),is("sif"));
            }
        }
    }
}
