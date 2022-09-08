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
import java.util.List;

public class StandardHotStoneGame implements Game {
    private int playerTurn = 0; //Keeps track of how many turns has passed
    private ArrayList<Cardimpl> cardsOnPeddersonsField = new ArrayList<Cardimpl>();
    private ArrayList<Cardimpl> cardsInFindusHand = new ArrayList<Cardimpl>();
    private ArrayList<Cardimpl> cardsInPeddersonsHand = new ArrayList<Cardimpl>();
    private ArrayList<Cardimpl> cardsOnFindusField = new ArrayList<Cardimpl>();
    private ArrayList<Cardimpl> findusDeck = new ArrayList<>();
    private ArrayList<Cardimpl> peddersonsDeck = new ArrayList<>();

    //Creates heros for findus and pedderson
    Heroimpl heroFindus = new Heroimpl(0, 3, 21, true, Player.FINDUS, "Baby");
    Heroimpl heroPedderson = new Heroimpl(0, 3, 21, true, Player.PEDDERSEN, "Baby");


    public StandardHotStoneGame() {
        //Add 7 cards to findus hand
        findusDeck.add(0, new Cardimpl("Uno", 1, 1, 1, Player.FINDUS));
        findusDeck.add(1, new Cardimpl("Dos", 2, 2, 2, Player.FINDUS));
        findusDeck.add(2, new Cardimpl("Tres", 3, 3, 3, Player.FINDUS));
        findusDeck.add(3, new Cardimpl("Cuatro", 2, 3, 1, Player.FINDUS));
        findusDeck.add(4, new Cardimpl("Cinco", 3, 5, 1, Player.FINDUS));
        findusDeck.add(5, new Cardimpl("Seis", 2, 1, 3, Player.FINDUS));
        findusDeck.add(6, new Cardimpl("Siete", 3, 2, 4, Player.FINDUS));

        //Add 7 cards to pedderson hand
        peddersonsDeck.add(0, new Cardimpl("Uno", 1, 1, 1, Player.PEDDERSEN));
        peddersonsDeck.add(1, new Cardimpl("Dos", 2, 2, 2, Player.PEDDERSEN));
        peddersonsDeck.add(2, new Cardimpl("Tres", 3, 3, 3, Player.PEDDERSEN));
        peddersonsDeck.add(3, new Cardimpl("Cuatro", 2, 3, 1, Player.PEDDERSEN));
        peddersonsDeck.add(4, new Cardimpl("Cinco", 3, 5, 1, Player.PEDDERSEN));
        peddersonsDeck.add(5, new Cardimpl("Seis", 2, 1, 3, Player.PEDDERSEN));
        peddersonsDeck.add(6, new Cardimpl("Siete", 3, 2, 4, Player.PEDDERSEN));

        //Deals 3 cards to pedderson and Finduss
        for (int i = 1; i <= 3; i++) {
            drawCard(cardsInFindusHand, findusDeck);
            drawCard(cardsInPeddersonsHand, peddersonsDeck);
        }
    }

    @Override
    public Player getPlayerInTurn() {
        if (playerTurn % 2 == 0) {
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
        return null;
    }

    @Override
    public int getTurnNumber() {
        return playerTurn;
    }

    @Override
    public int getDeckSize(Player who) {
        return who == Player.FINDUS ? findusDeck.size() : peddersonsDeck.size();
    }

    @Override
    public Cardimpl getCardInHand(Player who, int indexInHand) {
        return who == Player.FINDUS ? cardsInFindusHand.get(indexInHand) : cardsInPeddersonsHand.get(indexInHand);
    }


    @Override
    public Iterable<? extends Card> getHand(Player who) {
        if (who == Player.FINDUS) {
            return cardsInFindusHand;
        }
        return cardsInPeddersonsHand;
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
        return null;
    }

    @Override
    public int getFieldSize(Player who) {
        return 0;
    }

    @Override
    public void endTurn() {
        Heroimpl hero = (Heroimpl) getHero(getPlayerInTurn());
        // makes the hero active again
        if (!hero.isActive) hero.changeActive();
        // adds 3 mana each round
        hero.changeMana(3);
        playerTurn++;
    }

    //Finds index of the given card from parameter.
    @Override
    public Status playCard(Player who, Card card) {
        if (who == Player.FINDUS) {
            //Makes sure the player owns the card
            if (card.getOwner() != Player.FINDUS) return Status.NOT_OWNER;
            // What index the chosen card is on in the hand
            int indexOfFindusCard = cardsInFindusHand.indexOf(card);
            //Makes sure player has enough mana
            int manaCost = getCardInHand(Player.FINDUS, indexOfFindusCard).getManaCost();
            if (manaCost > heroFindus.getMana()) return Status.NOT_ENOUGH_MANA;
            //Makes sure its the player in turn
            if (getPlayerInTurn() != Player.FINDUS) return Status.NOT_PLAYER_IN_TURN;
            // Subtracts mana from Findus
            heroFindus.changeMana(-manaCost);
            //Adds card to field
            cardsOnFindusField.add(getCardInHand(who, indexOfFindusCard));
            //remove card from hand
            cardsInFindusHand.remove(card);
        } else {
            //Makes sure the player owns the card
            if (card.getOwner() != Player.PEDDERSEN) return Status.NOT_OWNER;
            // What index the chosen card is on in the hand
            int indexOfPeddersonCard = cardsInPeddersonsHand.indexOf(card);
            //Makes sure player has enough mana
            int manaCost = getCardInHand(Player.PEDDERSEN, indexOfPeddersonCard).getManaCost();
            if (manaCost > heroPedderson.getMana()) return Status.NOT_ENOUGH_MANA;
            //Makes sure its the player in turn
            if (getPlayerInTurn() != Player.PEDDERSEN) return Status.NOT_PLAYER_IN_TURN;
            // Subtracts mana from Pedderson
            heroPedderson.changeMana(-manaCost);
            //Adds card to field
            cardsOnPeddersonsField.add(getCardInHand(who, indexOfPeddersonCard));
            //remove card from hand
            cardsInPeddersonsHand.remove(card);
        }
        return Status.OK;
    }

    @Override
    public Status attackCard(Player playerAttacking, Card attackingCard, Card defendingCard) {
        return null;
    }

    @Override
    public Status attackHero(Player playerAttacking, Card attackingCard) {
        return null;
    }

    @Override
    public Status usePower(Player who) {
        Heroimpl hero = (Heroimpl) getHero(who);
        //If hero can use power
        if (hero.isActive) {
            // subtracts 2 mana
            hero.changeMana(-2);
            //Power is on cooldown untill next round
            hero.changeActive();
            return Status.OK;
        }
        return Status.POWER_USE_NOT_ALLOWED_TWICE_PR_ROUND;
    }
    public void drawCard(ArrayList cardsInHand, ArrayList deckName) {
        //Cards drawn always added to index 0
        cardsInHand.add(0, deckName.get(0));
        //Cards is removed from deck at index 0
        deckName.remove(deckName.get(0));
    }
}