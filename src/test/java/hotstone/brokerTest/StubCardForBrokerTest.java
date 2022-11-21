package hotstone.brokerTest;

import frds.broker.ClientRequestHandler;
import frds.broker.Invoker;
import frds.broker.Requestor;
import frds.broker.marshall.json.StandardJSONRequestor;
import hotstone.broker.client.CardClientProxy;
import hotstone.broker.doubles.LocalMethodClientRequestHandler;
import hotstone.broker.doubles.StubGameForBroker;
import hotstone.broker.server.HotStoneGameInvoker;
import hotstone.framework.Card;
import hotstone.framework.Game;
import hotstone.framework.Player;
import hotstone.standard.GameConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class StubCardForBrokerTest {
    private Card card;
    private String objectId = "pending";

    @BeforeEach
    public void setUp(){
        Game servant = new StubGameForBroker();
        Invoker invoker = new HotStoneGameInvoker(servant);

        ClientRequestHandler crh = new LocalMethodClientRequestHandler(invoker);
        Requestor requestor = new StandardJSONRequestor(crh);
        card = new CardClientProxy(requestor, objectId);
    }

    @Test
    public void shouldHaveNameNoodleSoup(){
        assertThat(card.getName(),is(GameConstants.NOODLE_SOUP_CARD));
    }
    @Test
    public void shouldHave1Mana(){
        assertThat(card.getManaCost(),is(1));
    }
    @Test
    public void shouldHave1Attack(){
        assertThat(card.getAttack(),is(1));
    }

    @Test
    public void shouldHave1health(){
        assertThat(card.getHealth(),is(1));
    }
    @Test
    public void shouldBeActive(){
        assertThat(card.isActive(),is(false));
    }
    @Test
    public void shouldBeFindusOwner(){
        assertThat(card.getOwner(),is(Player.FINDUS));
    }
}
