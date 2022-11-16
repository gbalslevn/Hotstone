package hotstone.brokerTest;

import frds.broker.ClientRequestHandler;
import frds.broker.Invoker;
import frds.broker.Requestor;
import frds.broker.marshall.json.StandardJSONRequestor;
import hotstone.broker.client.GameClientProxy;
import hotstone.broker.client.HeroClientProxy;
import hotstone.broker.doubles.LocalMethodClientRequestHandler;
import hotstone.broker.doubles.StubGameForBroker;
import hotstone.broker.server.HotStoneGameInvoker;
import hotstone.framework.*;
import hotstone.standard.GameConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class StubHeroForBrokerTest {
    private Hero hero;

    @BeforeEach
    public void setUp(){
        Game servant = new StubGameForBroker();
        Invoker invoker = new HotStoneGameInvoker(servant);

        ClientRequestHandler crh = new LocalMethodClientRequestHandler(invoker);
        Requestor requestor = new StandardJSONRequestor(crh);
        hero = new HeroClientProxy(requestor);
    }

    @Test
    public void shouldHaveMana1(){
        assertThat(hero.getMana(), is(1));
    }

    @Test
    public void shouldHave7Health(){
        assertThat(hero.getHealth(), is(7));
    }

    @Test
    public void shouldHeroBeActive(){
        assertThat(hero.isActive(), is(false));
    }

    @Test
    public void shouldBeTypeBaby(){
        assertThat(hero.getType(), is(GameConstants.BABY_HERO_TYPE));
    }

    @Test
    public void shouldBeOwnerFindus(){
        assertThat(hero.getOwner(), is(Player.FINDUS));
    }
}
