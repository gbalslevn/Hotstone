package hotstone.variants.DeltaStone;

import hotstone.framework.Card;
import hotstone.framework.Player;
import hotstone.framework.Strategies.DeckStrategy;
import hotstone.standard.CardImpl;
import hotstone.standard.StandardHotStoneGame;

import java.util.ArrayList;

import static java.util.Collections.shuffle;

public class DishDeck implements DeckStrategy {
    ArrayList findusDeck = new ArrayList();
    ArrayList peddersonsDeck = new ArrayList();


    @Override
    public void createDeck(StandardHotStoneGame game) {
        ArrayList findusDeck = (ArrayList) game.getDeck(Player.FINDUS);
        ArrayList peddersonsDeck = (ArrayList) game.getDeck(Player.PEDDERSEN);

        //Add 24 cards to findus hand
        deckBuilder(findusDeck, Player.FINDUS);
        deckBuilder(findusDeck, Player.FINDUS);

        //Add 24 cards to pedderson hand
        deckBuilder(peddersonsDeck, Player.PEDDERSEN);
        deckBuilder(peddersonsDeck, Player.PEDDERSEN);

        //Shuffles the 2 decks
        shuffle(findusDeck);
        shuffle(peddersonsDeck);

        Card firstCardP = sortMana(1, peddersonsDeck);
        Card secondCardP = sortMana(2, peddersonsDeck);
        Card thirdCardP = sortMana(4, peddersonsDeck);
        peddersonsDeck.add(firstCardP);
        peddersonsDeck.add(secondCardP);
        peddersonsDeck.add(thirdCardP);

        Card firstCardF = sortMana(1, findusDeck);
        Card secondCardF = sortMana(2, findusDeck);
        Card thirdCardF = sortMana(4, findusDeck);
        findusDeck.add(0,firstCardF);
        findusDeck.add(1, secondCardF);
        findusDeck.add(2, thirdCardF);
    }

    private void deckBuilder(ArrayList deckName, Player owner) {
        deckName.add(new CardImpl("Brown Rice", 1, 1, 2, false, owner));
        deckName.add(new CardImpl("French Fries", 1, 2, 1, false, owner));
        deckName.add(new CardImpl("Green Salad", 2, 2, 3, false, owner));
        deckName.add(new CardImpl("Tomato Salad", 2, 3, 2, false, owner));
        deckName.add(new CardImpl("Poke Bowl", 3, 2, 4, false, owner));
        deckName.add(new CardImpl("Pumpkin Soup", 4, 2, 7, false, owner));
        deckName.add(new CardImpl("Noodle Soup", 4, 5, 3, false, owner));
        deckName.add(new CardImpl("Spring Rolls", 5, 3, 7, false, owner));
        deckName.add(new CardImpl("Baked Salmon", 5, 8, 2, false, owner));
        deckName.add(new CardImpl("Chicken Curry", 6, 8, 4, false, owner));
        deckName.add(new CardImpl("Beef Burger", 6, 5, 6, false, owner));
        deckName.add(new CardImpl("Filet Mignon", 7, 9, 5, false, owner));
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
