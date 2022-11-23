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
import hotstone.standard.StandardHotStoneGame;
import hotstone.variants.AbstractFactory.AlphaStoneFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class GameForBrokerTest {
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
    public void ShouldMakeGetCardInHandWork(){
        assertThat(card,is(notNullValue()));
        assertThat(card.getName(), is("Tres"));
        assertThat(card.getAttack(), is(3));
    }
}
