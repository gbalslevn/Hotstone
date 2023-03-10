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

import hotstone.Observer.GameObserver;
import hotstone.Observer.ObserverHandler;
import hotstone.framework.*;
import hotstone.framework.Strategies.*;

import java.util.ArrayList;
import java.util.HashMap;

public class StandardHotStoneGame implements Game, MutableGame {
    private int turnNumber = 1; //Keeps track of how many turns has passed

    private ArrayList<Card>[] field;
    private ArrayList<Card>[] hand;
    private HashMap<Player, ArrayList<Card>> deck;
    private HashMap<Player, MutableHero> hero;

    // creates the manaStategy
    private ManaStrategy manaStrategy;
    private WinnerStategy winnerStategy;
    private TypeStrategy typeStrategy;
    private PowerStrategy powerStrategy;
    private DeckStrategy deckStrategy;
    private CardEffectStrategy cardEffectStrategy;

    private ObserverHandler observerHandler;




    public StandardHotStoneGame(StoneFactory stoneFactory) {

        deckStrategy = stoneFactory.createDeckStrategy();
        typeStrategy = stoneFactory.createTypeStrategy();
        manaStrategy = stoneFactory.createManaStrategy();
        powerStrategy = stoneFactory.createPowerStrategy();
        winnerStategy = stoneFactory.createWinnerStrategy();
        cardEffectStrategy = stoneFactory.createEffectStrategy();

        observerHandler = new ObserverHandler();
        initializeFieldVaraiables();

        setGameState(manaStrategy, typeStrategy, deckStrategy);
    }

    @Override
    public void addObserver(GameObserver observer) {
        observerHandler.addObserver(observer);
    }

    private void setGameState(ManaStrategy manaStrategy, TypeStrategy typeStrategy, DeckStrategy deckStrategy) {
        // Initialise mana for the given version
        setManaHero(hero.get(Player.PEDDERSEN),manaStrategy.calculateMana(this));
        setManaHero(hero.get(Player.FINDUS),manaStrategy.calculateMana(this));
        deckStrategy.createDeck(this);
        dealsInitial3Cards();
    }

    private void initializeFieldVaraiables() {
        // Creates two fields
        field = new ArrayList[2];
        field[0] = new ArrayList<>();
        field[1] = new ArrayList<>();
        // Creates two hands
        hand = new ArrayList[2];
        hand[0] = new ArrayList<>();
        hand[1] = new ArrayList<>();
        //Creates two decks
        deck = new HashMap<>();
        deck.put(Player.FINDUS, new ArrayList<>());
        deck.put(Player.PEDDERSEN, new ArrayList<>());

        //Creates two heroes
        hero = new HashMap<>();
        hero.put(Player.FINDUS,typeStrategy.chooseType(Player.FINDUS));
        hero.put(Player.PEDDERSEN,typeStrategy.chooseType(Player.PEDDERSEN));
    }

    private void dealsInitial3Cards() {
        //Deals 3 cards to pedderson and Findus
        for (int i = 1; i <= 3; i++) {
            drawCard(Player.FINDUS);
            drawCard(Player.PEDDERSEN);
        }
    }

    @Override
    public Player getPlayerInTurn() {
        return (turnNumber % 2 == 1) ? Player.FINDUS : Player.PEDDERSEN;
    }

    @Override
    public Hero getHero(Player who) {
        return hero.get(who);
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
        return deck.get(who).size();
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
        return deck.get(who);
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
        MutableHero inTurnHero = hero.get(getPlayerInTurn());
        // makes the hero and cards active again
        setActiveTrueHero(inTurnHero);
        setCardsOnFieldActive();

        turnNumber++;

        drawCard(getPlayerInTurn());
        setHeroMana(inTurnHero);

        observerHandler.notifyTurnChangeTo(getPlayerInTurn());

        Player winner = getWinner();
        if (winner != null){
            observerHandler.notifyGameWon(winner);
        }

    }

    //Calculate and set the mana of the hero
    private void setHeroMana(MutableHero hero) {
        // Finds out how much mana needs to be given to the Hero
        int newMana = manaStrategy.calculateMana(this);
        setManaHero(hero,newMana);
    }

    //Sets all the cards on the field to active
    private void setCardsOnFieldActive() {
        for (Card c : getField(getPlayerInTurn())) {
            setActiveTrueCard((MutableCard) c);
        }
    }

    @Override
    public Status playCard(Player who, Card card) {
        Status status = isPossibleToPlayCard(who, card);
        if (status != Status.OK) return status;

        //Adds Card to field and remove from hand
        field[card.getOwner().ordinal()].add(card);
        hand[card.getOwner().ordinal()].remove(card);

        // Card effect is used
        cardEffectStrategy.useEffect(this, card);

        // Hero uses mana when playing the card
        changeManaHero(hero.get(who),-card.getManaCost());
        // Notifies observer
        observerHandler.notifyPlayCard(who, card);
        return Status.OK;
    }

