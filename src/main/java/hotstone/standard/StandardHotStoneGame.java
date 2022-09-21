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
import hotstone.variants.DeltaStone.DishDeck;

import java.util.ArrayList;


public class StandardHotStoneGame implements Game {
    private int turnNumber = 1; //Keeps track of how many turns has passed
    private ArrayList<CardImpl> cardsOnPeddersonsField = new ArrayList<CardImpl>();
    private ArrayList<CardImpl> cardsInFindusHand = new ArrayList<CardImpl>();
    private ArrayList<CardImpl> cardsInPeddersonsHand = new ArrayList<CardImpl>();
    private ArrayList<CardImpl> cardsOnFindusField = new ArrayList<CardImpl>();
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
    public CardImpl getCardInHand(Player who, int indexInHand) {
        return who == Player.FINDUS ? cardsInFindusHand.get(indexInHand) : cardsInPeddersonsHand.get(indexInHand);
    }


    @Override
    public Iterable<? extends Card> getHand(Player who) {
        return who == Player.FINDUS ? cardsInFindusHand : cardsInPeddersonsHand;
    }

    public Iterable<? extends Card> getDeck(Player who) {
        return who == Player.FINDUS ? findusDeck : peddersonsDeck;
    }

    @Override
    public int getHandSize(Player who) {
        return who == Player.FINDUS ? cardsInFindusHand.size() : cardsInPeddersonsHand.size();
    }

    @Override
    public Card getCardInField(Player who, int indexInField) {
        return who == Player.FINDUS ? cardsOnFindusField.get(indexInField) : cardsOnPeddersonsField.get(indexInField);
    }

    @Override
    public Iterable<? extends Card> getField(Player who) {
        return who == Player.FINDUS ? cardsOnFindusField : cardsOnPeddersonsField;
    }

    @Override
    public int getFieldSize(Player who) {
        return who == Player.FINDUS ? cardsOnFindusField.size() : cardsOnPeddersonsField.size();
    }

    @Override
    public void endTurn() {
        HeroImpl hero = (HeroImpl) getHero(getPlayerInTurn());
        // makes the hero active again
        hero.setActiveTrue();
        for (Card c : getField(getPlayerInTurn())) {
            CardImpl cCast = (CardImpl) c;
            cCast.setActiveTrue();
        }
        //Adds one to turnnumber
        turnNumber++;
        // 'this' gives itself as a parameter
        // Sets mana to for the next round
        int newMana = manaStrategy.calculateMana(this);
        hero.setMana(newMana);
    }

    //Finds index of the given card from parameter.
    @Override
    public Status playCard(Player who, Card card) {
        //Makes sure the player owns the card
        if (who != card.getOwner()) return Status.NOT_OWNER;
        //Makes sure it's the player in turn
        if (who != getPlayerInTurn()) return Status.NOT_PLAYER_IN_TURN;
        //Mana cost of card
        int manaCost = card.getManaCost();
        HeroImpl hero = (HeroImpl) getHero(who);
        //Makes sure player has enough mana
        if (hero.getMana() < manaCost) return Status.NOT_ENOUGH_MANA;
        if (who == Player.FINDUS) {
            //Adds card to field
            cardsOnFindusField.add((CardImpl) card);
            //remove card from hand
            cardsInFindusHand.remove(card);
        } else {
            //Adds card to field
            cardsOnPeddersonsField.add((CardImpl) card);
            //remove card from hand
            cardsInPeddersonsHand.remove(card);
        }
        // Hero uses mana when playing the card
        hero.changeMana(-manaCost);
        return Status.OK;
    }

    @Override
    public Status attackCard(Player playerAttacking, Card attackingCard, Card defendingCard) {
        // gets damage of the 2 cards minions
        int attackerDamage = attackingCard.getAttack();
        int defenderDamage = defendingCard.getAttack();
        // Cast the cards to a Cardimp
        CardImpl defendingCardCast = (CardImpl) defendingCard;
        CardImpl attackingCardCast = (CardImpl) attackingCard;
        //Only able to play when it's your turn
        if (getPlayerInTurn() != playerAttacking) return Status.NOT_PLAYER_IN_TURN;
        //Not able to attack with opponents card
        if (playerAttacking != attackingCard.getOwner()) return Status.NOT_OWNER;
        if (!attackingCard.isActive()) return Status.ATTACK_NOT_ALLOWED_FOR_NON_ACTIVE_MINION;
        //Can not attack own minions
        if (defendingCard.getOwner() == attackingCard.getOwner()) return Status.ATTACK_NOT_ALLOWED_ON_OWN_MINION;
        // Subtract health from defending and attacking cards
        defendingCardCast.changeHealth(-attackerDamage);
        attackingCardCast.changeHealth(-defenderDamage);
        //If cards/minions health is 0 or below it will be removed from the field

        if (defendingCardCast.getHealth() <= 0) {
            if (playerAttacking == Player.FINDUS){
            int indexCardToRemove = cardsOnPeddersonsField.indexOf(defendingCard);
            Card cardToRemove = getCardInField(Player.PEDDERSEN,indexCardToRemove);
            cardsOnPeddersonsField.remove(cardToRemove);
            }else {
            int indexCardToRemove = cardsOnFindusField.indexOf(defendingCard);
            Card cardToRemove = getCardInField(Player.FINDUS,indexCardToRemove);
            cardsOnFindusField.remove(cardToRemove);
            }
        }
        if (attackingCardCast.getHealth() <= 0) {
            if (playerAttacking == Player.FINDUS){
                int indexCardToRemove = cardsOnFindusField.indexOf(attackingCard);
                Card cardToRemove = getCardInField(Player.FINDUS,indexCardToRemove);
                cardsOnFindusField.remove(cardToRemove);
            }else {
            int indexCardToRemove = cardsOnPeddersonsField.indexOf(attackingCard);
            Card cardToRemove = getCardInField(Player.PEDDERSEN,indexCardToRemove);
            cardsOnPeddersonsField.remove(cardToRemove);
            }
        }

        //Sets minion to inactive after attacking
        ((CardImpl) attackingCard).setActiveFalse();
        return Status.OK;
    }

    @Override
    public Status attackHero(Player playerAttacking, Card attackingCard) {
        //Only able to play when it's your turn
        if (getPlayerInTurn() != playerAttacking) return Status.NOT_PLAYER_IN_TURN;
        // attacks the hero with the card damage
        int cardDamage = attackingCard.getAttack();
        //Minions needs to be active.
        if (!attackingCard.isActive()) return Status.ATTACK_NOT_ALLOWED_FOR_NON_ACTIVE_MINION;
        //Damage the opponents hero
        HeroImpl heroDamaged = (HeroImpl) getHero(Utility.computeOpponent(playerAttacking));
        heroDamaged.changeHealth(-cardDamage);
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
        if (deckName.size() <= 0) {
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