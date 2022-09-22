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

import hotstone.framework.*;
import hotstone.framework.Strategies.*;

import java.util.ArrayList;

public class StandardHotStoneGame implements Game {
    private int turnNumber = 1; //Keeps track of how many turns has passed

    private ArrayList<CardImpl>[] field;
    private ArrayList<CardImpl>[] hand;
    private ArrayList<CardImpl> findusDeck = new ArrayList<>();
    private ArrayList<CardImpl> peddersonsDeck = new ArrayList<>();


    //Creates heros for findus and pedderson
    HeroImpl heroFindus = new HeroImpl(0, 3, GameConstants.HERO_MAX_HEALTH, true,
            Player.FINDUS, "Baby", "Cute");
    HeroImpl heroPedderson = new HeroImpl(0, 3, GameConstants.HERO_MAX_HEALTH, true,
            Player.PEDDERSEN, "Baby", "Cute");

    // creates the manaStategy
    private ManaStrategy manaStrategy;
    private WinnerStategy winnerStategy;
    private TypeStrategy typeStrategy;
    private PowerStrategy powerStrategy;
    private DeckStrategy deckStrategy;


    public StandardHotStoneGame(ManaStrategy manaStrategy,
                                WinnerStategy winnerStategy,
                                TypeStrategy typeStrategy,
                                PowerStrategy powerStrategy,
                                DeckStrategy deckStrategy) {

        this.winnerStategy = winnerStategy;
        this.manaStrategy = manaStrategy;
        this.typeStrategy = typeStrategy;
        this.powerStrategy = powerStrategy;
        this.deckStrategy = deckStrategy;

        // Initialise mana for the given version
        heroFindus.setMana(manaStrategy.calculateMana(this));
        heroPedderson.setMana(manaStrategy.calculateMana(this));
        typeStrategy.chooseType(this);
        deckStrategy.createDeck(this);

        // Creates two fields
        field = new ArrayList[2];
        field[0] = new ArrayList<>();
        field[1] = new ArrayList<>();
        // Creates two hands
        hand = new ArrayList[2];
        hand[0] = new ArrayList<>();
        hand[1] = new ArrayList<>();

        dealsInitial3Cards();
    }

    private void dealsInitial3Cards() {
        //Deals 3 cards to pedderson and Findus
        for (int i = 1; i <= 3; i++) {
            drawCard(Player.FINDUS, findusDeck);
            drawCard(Player.PEDDERSEN, peddersonsDeck);
        }
    }

    @Override
    public Player getPlayerInTurn() {
        return (turnNumber % 2 == 1) ? Player.FINDUS : Player.PEDDERSEN;
    }

    @Override
    public Hero getHero(Player who) {
        return who == Player.FINDUS ? heroFindus : heroPedderson;
    }


    @Override
    public Player getWinner() {
        return winnerStategy.calculateWinner(this);
    }

    @Override
    public int getTurnNumber() {
        return turnNumber;
    }

    @Override
    public int getDeckSize(Player who) {
        return who == Player.FINDUS ? findusDeck.size() : peddersonsDeck.size();
    }

    @Override
    public Card getCardInHand(Player who, int indexInHand) {
        return who == Player.FINDUS ? hand[0].get(indexInHand) : hand[1].get(indexInHand);
    }


    @Override
    public Iterable<? extends Card> getHand(Player who) {
        return who == Player.FINDUS ? hand[0] : hand[1];
    }

    public Iterable<? extends Card> getDeck(Player who) {
        return who == Player.FINDUS ? findusDeck : peddersonsDeck;
    }

    @Override
    public int getHandSize(Player who) {
        return who == Player.FINDUS ? hand[0].size() : hand[1].size();
    }

    @Override
    public Card getCardInField(Player who, int indexInField) {
        return who == Player.FINDUS ? field[0].get(indexInField) : field[1].get(indexInField);
    }

    @Override
    public Iterable<? extends Card> getField(Player who) {
        return (who == Player.FINDUS) ? field[0] : field[1];
    }

    @Override
    public int getFieldSize(Player who) {
        return (who == Player.FINDUS) ? field[0].size() : field[1].size();
    }

    @Override
    public void endTurn() {
        HeroImpl hero = (HeroImpl) getHero(getPlayerInTurn());
        // makes the hero and cards active again
        hero.setActiveTrue();
        setCardsOnFieldActive();
        turnNumber++;
        setHeroMana(hero);
    }

    //Calculate and set the mana of the hero
    private void setHeroMana(HeroImpl hero) {
        // Finds out how much mana needs to be given to the Hero
        int newMana = manaStrategy.calculateMana(this);
        hero.setMana(newMana);
    }

    //Sets all the cards on the field to active
    private void setCardsOnFieldActive() {
        for (Card c : getField(getPlayerInTurn())) {
            CardImpl cCast = (CardImpl) c;
            cCast.setActiveTrue();
        }
    }

