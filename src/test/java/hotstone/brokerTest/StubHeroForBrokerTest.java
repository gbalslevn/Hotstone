package hotstone.brokerTest;

import frds.broker.ClientRequestHandler;
import frds.broker.Invoker;
import frds.broker.Requestor;
import frds.broker.marshall.json.StandardJSONRequestor;
import hotstone.broker.client.GameClientProxy;
import hotstone.broker.doubles.LocalMethodClientRequestHandler;
import hotstone.broker.server.HotStoneGameInvoker;
import hotstone.framework.Game;
import hotstone.framework.Hero;
import hotstone.framework.Player;
import hotstone.standard.GameConstants;
import hotstone.standard.StandardHotStoneGame;
import hotstone.variants.AbstractFactory.AlphaStoneFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class StubHeroForBrokerTest {
    private Hero hero;
    private String objectId;

    @BeforeEach
    public void setUp(){
        Game servant = new StandardHotStoneGame(new AlphaStoneFactory());
        Invoker invoker = new HotStoneGameInvoker(servant);

        ClientRequestHandler crh = new LocalMethodClientRequestHandler(invoker);
        Requestor requestor = new StandardJSONRequestor(crh);
//        hero = new HeroClientProxy(requestor, objectId);

        servant = new GameClientProxy(requestor);
        hero = servant.getHero(Player.FINDUS);

    }

    @Test
    public void shouldHaveMana1(){
        assertThat(hero.getMana(), is(3));
    }

    @Test
    public void shouldHave7Health(){
        assertThat(hero.getHealth(), is(21));
    }

    @Test
    public void shouldHeroBeActive(){
        assertThat(hero.isActive(), is(true));
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
