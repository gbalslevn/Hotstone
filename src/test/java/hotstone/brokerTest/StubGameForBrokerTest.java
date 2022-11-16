package hotstone.brokerTest;

import frds.broker.ClientRequestHandler;
import frds.broker.Invoker;
import frds.broker.Requestor;
import frds.broker.marshall.json.StandardJSONRequestor;
import hotstone.broker.client.GameClientProxy;
import hotstone.broker.doubles.LocalMethodClientRequestHandler;
import hotstone.broker.doubles.StubGameForBroker;
import hotstone.broker.server.HotStoneGameInvoker;
import hotstone.framework.Card;
import hotstone.framework.Game;
import hotstone.framework.Player;
import hotstone.framework.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class StubGameForBrokerTest {
    private Game game;

    @BeforeEach
    public void setUp(){
        Game servant = new StubGameForBroker();
        Invoker invoker = new HotStoneGameInvoker(servant);

        ClientRequestHandler crh = new LocalMethodClientRequestHandler(invoker);
        Requestor requestor = new StandardJSONRequestor(crh);
        game = new GameClientProxy(requestor);
    }
    @Test
    public void ShouldHaveTurnNumber312(){
        assertThat(game.getTurnNumber(),is(312));
    }

    @Test
    public void shouldHaveHandsize3(){
        assertThat(game.getHandSize(Player.FINDUS), is(3));
    }

    @Test
    public void shouldHave13CardsInDeck(){
        assertThat(game.getDeckSize(Player.FINDUS), is(13));
    }
    @Test
    public void shouldHave1CardOnField(){
        assertThat(game.getFieldSize(Player.FINDUS),is(1));
    }

    @Test
    public void shouldBeFindusInturn(){
        assertThat(game.getPlayerInTurn(), is(Player.FINDUS));
    }
    @Test
    public void shouldbePeddersonWinning(){
        assertThat(game.getWinner(), is(Player.PEDDERSEN));
    }
    @Test
    public void shouldBeStatusOkToPlayCard(){
        assertThat(game.playCard(Player.FINDUS, game.getCardInHand(Player.FINDUS,0)), is(Status.OK));
    }
    @Test
    public void shouldBeStatusOkToAttackCard(){
        game.playCard(Player.FINDUS, game.getCardInHand(Player.FINDUS,0));
        Card findusCard = game.getCardInField(Player.FINDUS, 0);
        game.endTurn();
        game.playCard(Player.PEDDERSEN, game.getCardInHand(Player.PEDDERSEN,0));
        Card peddersonCard = game.getCardInField(Player.PEDDERSEN,0);
        assertThat(game.attackCard(Player.FINDUS,findusCard, peddersonCard), is(Status.OK));
    }

    @Test
    public void shouldBeStatusOkToAttackHero(){
        game.playCard(Player.FINDUS, game.getCardInHand(Player.FINDUS,0));
        Card findusCard = game.getCardInField(Player.FINDUS, 0);
        assertThat(game.attackHero(Player.FINDUS, findusCard), is(Status.OK));
    }

    @Test
    public void shouldBeStatusOkToUsePower(){
        assertThat(game.usePower(Player.FINDUS), is(Status.OK));
    }
}