    @Override
    public Status playCard(Player who, Card card) {
        Status status = isPossibleToPlayCard(who, card);
        if (status != Status.OK) return status;

        //Adds Card to field and remove from hand
        field[card.getOwner().ordinal()].add((CardImpl) card);
        hand[card.getOwner().ordinal()].remove((CardImpl) card);

        // Hero uses mana when playing the card
        ((HeroImpl) getHero(who)).changeMana(-card.getManaCost());
        return Status.OK;
    }

    // Checks if player is owner, in turn and has enough mana
    private Status isPossibleToPlayCard(Player who, Card card) {
        Status status = isOwningAndInTurn(who, card);
        if (status != Status.OK) return status;
        HeroImpl hero = (HeroImpl) getHero(who);
        boolean isEnoughMana = hero.getMana() >= card.getManaCost();
        if (!isEnoughMana) return Status.NOT_ENOUGH_MANA;
        return Status.OK;
    }

    @Override
    public Status attackCard(Player playerAttacking, Card attackingCard, Card defendingCard) {
        Status status = isPossibleToAttack(playerAttacking, attackingCard, defendingCard);
        if (status != Status.OK) return status;

        executeAttack((CardImpl)attackingCard,(CardImpl)defendingCard);

        return Status.OK;
    }

    //Damage minions and makes attacker inactive then removes minion if dead
    private void executeAttack(CardImpl attackingCard, CardImpl defendingCard) {
        // Subtract health from defending and attacking cards
        defendingCard.changeHealth(-attackingCard.getAttack());
        attackingCard.changeHealth(-defendingCard.getAttack());

        removeCardIfDead(attackingCard);
        removeCardIfDead(defendingCard);

        //Sets minion to inactive after attacking
        attackingCard.setActiveFalse();
    }

    // If minions health is 0 its removed
    private void removeCardIfDead(Card card) {
        if (card.getHealth() <= 0) {
            field[card.getOwner().ordinal()].remove(card);
        }
    }

    //Checks that the minion is active and its not attacking own minion
    private Status isPossibleToAttack(Player playerAttacking, Card attackingCard, Card defendingCard) {
        Status status = isOwningAndInTurn(playerAttacking, attackingCard);
        if (status != Status.OK) return status;
        boolean isMinionActive = attackingCard.isActive();
        if (!isMinionActive) return Status.ATTACK_NOT_ALLOWED_FOR_NON_ACTIVE_MINION;
        boolean isCardsSameOwner = defendingCard.getOwner() == attackingCard.getOwner();
        if (isCardsSameOwner) return Status.ATTACK_NOT_ALLOWED_ON_OWN_MINION;
        return Status.OK;
    }

    // Checks if the user owns the card and is in turn
    private Status isOwningAndInTurn(Player who, Card card) {
        boolean isOwningTheCard = who == card.getOwner();
        if (!isOwningTheCard) return Status.NOT_OWNER;
        boolean isPlayerInTurn = who == getPlayerInTurn();
        if (!isPlayerInTurn) return Status.NOT_PLAYER_IN_TURN;
        return Status.OK;
    }

    @Override
    public Status attackHero(Player playerAttacking, Card attackingCard) {
        Status status = isPossibleToAttackHero(playerAttacking, attackingCard);
        if (status != Status.OK) return status;
        // attacks the hero with the card damage
        int cardDamage = attackingCard.getAttack();
        //Damage the opponents hero
        HeroImpl heroDamaged = (HeroImpl) getHero(Utility.computeOpponent(playerAttacking));
        heroDamaged.changeHealth(-cardDamage);
        return Status.OK;
    }

    // Checks if owner, in turn and minion is active. Used in attackHero()
    public Status isPossibleToAttackHero(Player who, Card attackingCard) {
        Status status = isOwningAndInTurn(who, attackingCard);
        if (status != Status.OK) return status;
        boolean isMinionActive = attackingCard.isActive();
        if (!isMinionActive) return Status.ATTACK_NOT_ALLOWED_FOR_NON_ACTIVE_MINION;
        return Status.OK;
    }

    @Override
    public Status usePower(Player who) {
        HeroImpl hero = (HeroImpl) getHero(who);
        //If hero can use power
        if (hero.isActive()) {
            // subtracts 2 mana
            hero.changeMana(-2);
            //Power is on cooldown until next round
            hero.setActiveFalse();
            // Hero uses special ability/power
            powerStrategy.useHeroPower(this);
            return Status.OK;
        }
        return Status.POWER_USE_NOT_ALLOWED_TWICE_PR_ROUND;
    }

    public void drawCard(Player who, ArrayList deckName) {
        // If the deck is 0 no card is drawn and hero loses 2 health
        if (deckName.size() == 0) {
            HeroImpl playerHero = (HeroImpl) getHero(who);
            playerHero.changeHealth(-2);
        } else {
            //Cards drawn always added to index 0
            ArrayList getHand = (ArrayList) getHand(who);
            getHand.add(0, deckName.get(0));
            //Cards is removed from deck at index 0
            deckName.remove(deckName.get(0));
        }
    }
}