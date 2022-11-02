package hotstone.standard;

import hotstone.Observer.GameObserver;
import hotstone.Observer.GameObserverSpy;
import hotstone.Observer.Observable;
import hotstone.framework.MutableGame;
import hotstone.framework.StoneFactory;
import hotstone.utility.TestHelper;
import hotstone.variants.AbstractFactory.*;
import org.junit.jupiter.api.BeforeEach;



import hotstone.framework.*;
import hotstone.variants.AbstractFactory.AlphaStoneFactory;
import hotstone.variants.AlphaStone.*;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Template for your own ongoing TDD process.
 * Fill it out until you have covered all
 * requirements for the alpha stone game.
 */

public class TestObserver {
    private MutableGame game;

    private StoneFactory stoneFactory;
    private GameObserverSpy observer;

    /**
     * Fixture for Observer testing.
     */
    /* setMana3 is default for AlphaStone */
    @BeforeEach
    public void setUp() {
        game = new StandardHotStoneGame(new SemiStoneFactory());
        observer = new GameObserverSpy();
        game.addObserver(observer);
    }

    @Test
    public void shouldOutputCardPlay(){
        Card card = game.getCardInHand(Player.FINDUS, 2);
        game.playCard(Player.FINDUS, card);
        assertThat(observer.lastMethodCalled(), is("onCardPlay"));
    }

    @Test
    public void shouldChangeToOnTurn(){
        game.endTurn();
        assertThat(observer.lastMethodCalled(), is("onTurnChangeTo"));
    }

    @Test
    public void shouldOutputAttackCard(){
        Card card = game.getCardInHand(Player.FINDUS, 2);
        game.playCard(Player.FINDUS, card);
        game.endTurn();
        Card cardDefending = game.getCardInHand(Player.PEDDERSEN, 2);
        game.playCard(Player.PEDDERSEN, cardDefending);
        game.endTurn();
        game.attackCard(Player.FINDUS, card, cardDefending);
        assertThat(observer.lastMethodCalled(), is("onAttackCard"));
    }

    @Test
    public void shouldOutputGameWinner(){
        HeroImpl hero = (HeroImpl) game.getHero(Player.FINDUS);
        hero.changeHealth(-21);
        game.endTurn();
        System.out.println(hero.getHealth());
        assertThat(observer.lastMethodCalled(), is("onGameWon"));
    }

    @Test
    public void shouldOutputusePower(){
        game.usePower(Player.PEDDERSEN);
        assertThat(observer.lastMethodCalled(), is("onUsePower"));
        assertThat(game.getFieldSize(Player.FINDUS),is(1));
    }
}













/*
 * Copyright (C) 2022. Henrik Bærbak Christensen, Aarhus University.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *
 *  You may obtain a copy of the License at
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */



