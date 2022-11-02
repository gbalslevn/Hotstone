package hotstone.variants;


import hotstone.framework.*;
import hotstone.standard.StandardHotStoneGame;
import hotstone.variants.AbstractFactory.AlphaStoneFactory;
import hotstone.variants.AbstractFactory.DeltaStoneFactory;
import hotstone.variants.AlphaStone.NoCardEffect;
import hotstone.variants.AlphaStone.WinAfter4Rounds;
import hotstone.variants.DeltaStone.DishDeck;
import hotstone.variants.DeltaStone.SetManaTo7;
import hotstone.variants.GammaStone.HeroPowerThaiDanish;
import hotstone.variants.GammaStone.DanishThaiChefs;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
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
    public void shouldDisplayAllActions(){
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

        game.attackCard(Player.FINDUS,card,card1);
        game.endTurn();
        game.usePower(Player.PEDDERSEN);

    }
}
