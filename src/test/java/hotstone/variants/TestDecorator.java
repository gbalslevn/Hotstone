package hotstone.variants;


import hotstone.framework.Card;
import hotstone.framework.Game;
import hotstone.framework.Player;
import hotstone.standard.StandardHotStoneGame;
import hotstone.variants.AbstractFactory.AlphaStoneFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
public class TestDecorator {
    private Game game;

    /**
     * Fixture for Decorator.
     */

    @BeforeEach
    public void setUp() {
        game = new StandardHotStoneGame(new AlphaStoneFactory());
    }


    @Test
    public void shouldDisplayActions(){
        Game original = game;
        game = new LoggingStandartHotstoneGame(game);
        // Findus plays first card
        Card card = game.getCardInHand(Player.FINDUS, 0);
        game.playCard(Player.FINDUS, card);
        game.endTurn();
        // Peddersen plays first card
        Card card1 = game.getCardInHand(Player.PEDDERSEN, 0);
        game.playCard(Player.PEDDERSEN, card1);
        game.endTurn();


        original.attackCard(Player.FINDUS,card,card1);
        original.endTurn();
        original.usePower(Player.PEDDERSEN);

    }
}
