package hotstone.variants.CustomDeckStategy;

import hotstone.framework.Player;
import hotstone.framework.Strategies.DeckStrategy;
import hotstone.standard.CardImpl;
import hotstone.standard.GameConstants;
import hotstone.standard.StandardHotStoneGame;
import thirdparty.CardPODO;
import thirdparty.PersonalDeckReader;

import java.util.ArrayList;

public class CustomeDeck implements DeckStrategy {
    @Override
    public void createDeck(StandardHotStoneGame game) {
        addCardsToDeck(Player.FINDUS, (ArrayList) game.getDeck(Player.FINDUS));
        addCardsToDeck(Player.PEDDERSEN, (ArrayList) game.getDeck(Player.PEDDERSEN));
    }
    public void addCardsToDeck(Player who, ArrayList deck){
        PersonalDeckReader reader = new PersonalDeckReader("animaldeck.json");
        for (CardPODO acard : reader) {
            deck.add(new CardImpl(acard.name(), acard.mana(), acard.attack(), acard.health(), false, who));

        }
        reader = new PersonalDeckReader("allOtherFileNamesAreTheSame.json");
        for (CardPODO acard : reader) {
            deck.add(new CardImpl(acard.name(), acard.mana(), acard.attack(), acard.health(), false, who));
        }

    }
}
