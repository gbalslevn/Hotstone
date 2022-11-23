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
import hotstone.standard.GameConstants;
import hotstone.standard.StandardHotStoneGame;
import hotstone.variants.AbstractFactory.AlphaStoneFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class StubCardForBrokerTest {
    private Card card;
    private Game servant;
    private Requestor requestor;

    @BeforeEach
    public void setUp(){
        servant = new StandardHotStoneGame(new AlphaStoneFactory());
        Invoker invoker = new HotStoneGameInvoker(servant);
        ClientRequestHandler crh = new LocalMethodClientRequestHandler(invoker);
        requestor = new StandardJSONRequestor(crh);

        servant = new GameClientProxy(requestor);
        card = servant.getCardInHand(Player.FINDUS, 0);

    }

    @Test
    public void shouldHaveNameNoodleSoup(){
        assertThat(card.getName(),is(GameConstants.TRES_CARD));
    }
    @Test
    public void shouldHave1Mana(){
        assertThat(card.getManaCost(),is(3));
    }
    @Test
    public void shouldHave1Attack(){
        assertThat(card.getAttack(),is(3));
    }

    @Test
    public void shouldHave1health(){
        assertThat(card.getHealth(),is(3));
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
