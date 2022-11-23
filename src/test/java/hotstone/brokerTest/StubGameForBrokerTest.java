package hotstone.brokerTest;

import frds.broker.ClientRequestHandler;
import frds.broker.Invoker;
import frds.broker.Requestor;
import frds.broker.marshall.json.StandardJSONRequestor;
import hotstone.broker.client.GameClientProxy;
import hotstone.broker.doubles.LocalMethodClientRequestHandler;
import hotstone.broker.server.HotStoneGameInvoker;
import hotstone.framework.Card;
import hotstone.framework.Game;
import hotstone.framework.Player;
import hotstone.framework.Status;
import hotstone.standard.GameConstants;
import hotstone.standard.StandardHotStoneGame;
import hotstone.variants.AbstractFactory.AlphaStoneFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class StubGameForBrokerTest {
    private Game game;
    private Card card;

    @BeforeEach
    public void setUp(){
        Game servant = new StandardHotStoneGame(new AlphaStoneFactory());
        Invoker invoker = new HotStoneGameInvoker(servant);

        ClientRequestHandler crh = new LocalMethodClientRequestHandler(invoker);
        Requestor requestor = new StandardJSONRequestor(crh);
        game = new GameClientProxy(requestor);

        Game proxy = new GameClientProxy(requestor);
        card = proxy.getCardInHand(Player.FINDUS,0);
    }
    @Test
    public void ShouldHaveTurnNumber312(){
        assertThat(game.getTurnNumber(),is(1));
    }

    @Test
    public void shouldHaveHandsize3(){
        assertThat(game.getHandSize(Player.FINDUS), is(3));
    }

    @Test
    public void shouldHave13CardsInDeck(){
        assertThat(game.getDeckSize(Player.FINDUS), is(4));
    }
    @Test
    public void shouldHave1CardOnField(){
        assertThat(game.getFieldSize(Player.FINDUS),is(0));
    }

    @Test
    public void shouldBeFindusInturn(){
        assertThat(game.getPlayerInTurn(), is(Player.FINDUS));
    }
   /* @Test
    public void shouldbePeddersonWinning(){
        game.endTurn();
        game.endTurn();
        game.endTurn();
        game.endTurn();
        game.endTurn();
        System.out.println(game.getTurnNumber());
        assertThat(game.getWinner(), is(Player.FINDUS));
    }*/

    @Test
    public void shouldBeStatusOkToUsePower(){
        assertThat(game.usePower(Player.FINDUS), is(Status.OK));
    }

    @Test
    public void ShouldMakeGetCardInHandWork(){
        assertThat(card,is(notNullValue()));
        assertThat(card.getName(), is("Tres"));
        assertThat(card.getAttack(), is(3));
    }

    @Test
    public void shouldHaveCardUnoDoesTres(){
        System.out.println(game.getHand(Player.FINDUS));
//        assertThat(game.getHand(Player.FINDUS),"does");

        int count = game.getHandSize(Player.FINDUS);
        assertThat(count, is(3));
        // And these are ordered Tres, Dos, Uno in slot 0,1,2
        // Given card at index 0 in the hand
        Card card = game.getCardInHand(Player.FINDUS, 0);
        // Then is it Tres
        assertThat(card.getName(), is(GameConstants.TRES_CARD));
        // Given card at index 1 in the hand
        Card card1 = game.getCardInHand(Player.FINDUS, 1);
        // Then is it Dos
        assertThat(card1.getName(), is(GameConstants.DOS_CARD));
        // Given card at index 2 in the hand
        Card card2 = game.getCardInHand(Player.FINDUS, 2);
        // Then is it Uno
        assertThat(card2.getName(), is(GameConstants.UNO_CARD));
        // Given card at index 0 in the hand for Peddersen
        Card card3 = game.getCardInHand(Player.PEDDERSEN, 2);
        // Then is it Uno
        assertThat(card3.getName(), is(GameConstants.UNO_CARD));


    }


  /*  @Test
    public void shouldBeStatusOkToAttackHero(){
        game.playCard(Player.FINDUS, game.getCardInHand(Player.FINDUS,0));
        Card findusCard = game.getCardInField(Player.FINDUS, 0);
        assertThat(game.attackHero(Player.FINDUS, findusCard), is(Status.OK));
    }*/

  /*  @Test
    public void shouldBeStatusOkToAttackCard(){
        game.playCard(Player.FINDUS, game.getCardInHand(Player.FINDUS,0));
        Card findusCard = game.getCardInField(Player.FINDUS, 0);
        game.endTurn();
        game.playCard(Player.PEDDERSEN, game.getCardInHand(Player.PEDDERSEN,0));
        Card peddersonCard = game.getCardInField(Player.PEDDERSEN,0);
        assertThat(game.attackCard(Player.FINDUS,findusCard, peddersonCard), is(Status.OK));
    }*/

    /*@Test
    public void shouldBeStatusOkToPlayCard(){
        assertThat(game.playCard(Player.FINDUS, game.getCardInHand(Player.FINDUS,0)), is(Status.OK));
    }*/

}
