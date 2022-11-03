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
import hotstone.variants.AbstractFactory.AlphaStoneFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Template for your own ongoing TDD process.
 * Fill it out until you have covered all
 * requirements for the alpha stone game.
 */
public class TestAlphaStone {
    private MutableGame game;
    private StoneFactory stoneFactory;

    /**
     * Fixture for AlphaStone testing.
     */
    /* setMana3 is default for AlphaStone */
    @BeforeEach
    public void setUp() {
        game = new StandardHotStoneGame(new AlphaStoneFactory());
    }

    public void testPlayCard(Player who, int index) {
        Card chosenCard = game.getCardInHand(who, index);
        game.playCard(who, chosenCard);
    }

    public void testPlayOneCardEach(int indexFindus, int indexPedderson) {
        testPlayCard(Player.FINDUS, indexFindus);
        game.endTurn();
        testPlayCard(Player.PEDDERSEN, indexPedderson);
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
        testPlayCard(Player.FINDUS, 1);
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
        assertThat(game.getTurnNumber(), is(1));
        game.endTurn();
        assertThat(game.getTurnNumber(), is(2));
    }

    @Test
    public void shoulSetManaTo3EachRound() {
        // Checks if hero starts with 3 mana
        assertThat(game.getHero(Player.FINDUS).getMana(), is(3));
        // the turn ends
        testPlayCard(Player.FINDUS, 1);
        game.endTurn();
        // should be mana amount before ended turn + 3
        assertThat(game.getHero(Player.FINDUS).getMana(), is(3));
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
        HeroImpl hero = (HeroImpl) game.getHero(Player.FINDUS);
        assertThat(hero.isActive(), is(true));
        game.usePower(Player.FINDUS);
        assertThat(hero.isActive(), is(false));
        game.endTurn();
        //After the turn has ended the hero is allowed to use power again
        assertThat(hero.isActive(), is(true));
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

    @Test
    public void shouldFirstCardBeQuatroInRoundTwo() {
        // ends turn to  make it round two
        game.endTurn();
        // The card at index 0 should be quatro
        assertThat(game.getCardInHand(Player.PEDDERSEN, 0).getName(), is("Cuatro"));
    }

    @Test
    public void shouldFindusWinsAfter8Rounds() {
        for (int i = 0; i < 8; i++) {
            game.endTurn();
        }
        assertThat(game.getWinner(), is(Player.FINDUS));
    }

    @Test
    public void shouldCardBeInactiveWhenDrawn() {
        assertThat(game.getCardInHand(Player.FINDUS, 0).isActive(), is(false));
    }

    @Test
    public void shouldBeActiveAfter1RoundOnTheField() {
        //Findus Plays a card to the field
        Card tres = game.getCardInHand(Player.FINDUS, 0);
        game.playCard(Player.FINDUS, tres);
        game.endTurn();
        //The state of the card should be active next round
        assertThat(game.getCardInField(Player.FINDUS, 0).isActive(), is(true));
    }

    @Test
    public void shouldBe2CardsOnTheFieldAfter2CardsAreDrawn() {
        //Findus plays 2 cards and the size of the field is 2
        //Note: needs to be enough mana for the two cards
        testPlayCard(Player.FINDUS, 2);
        testPlayCard(Player.FINDUS, 1);
        assertThat(game.getFieldSize(Player.FINDUS), is(2));
    }

    @Test
    public void shouldUsePowerAndDamageOpponent0() {
        // get health of opponent
        int opponentHealth = game.getHero(Player.PEDDERSEN).getHealth();
        // findus uses power
        game.usePower(Player.FINDUS);
        // gets health of opponent after the use of power
        int opponentHealthAfterPower = game.getHero(Player.PEDDERSEN).getHealth();
        // The health should be the same as power damage is 0
        assertThat(opponentHealth, is(opponentHealthAfterPower));
    }

    @Test
    public void shouldMinionDealDamageToOpponentsCard() {
        // get card at index 0 from findus hand. Get the damage and play it.
        // It needs to be played so it can attack in the next turn
        Card findusCard = game.getCardInHand(Player.FINDUS, 0);
        int findusCardDamage = findusCard.getAttack();
        game.playCard(Player.FINDUS, findusCard);
        // Findus ends turn
        game.endTurn();

        // Pedderson needs to play a card so findus can attack it
        Card peddernsCard = game.getCardInHand(Player.PEDDERSEN, 0);
        game.playCard(Player.PEDDERSEN, peddernsCard);
        // Peddersen ends turn
        game.endTurn();

        // Gets peddersons cards health
        int HpBeforeAttack = peddernsCard.getHealth();
        // findus attacks
        game.attackCard(Player.FINDUS, findusCard, peddernsCard);
        // checks the damage is subtracted
        int HpAfterAttack = peddernsCard.getHealth();
        assertThat(HpBeforeAttack - findusCardDamage, is(HpAfterAttack));
    }

    @Test
    public void shouldNotBePossibleToAttackWithInactiveMinion() {
        //Set a game up with a card in each field
        testPlayOneCardEach(0, 0);
        Card findusCardFromField = game.getCardInField(Player.FINDUS, 0);
        Card peddersonCardFromField = game.getCardInField(Player.PEDDERSEN, 0);
        assertThat(game.attackCard(Player.PEDDERSEN, peddersonCardFromField, findusCardFromField), is(Status.ATTACK_NOT_ALLOWED_FOR_NON_ACTIVE_MINION));
    }

    @Test
    public void shouldNotBeAbleToAttackOwnMinion() {
        //Set a game up with 2 cards in findus field
        testPlayCard(Player.FINDUS, 2);
        testPlayCard(Player.FINDUS, 1);
        Card findusFirstCardFromField = game.getCardInField(Player.FINDUS, 0);
        Card findusSecondCardFromField = game.getCardInField(Player.FINDUS, 1);
        game.endTurn();
        game.endTurn();
        assertThat(game.attackCard(Player.FINDUS, findusFirstCardFromField, findusSecondCardFromField), is(Status.ATTACK_NOT_ALLOWED_ON_OWN_MINION));
    }

    @Test
    public void shouldOnlyBeAbleToAttakcWhenItsYourTurn() {
        //Set a game up with a card in each field
        testPlayOneCardEach(0, 0);
        Card findusCardFromField = game.getCardInField(Player.FINDUS, 0);
        Card peddersonCardFromField = game.getCardInField(Player.PEDDERSEN, 0);
        game.endTurn();
        assertThat(game.attackCard(Player.PEDDERSEN, peddersonCardFromField, findusCardFromField), is(Status.NOT_PLAYER_IN_TURN));
    }

    @Test
    public void shouldRemoveCardFromFieldWhenHealthIs0OrBelow() {
        //Set a game up with a card in each field
        testPlayOneCardEach(0, 0);
        Card findusCardFromField = game.getCardInField(Player.FINDUS, 0);
        Card peddersonCardFromField = game.getCardInField(Player.PEDDERSEN, 0);
        game.endTurn();
        //Findus attacks Peddersons card and it dies
        game.attackCard(Player.FINDUS, findusCardFromField, peddersonCardFromField);
        //Minions should be romoved from peddersonsfield
        assertThat(game.getFieldSize(Player.PEDDERSEN), is(0));
    }

    @Test
    public void shouldHeroLoseHealthWhenMinionAttacks() {
        // Gets findus card and its attack damage
        Card findusCard = game.getCardInHand(Player.FINDUS, 0);
        int findusCardDamage = findusCard.getAttack();
        // Plays card and waits till its active again
        game.playCard(Player.FINDUS, findusCard);
        game.endTurn();
        game.endTurn();
        // Gets health before attack - Attacks - and then compares the health after attack
        int HpBeforeAttack = game.getHero(Player.PEDDERSEN).getHealth();
        game.attackHero(Player.FINDUS, findusCard);
        int HpAfterAttack = game.getHero(Player.PEDDERSEN).getHealth();
        assertThat(HpBeforeAttack - findusCardDamage, is(HpAfterAttack));
    }

    @Test
    public void shouldMinionCannotAttackWhenItsNotActive() {
        //plays card
        testPlayCard(Player.FINDUS,0);
        // Gets findus card
        Card findusCard = game.getCardInField(Player.FINDUS, 0);
        // Attacks
        assertThat(game.attackHero(Player.FINDUS, findusCard), is(Status.ATTACK_NOT_ALLOWED_FOR_NON_ACTIVE_MINION));
    }

    @Test
    public void shouldMinionNotAttackWhenNotInTurn() {
        // Gets findus card
        Card findusCard = game.getCardInHand(Player.FINDUS, 0);
        // Plays card
        game.playCard(Player.FINDUS, findusCard);
        // ends turn
        game.endTurn();
        // Tries to attack when not in turn
        assertThat(game.attackHero(Player.FINDUS, findusCard), is(Status.NOT_PLAYER_IN_TURN));

        // Also checks for Pedderson

        // Gets Peddersons card
        Card peddersonCard = game.getCardInHand(Player.PEDDERSEN, 0);
        // Plays card
        game.playCard(Player.PEDDERSEN, peddersonCard);
        game.endTurn();
        assertThat(game.attackHero(Player.PEDDERSEN, peddersonCard), is(Status.NOT_PLAYER_IN_TURN));
    }

    @Test
    public void shouldMinionLooseHealthWhenAttacking() {
        //Set up game with 1 card each
        testPlayOneCardEach(0,1);
        game.endTurn();
        Card cardFindus = game.getCardInField(Player.FINDUS,0);
        Card cardPeddersen = game.getCardInField(Player.PEDDERSEN,0);
        // Gets peddersons card
        int healthInBeginning = cardPeddersen.getHealth();
        int attack = cardFindus.getAttack();
        // Tries to attack
        game.attackCard(Player.FINDUS, cardFindus, cardPeddersen);
        // The attacking card should lose the amount health which pedersons card has in attack
        assertThat(cardFindus.getHealth(), is(healthInBeginning - attack));
    }

    @Test
    public void shouldNotBeAbleToAttackWithOpponentsCard() {
        //Set up game with 1 card each
        testPlayOneCardEach(0,0);
        game.endTurn();
        Card cardFindus = game.getCardInField(Player.FINDUS,0);
        Card cardPeddersen = game.getCardInField(Player.PEDDERSEN,0);
        //Not able to attack with opponents cards
        assertThat(game.attackCard(Player.FINDUS, cardPeddersen, cardFindus), is(Status.NOT_OWNER));
        assertThat(game.attackCard(Player.FINDUS, cardPeddersen, cardPeddersen), is(Status.NOT_OWNER));
    }

    @Test
    public void shouldReturnUnoDosTresInBeginning() {
        // gets the hand of findus
        ArrayList hand = (ArrayList) game.getHand(Player.FINDUS);
        // gets all three cards in the hand
        CardImpl uno = (CardImpl) game.getCardInHand(Player.FINDUS, 2);
        CardImpl dos = (CardImpl) game.getCardInHand(Player.FINDUS, 1);
        CardImpl tres = (CardImpl) game.getCardInHand(Player.FINDUS, 0);
        // checks all three cards are in the hand
        assertThat(hand.contains(uno), is(true));
        assertThat(hand.contains(dos), is(true));
        assertThat(hand.contains(tres), is(true));
    }

    @Test
    public void shouldMinionOnlyBeAbleToAttackOncePerRound() {
        //Plays a card eash
        testPlayOneCardEach(2,0);
        game.endTurn();
        //Get attacking and defending card and plays them
        Card attackingCard = game.getCardInField(Player.FINDUS, 0);
        Card defendingCard = game.getCardInField(Player.PEDDERSEN, 0);
        //Makes sure that card is set to inactive after attacking
        assertThat(game.attackCard(Player.FINDUS, attackingCard, defendingCard),is(Status.OK));
        assertThat(game.attackCard(Player.FINDUS,attackingCard, defendingCard), is(Status.ATTACK_NOT_ALLOWED_FOR_NON_ACTIVE_MINION));
    }

    @Test
    public void shouldBeSomethingInTheField() {
    assertThat(game.getField(Player.FINDUS).iterator().hasNext(),is(false));
    testPlayCard(Player.FINDUS,0);
    assertThat(game.getField(Player.FINDUS).iterator().hasNext(),is(true));
    }

    @Test
    public void shouldBeSomethingInTheHand() {
        //There is always 3 cards in the hand in the beginning of the game
        assertThat(game.getHand(Player.FINDUS).iterator().hasNext(), is(true));
        StandardHotStoneGame game1 = (StandardHotStoneGame) game;
        game1.setManaHero((MutableHero) game1.getHero(Player.FINDUS),100);
        testPlayCard(Player.FINDUS, 2);
        testPlayCard(Player.FINDUS, 1);
        testPlayCard(Player.FINDUS, 0);
        //There is 0 cards in the hand
        assertThat(game.getHand(Player.FINDUS).iterator().hasNext(), is(false));
    }

    @Test
    public void shouldDamageHeroIfDeckIsEmpty(){
        int healthInTheBeginning = game.getHero(Player.FINDUS).getHealth();
        // draws 5 cards from deck. The fifth card makes the hero lose two health because it doesnt exist
        game.drawCard(Player.FINDUS);
        game.drawCard(Player.FINDUS);
        game.drawCard(Player.FINDUS);
        game.drawCard(Player.FINDUS);
        game.drawCard(Player.FINDUS);
        assertThat(game.getHero(Player.FINDUS).getHealth(), is(healthInTheBeginning-2));
    }

    @Test
    public void shouldPlayerFindusBeTypeBaby(){
        assertThat(game.getHero(Player.FINDUS).getType(),is(GameConstants.BABY_HERO_TYPE));
    }
}
