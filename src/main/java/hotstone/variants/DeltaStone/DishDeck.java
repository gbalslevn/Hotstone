package hotstone.variants.DeltaStone;

import hotstone.framework.Card;
import hotstone.framework.Game;
import hotstone.framework.Player;
import hotstone.framework.Strategies.DeckStrategy;
import hotstone.standard.CardImpl;
import hotstone.standard.GameConstants;
import hotstone.standard.StandardHotStoneGame;

import java.util.ArrayList;

import static java.util.Collections.shuffle;

public class DishDeck implements DeckStrategy {

    @Override
    public void createDeck(StandardHotStoneGame game) {
        ArrayList findusDeck = (ArrayList) game.getDeck(Player.FINDUS);
        ArrayList peddersonsDeck = (ArrayList) game.getDeck(Player.PEDDERSEN);

        buildAndShuffleDeck(findusDeck, Player.FINDUS);
        buildAndShuffleDeck(peddersonsDeck, Player.PEDDERSEN);

    }

    //Handles the card shuffle and sorting
    private void buildAndShuffleDeck(ArrayList deck,Player who) {
        //Add 24 cards to hand
        deckBuilder(deck, who);
        deckBuilder(deck, who);

        //Shuffles the deck
        shuffle(deck);

        sortByMana(deck);
    }

    private void sortByMana(ArrayList deck) {
        Card firstCard = sortMana(1, deck);
        Card secondCard = sortMana(2, deck);
        Card thirdCard = sortMana(4, deck);

        deck.add(0, firstCard);
        deck.add(1, secondCard);
        deck.add(2, thirdCard);
    }

    private void deckBuilder(ArrayList deckName, Player owner) {
        deckName.add(new CardImpl(GameConstants.BROWN_RICE_CARD, 1, 1, 2, false, owner));
        deckName.add(new CardImpl(GameConstants.FRENCH_FRIES_CARD, 1, 2, 1, false, owner));
        deckName.add(new CardImpl(GameConstants.GREEN_SALAD_CARD, 2, 2, 3, false, owner));
        deckName.add(new CardImpl(GameConstants.TOMATO_SALAD_CARD, 2, 3, 2, false, owner));
        deckName.add(new CardImpl(GameConstants.POKE_BOWL_CARD, 3, 2, 4, false, owner));
        deckName.add(new CardImpl(GameConstants.PUMPKIN_SOUP_CARD, 4, 2, 7, false, owner));
        deckName.add(new CardImpl(GameConstants.NOODLE_SOUP_CARD, 4, 5, 3, false, owner));
        deckName.add(new CardImpl(GameConstants.SPRING_ROLLS_CARD, 5, 3, 7, false, owner));
        deckName.add(new CardImpl(GameConstants.BAKED_SALMON_CARD, 5, 8, 2, false, owner));
        deckName.add(new CardImpl(GameConstants.CHICKEN_CURRY_CARD, 6, 8, 4, false, owner));
        deckName.add(new CardImpl(GameConstants.BEEF_BURGER_CARD, 6, 5, 6, false, owner));
        deckName.add(new CardImpl(GameConstants.FILET_MIGNON_CARD, 7, 9, 5, false, owner));
    }

    private Card sortMana(int mana, ArrayList<Card> deck) {
        for (Card c : deck) {
            if (c.getManaCost() <= mana) {
                Card resultCard;
                resultCard = c;
                deck.remove(c);
                return resultCard;
            }
        }
        return null;
    }
}
