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

import java.util.ArrayList;


public class StandardHotStoneGame implements Game {
    private int turnNumber = 0; //Keeps track of how many turns has passed
    private ArrayList<CardImpl> cardsOnPeddersonsField = new ArrayList<CardImpl>();
    private ArrayList<CardImpl> cardsInFindusHand = new ArrayList<CardImpl>();
    private ArrayList<CardImpl> cardsInPeddersonsHand = new ArrayList<CardImpl>();
    private ArrayList<CardImpl> cardsOnFindusField = new ArrayList<CardImpl>();
    private ArrayList<CardImpl> findusDeck = new ArrayList<>();
    private ArrayList<CardImpl> peddersonsDeck = new ArrayList<>();

    //Creates heros for findus and pedderson
    HeroImpl heroFindus = new HeroImpl(0, 3, GameConstants.HERO_MAX_HEALTH, true, Player.FINDUS, "Baby");
    HeroImpl heroPedderson = new HeroImpl(0, 3, GameConstants.HERO_MAX_HEALTH, true, Player.PEDDERSEN, "Baby");


    public StandardHotStoneGame() {

        //Add 7 cards to findus hand
        findusDeck.add(0, new CardImpl("Uno", 1, 1, 1, false, Player.FINDUS));
        findusDeck.add(1, new CardImpl("Dos", 2, 2, 2, false, Player.FINDUS));
        findusDeck.add(2, new CardImpl("Tres", 3, 3, 3, false, Player.FINDUS));
        findusDeck.add(3, new CardImpl("Cuatro", 2, 3, 1, false, Player.FINDUS));
        findusDeck.add(4, new CardImpl("Cinco", 3, 5, 1, false, Player.FINDUS));
        findusDeck.add(5, new CardImpl("Seis", 2, 1, 3, false, Player.FINDUS));
        findusDeck.add(6, new CardImpl("Siete", 3, 2, 4, false, Player.FINDUS));

        //Add 7 cards to pedderson hand
        peddersonsDeck.add(0, new CardImpl("Uno", 1, 1, 1, false, Player.PEDDERSEN));
        peddersonsDeck.add(1, new CardImpl("Dos", 2, 2, 2, false, Player.PEDDERSEN));
        peddersonsDeck.add(2, new CardImpl("Tres", 3, 3, 3, false, Player.PEDDERSEN));
        peddersonsDeck.add(3, new CardImpl("Cuatro", 2, 3, 1, false, Player.PEDDERSEN));
        peddersonsDeck.add(4, new CardImpl("Cinco", 3, 5, 1, false, Player.PEDDERSEN));
        peddersonsDeck.add(5, new CardImpl("Seis", 2, 1, 3, false, Player.PEDDERSEN));
        peddersonsDeck.add(6, new CardImpl("Siete", 3, 2, 4, false, Player.PEDDERSEN));

        //Deals 3 cards to pedderson and Findus
        for (int i = 1; i <= 3; i++) {
            drawCard(Player.FINDUS, findusDeck);
            drawCard(Player.PEDDERSEN, peddersonsDeck);
        }
    }

    @Override
    public Player getPlayerInTurn() {
        if (turnNumber % 2 == 0) {
            return Player.FINDUS;
        }
        return Player.PEDDERSEN;
    }

    @Override
    public Hero getHero(Player who) {
        return who == Player.FINDUS ? heroFindus : heroPedderson;
    }

    @Override
    public Player getWinner() {
        if (turnNumber >= 8) return Player.FINDUS;
        return null;
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
        if (who == Player.FINDUS) {
            return cardsInFindusHand;
        }
        return cardsInPeddersonsHand;
    }

    public Iterable<? extends Card> getDeck(Player who) {
        if (who == Player.FINDUS) {
            return findusDeck;
        }
        return peddersonsDeck;
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
        if (who == Player.FINDUS) {
            return cardsOnFindusField;
        }
        return cardsOnPeddersonsField;
    }

    @Override
    public int getFieldSize(Player who) {
        if (who == Player.FINDUS) {
            return cardsOnFindusField.size();
        }
        return cardsOnPeddersonsField.size();
    }

    @Override
    public void endTurn() {
        HeroImpl hero = (HeroImpl) getHero(getPlayerInTurn());
        // makes the hero active again
        hero.setActiveTrue();
        // Sets mana to 3 each round
        hero.setMana(3);
        for (Card c : getField(getPlayerInTurn())) {
            CardImpl cCast = (CardImpl) c;
            cCast.setActiveTrue();
        }
        turnNumber++;
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
        // gets the players fields
        ArrayList playerAttackingField = (ArrayList) getField(playerAttacking);
        ArrayList playerDefendingField = (ArrayList) getField(Utility.computeOpponent(playerAttacking));
        // gets damage of the 2 cards minions
        int attackerDamage = attackingCard.getAttack();
        int defenderDamage = defendingCard.getAttack();
        // Cast the cards to a Cardimp
        CardImpl defendingCardCast = (CardImpl) defendingCard;
        CardImpl attackingCardCast = (CardImpl) attackingCard;
        //Not able to attack with opponents card
        if (playerAttacking != attackingCard.getOwner()) return Status.NOT_OWNER;
        //Only able to play when it's your turn
        if (getPlayerInTurn() != playerAttacking) return Status.NOT_PLAYER_IN_TURN;
        //If The minion is not active an error status is returned
        if (!attackingCard.isActive()) return Status.ATTACK_NOT_ALLOWED_FOR_NON_ACTIVE_MINION;
        //Can not attack own minions
        if (defendingCard.getOwner() == attackingCard.getOwner()) return Status.ATTACK_NOT_ALLOWED_ON_OWN_MINION;
        // Subtract health from defending and attacking cards
        defendingCardCast.changeHealth(-attackerDamage);
        attackingCardCast.changeHealth(-defenderDamage);
        //If cards/minions health is 0 or below it will be removed from the field
        if (defendingCardCast.getHealth() <= 0) playerDefendingField.remove(defendingCardCast);
        if (attackingCardCast.getHealth() <= 0) playerAttackingField.remove(attackingCardCast);
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
            //Power is on cooldown untill next round
            hero.setActiveFalse();
            // Hero attacks and deals damage to other players Hero. ChangeHealth should be - when subtracting
            ((HeroImpl) getHero(Utility.computeOpponent(who))).changeHealth(((HeroImpl) getHero(who)).getDamage());
            return Status.OK;
        }
        return Status.POWER_USE_NOT_ALLOWED_TWICE_PR_ROUND;
    }

    public void drawCard(Player who, ArrayList deckName) {
        //Cards drawn always added to index 0
        ArrayList getHand = (ArrayList) getHand(who);
        getHand.add(0, deckName.get(0));
        //Cards is removed from deck at index 0
        deckName.remove(deckName.get(0));
    }
}