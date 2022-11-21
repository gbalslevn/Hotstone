package hotstone.broker.server;

import hotstone.framework.Card;

import java.util.HashMap;

public class NameServiceClass implements NameService {

    private HashMap cardMap = new HashMap<String,Card>();
    @Override
    public void putCard(String id, Card card) {
        cardMap.put(id,card);
    }

    @Override
    public Card getCard(String id) {
        return (Card) cardMap.get(id);
    }
}
