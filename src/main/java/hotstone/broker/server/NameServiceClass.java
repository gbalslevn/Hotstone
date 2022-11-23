package hotstone.broker.server;

import hotstone.framework.Card;

import java.util.HashMap;

public class NameServiceClass implements NameService {
    private HashMap<String,Card> cardMap;
    public NameServiceClass(){
        cardMap = new HashMap<>();
    }


    @Override
    public void putCard(String id, Card card) {
        cardMap.put(id,card);
    }

    @Override
    public Card getCard(String id) {
        return cardMap.get(id); }
}
