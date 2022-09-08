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

package hotstone.standard;

/**
 * Skeleton class for AlphaStone test cases
 * <p>
 * This source code is from the book
 * "Flexible, Reliable Software:
 * Using Patterns and Agile Development"
 * 2nd Edition
 * Author:
 * Henrik Bærbak Christensen
 * Department of Computer Science
 * Aarhus University
 */

import hotstone.framework.*;
import hotstone.utility.TestHelper;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Template for your own ongoing TDD process.
 * Fill it out until you have covered all
 * requirements for the alpha stone game.
 */
public class TestAlphaStone {
    private Game game;


    /**
     * Fixture for AlphaStone testing.
     */
    @BeforeEach
    public void setUp() {
        game = new StandardHotStoneGame();
    }
    @Test
    public void shouldHaveUnoDosTresCardsInitially() {
        // Given a game, Findus has 3 cards in hand
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
    public void shouldHave2_2_2AttributesForDos() {
        //Checks that card dos has 2atk, manacost and health
        Card dos = game.getCardInHand(Player.FINDUS, 1);
        assertThat(dos.getAttack(), is(2));
        assertThat(dos.getHealth(), is(2));
        assertThat(dos.getManaCost(), is(2));
    }

    @Test
    public void shouldBeFindusTurnFirst() {
        //Findus is starting
        assertThat(game.getPlayerInTurn(), is(Player.FINDUS));
    }

    @Test
    public void shouldBePeddersenAfterFindus() {
        game.endTurn();
        //After Findus ends his turn Peddersen is next
        assertThat(game.getPlayerInTurn(), is(Player.PEDDERSEN));
        //Then Findus again
        game.endTurn();
        assertThat(game.getPlayerInTurn(), is(Player.FINDUS));
    }

    @Test
    public void shouldBeAllowedToPlayUno() {
        //Gets card uno and uses play methode to check if status is OK
        Card uno = game.getCardInHand(Player.FINDUS, 2);
        assertThat(game.playCard(Player.FINDUS, uno), is(Status.OK));
        //Card uno is placed at index 0 on field
        assertThat(game.getCardInField(Player.FINDUS, 0), is(uno));
        //The card is removed from the hand
        assertThat(game.getHandSize(Player.FINDUS), is(2));
    }

    @Test
    public void shouldLose2ManaWhenDosIsPlayed() {
        Card dos = game.getCardInHand(Player.FINDUS, 1);
        assertThat(game.getHero(Player.FINDUS).getMana(), is(3));
        game.playCard(Player.FINDUS, dos);
        assertThat(game.getHero(Player.FINDUS).getMana(), is(1));
    }

    @Test
    public void shouldPeddersonStillHave3CardsWhenFindusPlaysACard() {
        // Checks the number of cards in hand for both players
        assertThat(game.getHandSize(Player.FINDUS), is(3));
        assertThat(game.getHandSize(Player.PEDDERSEN), is(3));
        // Findus plays card dos
        Card dos = game.getCardInHand(Player.FINDUS, 1);
        game.playCard(Player.FINDUS, dos);
        // Checks if Findus has one less card and Peddersen has the same
        assertThat(game.getHandSize(Player.FINDUS), is(2));
        assertThat(game.getHandSize(Player.PEDDERSEN), is(3));
    }

    @Test
    public void cardsAreRemovedWhenDrawn() {
        //Should be 4 card because we already drew 3 cards of the 7card deck
        assertThat(game.getDeckSize(Player.FINDUS), is(4));
    }

    @Test
    public void shouldIncreaseTurnNumberAfterEndTurn() {
        assertThat(game.getTurnNumber(), is(0));
        game.endTurn();
        assertThat(game.getTurnNumber(), is(1));
    }

    @Test
    public void shoulIncreaseManaBy3EachRound() {
        // Checks if hero starts with 3 mana
        assertThat(game.getHero(Player.FINDUS).getMana(), is(3));
        // gets how much mana is left after players turn
        int manaBeforeEndTurn = game.getHero(Player.FINDUS).getMana();
        // the turn ends
        game.endTurn();
        // should be mana amount before ended turn + 3
        assertThat(game.getHero(Player.FINDUS).getMana(), is(manaBeforeEndTurn + 3));
    }

    @Test
    public void shouldUse2ManaWhenPowerIsUsed() {
        // Mana should start with 3 as each player always starts with 3
        assertThat(game.getHero(Player.PEDDERSEN).getMana(), is(3));
        // Pedersen uses his power
        game.usePower(Player.PEDDERSEN);
        assertThat(game.getHero(Player.PEDDERSEN).getMana(), is(1));
    }

    @Test
    public void shouldOnlyBeAbleToUsePowerOnceARound() {
        assertThat(game.usePower(Player.FINDUS), is(Status.OK));
        //It's not allowed to use power twice
        assertThat(game.usePower(Player.FINDUS), is(Status.POWER_USE_NOT_ALLOWED_TWICE_PR_ROUND));
    }

    @Test
    public void shouldHeroBeActiveAfterEachRound() {
        Heroimpl hero = (Heroimpl) game.getHero(Player.FINDUS);
        assertThat(hero.isActive, is(true));
        game.usePower(Player.FINDUS);
        assertThat(hero.isActive, is(false));
        game.endTurn();
        //After the turn has ended the hero is allowed to use power again
        assertThat(hero.isActive, is(true));
    }

    @Test
    public void shouldNotBeAbleToUseCardIfInsufficientMana() {
        //Gets card at index 0
        Card card = game.getCardInHand(Player.FINDUS, 0);
        //Plays the card at index 0 that cost 3 mana
        game.playCard(Player.FINDUS, card);
        //Mana is now 0
        assertThat(game.getHero(Player.FINDUS).getMana(), is(0));
        Card card1 = game.getCardInHand(Player.FINDUS, 0);
        //Card can not be used because of insufficient mana
        assertThat(game.playCard(Player.FINDUS, card1), is(Status.NOT_ENOUGH_MANA));
    }

    @Test
    public void shouldNotBeAbleToPlayCardWhenOppositeTurn() {
        //In first turn pedderson cannot play a card
        Card card = game.getCardInHand(Player.PEDDERSEN, 0);
        assertThat(game.playCard(Player.PEDDERSEN, card), is(Status.NOT_PLAYER_IN_TURN));
        game.endTurn();
        //In second round he is allowed to play
        assertThat(game.playCard(Player.PEDDERSEN, card), is(Status.OK));
    }

    @Test
    public void shouldNotBeAllowedToUseCardsFromOpponentsHand() {
        // Gets card from Findus hand
        Card card = game.getCardInHand(Player.FINDUS, 0);
        // Peddersen should not be able to play the card from Findus hand
        assertThat(game.playCard(Player.PEDDERSEN, card), is(Status.NOT_OWNER));
    }
}