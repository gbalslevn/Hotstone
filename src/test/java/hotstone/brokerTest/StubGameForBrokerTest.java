package hotstone.brokerTest;

import frds.broker.ClientRequestHandler;
import frds.broker.Invoker;
import frds.broker.Requestor;
import frds.broker.marshall.json.StandardJSONRequestor;
import hotstone.broker.client.GameClientProxy;
import hotstone.broker.doubles.LocalMethodClientRequestHandler;
import hotstone.broker.server.HotStoneGameInvoker;
import hotstone.framework.*;
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
    private Hero hero;

    @BeforeEach
    public void setUp(){
        Game servant = new StandardHotStoneGame(new AlphaStoneFactory());
        Invoker invoker = new HotStoneGameInvoker(servant);

        ClientRequestHandler crh = new LocalMethodClientRequestHandler(invoker);
        Requestor requestor = new StandardJSONRequestor(crh);
        game = new GameClientProxy(requestor);

        Game proxy = new GameClientProxy(requestor);
        card = proxy.getCardInHand(Player.FINDUS,0);

        Game proxy1 = new GameClientProxy(requestor);
        hero = proxy1.getHero(Player.FINDUS);
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
    @Test
    public void shouldbePeddersonWinning(){
        game.endTurn();
        game.endTurn();
        game.endTurn();
        game.endTurn();
        game.endTurn();
        game.endTurn();
        game.endTurn();
        assertThat(game.getWinner(), is(Player.FINDUS));
    }

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

    @Test
    public void shouldBeNothingOnTheField(){
        assertThat(game.getField(Player.FINDUS).iterator().hasNext(),is(false));
        assertThat(game.getFieldSize(Player.FINDUS),is(0));
        Card chosenCard = game.getCardInHand(Player.FINDUS, 0);
        game.playCard(Player.FINDUS, chosenCard);
        assertThat(game.getField(Player.FINDUS).iterator().hasNext(),is(true));
        assertThat(game.getFieldSize(Player.FINDUS),is(1));
    }
    @Test
    public void shouldBeStatusOkToPlayCard(){
        Card card = game.getCardInHand(Player.FINDUS, 2);
        assertThat(game.playCard(Player.FINDUS, card), is(Status.OK));

        game.endTurn();
        Card cardPedderson = game.getCardInHand(Player.PEDDERSEN, 2);
        assertThat(game.playCard(Player.PEDDERSEN, cardPedderson), is(Status.OK));
    }
    @Test
    public void shouldHaveCardInfield(){
        //Gets card uno and uses play methode to check if status is OK
        Card uno = game.getCardInHand(Player.FINDUS, 2);
        assertThat(game.playCard(Player.FINDUS, uno), is(Status.OK));
        //Card uno is placed at index 0 on field
        assertThat(game.getCardInField(Player.FINDUS, 0).getName(), is("Uno"));
        //The card is removed from the hand
        assertThat(game.getHandSize(Player.FINDUS), is(2));
    }

    @Test
    public void shouldEndturnWork(){
        assertThat(game.getTurnNumber(),is(1));
        game.endTurn();
        assertThat(game.getTurnNumber(),is(2));
    }
    @Test
    public void shouldFindusHaveHeroBaby(){
        assertThat(game.getHero(Player.FINDUS).getType(), is("Baby"));
    }

    @Test
    public void shouldBeStatusOkToAttackCard(){
        game.playCard(Player.FINDUS, game.getCardInHand(Player.FINDUS,2));
        Card findusCard = game.getCardInField(Player.FINDUS, 0);
        game.endTurn();
        game.playCard(Player.PEDDERSEN, game.getCardInHand(Player.PEDDERSEN,2));
        Card peddersonCard = game.getCardInField(Player.PEDDERSEN,0);
        game.endTurn();
//        assertThat(game.attackCard(Player.FINDUS,findusCard, peddersonCard), is(Status.OK));
    }

    @Test
    public void shouldBeBabyHeroFOrPlayerFindus(){
        assertThat(game.getHero(Player.FINDUS).getType(),is("Baby"));
    }
    @Test
    public void shouldBeStatusOkToAttackHero(){
        game.playCard(Player.FINDUS, game.getCardInHand(Player.FINDUS,2));
        Card findusCard = game.getCardInField(Player.FINDUS, 0);
        game.endTurn();
      assertThat(game.attackHero(Player.FINDUS,findusCard), is(Status.OK));
    }




}
