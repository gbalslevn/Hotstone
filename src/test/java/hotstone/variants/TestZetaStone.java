package hotstone.variants;

import hotstone.Stub.RandomFixed;
import hotstone.framework.Card;
import hotstone.framework.Game;
import hotstone.framework.Player;
import hotstone.standard.GameConstants;
import hotstone.standard.HeroImpl;
import hotstone.standard.StandardHotStoneGame;
import hotstone.variants.AlphaStone.*;
import hotstone.variants.BetaStone.WinWhenHealthIs0;
import hotstone.variants.EpsilonStone.WinAfter7DamageOutput;
import hotstone.variants.EtaStone.Effect;
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
        game = new StandardHotStoneGame(new SetMana3(), new AlternatingWinner(new WinWhenHealthIs0(), new WinAfter7DamageOutput()), new TypeBaby(), new HeroPowerBaby(), new CincoDeck(), new NoEffect());
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
    public void findusShouldWinWith7DamageOutputInRound10() {
        game.endTurn();
        game.endTurn();
        game.endTurn();
        game.endTurn();
        game.endTurn();
        game.endTurn();
        System.out.println("Turn number " + game.getTurnNumber());
        Card card = game.getCardInHand(Player.FINDUS, 0);
        game.playCard(Player.FINDUS,card);
        game.endTurn();
        Card card1 = game.getCardInHand(Player.PEDDERSEN, 0);
        game.playCard(Player.PEDDERSEN,card1);
        game.endTurn();
        Card card2 = game.getCardInHand(Player.FINDUS, 0);
        game.playCard(Player.FINDUS,card2);
        game.endTurn();
        Card card3 = game.getCardInHand(Player.PEDDERSEN, 0);
        game.playCard(Player.PEDDERSEN,card3);
        game.endTurn();
        Card card4 = game.getCardInHand(Player.FINDUS, 0);
        game.playCard(Player.FINDUS,card4);
        game.endTurn();
        Card card5 = game.getCardInHand(Player.PEDDERSEN, 0);
        game.playCard(Player.PEDDERSEN,card5);
        game.endTurn();
        StandardHotStoneGame game1 = (StandardHotStoneGame) game;
        game1.drawCard(Player.FINDUS);
        Card card6 = game.getCardInHand(Player.FINDUS, 0);
        game.playCard(Player.FINDUS,card6);
        game.endTurn();
        game1.drawCard(Player.PEDDERSEN);
        Card card7 = game.getCardInHand(Player.PEDDERSEN, 0);
        game.playCard(Player.PEDDERSEN,card7);
        game.endTurn();
        System.out.println("Turn number after " + game.getTurnNumber());
        game.attackCard(Player.FINDUS,card,card1);
        game.attackCard(Player.FINDUS,card2,card3);
        System.out.println("Damaga output" + ((StandardHotStoneGame) game).getDamageOutput(Player.FINDUS));
        game.endTurn();
        game.endTurn();
        game.attackCard(Player.FINDUS,card,card1);
        game.attackCard(Player.FINDUS,card2,card3);
        game.endTurn();
        game.endTurn();
        game.attackCard(Player.FINDUS,card4,card5);
        game.attackCard(Player.FINDUS,card6,card7);
        System.out.println("Turn number after " + game.getTurnNumber());
        System.out.println("Damaga output after turn 13 " + ((StandardHotStoneGame) game).getDamageOutput(Player.FINDUS));
        game.endTurn();
        game.endTurn();

        game.attackCard(Player.FINDUS,card4,card5);
        System.out.println("Turn number after " + game.getTurnNumber());
        System.out.println(((StandardHotStoneGame) game).getDamageOutput(Player.FINDUS));
        assertThat(game.getWinner(),is(Player.FINDUS));
    }

}