    // Checks if player is owner, in turn and has enough mana
    private Status isPossibleToPlayCard(Player who, Card card) {
        Status status = isOwningAndInTurn(who, card);
        if (status != Status.OK) return status;
        Hero hero = getHero(who);
        boolean isEnoughMana = hero.getMana() >= card.getManaCost();
        if (!isEnoughMana) return Status.NOT_ENOUGH_MANA;

        return Status.OK;
    }

    @Override
    public Status attackCard(Player playerAttacking, Card attackingCard, Card defendingCard) {
        Status status = isPossibleToAttack(playerAttacking, attackingCard, defendingCard);
        if (status != Status.OK) return status;

        executeAttack((MutableCard) attackingCard, (MutableCard) defendingCard);
        Stats.changeDamageOutput(attackingCard.getOwner(), attackingCard.getAttack(), getTurnNumber());

        observerHandler.notifyAttackCard(playerAttacking, attackingCard, defendingCard);
        return Status.OK;
    }

    //Damage minions and makes attacker inactive then removes minion if dead
    private void executeAttack(MutableCard attackingCard, MutableCard defendingCard) {
        // Subtract health from defending and attacking cards
        changeHealthCard(defendingCard,-attackingCard.getAttack());
        changeHealthCard(attackingCard,-defendingCard.getAttack());

        removeCardIfDead(attackingCard);
        removeCardIfDead(defendingCard);

        //Sets minion to inactive after attacking
        setActiveFalseCard(attackingCard);

        observerHandler.notifyCardUpdate(attackingCard);
        observerHandler.notifyCardUpdate(defendingCard);

    }

    // If minions health is 0 its removed
    private void removeCardIfDead(Card card) {
        if (card.getHealth() <= 0) {
            field[card.getOwner().ordinal()].remove(card);
            observerHandler.notifyCardRemove(card.getOwner(), card);
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
        MutableHero heroDamaged = hero.get(Utility.computeOpponent(playerAttacking));
        changeHealthHero(heroDamaged,-cardDamage);
        setActiveFalseCard((MutableCard) attackingCard);
        observerHandler.notifyAttackHero(playerAttacking, attackingCard);
        observerHandler.notifyHeroUpdate(Utility.computeOpponent(playerAttacking));
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
        MutableHero heroInTurn = hero.get(who);
        Boolean isHeroActive = heroInTurn.isActive();
        if (!isHeroActive) return Status.POWER_USE_NOT_ALLOWED_TWICE_PR_ROUND;
        if(heroInTurn.getMana() <= GameConstants.HERO_POWER_COST) return Status.NOT_ENOUGH_MANA;
        executePower(heroInTurn);
        observerHandler.notifyUsePower(who);
        return Status.OK;
    }

    //Mana is used and hero is set to false, and hero power is executed
    private void executePower(MutableHero hero) {
        changeManaHero(hero,-GameConstants.HERO_POWER_COST);
        setActiveFalseHero(hero);
        observerHandler.notifyHeroUpdate(hero.getOwner());
        powerStrategy.useHeroPower(this);
    }

    @Override
    // Draws card, If the deck is 0 no card is drawn and hero loses 2 health
    public void drawCard(Player who) {
        if (deck.get(who).size() == 0) changeHealthHero(hero.get(who),-GameConstants.HERO_HEALTH_PENALTY_ON_EMPTY_DECK);
        else {
            observerHandler.notifyCardDraw(who, deck.get(who).get(0));
            addCardToHandAndRemoveFromDeck(who);
        }
    }

    //Adds card to hand and removes from deck
    private void addCardToHandAndRemoveFromDeck(Player who) {
        Card card = deck.get(who).get(0);
        //Cards drawn always added to index 0
        hand[card.getOwner().ordinal()].add(0, card);
        //Cards is removed from deck at index 0
        deck.get(who).remove(card);
    }

    public void setActiveFalseCard(MutableCard card) {
            card.setActiveFalse();
            observerHandler.notifyCardUpdate(card);
    }

    public void setActiveTrueCard(MutableCard card) {
        card.setActiveTrue();
        observerHandler.notifyCardUpdate(card);
    }


    public void changeHealthCard(MutableCard card, int healthChange) {
        card.changeHealth(healthChange);
        observerHandler.notifyCardUpdate(card);
    }
    public void changeAttackCard(MutableCard card, int attackChange) {
        card.changeAttack(attackChange);
        observerHandler.notifyCardUpdate(card);
    }

    public void setManaHero(MutableHero hero, int newMana) {
        hero.setMana(newMana);
        observerHandler.notifyHeroUpdate(hero.getOwner());
    }

    public void changeManaHero(MutableHero hero, int manaChange) {
        hero.changeMana(manaChange);
        observerHandler.notifyHeroUpdate(hero.getOwner());
    }

    public void setActiveFalseHero(MutableHero hero) {
        hero.setActiveFalse();
        observerHandler.notifyHeroUpdate(hero.getOwner());
    }

    public void setActiveTrueHero(MutableHero hero) {
        hero.setActiveTrue();
        observerHandler.notifyHeroUpdate(hero.getOwner());
    }
    public void changeHealthHero(MutableHero hero, int healthChange) {
        hero.changeHealth(healthChange);
        observerHandler.notifyHeroUpdate(hero.getOwner());
    }


}



