package hotstone.standard;

import hotstone.framework.ObserverHandlerFixed;
import hotstone.framework.MutableGame;

import hotstone.framework.StoneFactory;
import hotstone.variants.AbstractFactory.AlphaStoneFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;


import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestObserver {
    private MutableGame game;
    private ObserverHandlerFixed observer;
    private StoneFactory stoneFactory;

    /**
     * Fixture for AlphaStone testing.
     */
    /* setMana3 is default for AlphaStone */
    @BeforeEach
    public void setUp() {
        observer = new ObserverHandlerFixed();
        game = new StandardHotStoneGame(new AlphaStoneFactory());
    }

    @Test
    public void doesObserverWork(){
        assertThat(observer.notifyPlayCard(), is("Card was played"));
    }
}
