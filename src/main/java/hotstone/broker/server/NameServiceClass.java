package hotstone.broker.server;

import hotstone.framework.Card;
import hotstone.framework.Hero;

import java.util.HashMap;

public class NameServiceClass implements NameService {
    private HashMap<String,Card> cardMap;

    private HashMap<String, Hero> heroMap;

    public NameServiceClass(){
        cardMap = new HashMap<>();
        heroMap = new HashMap<>();
    }

    @Override
    public void putCard(String id, Card card) {
        cardMap.put(id,card);
    }

    @Override
    public Card getCard(String id) {
        return cardMap.get(id); }

    @Override
    public Hero getHero(String id) {
        return heroMap.get(id);
    }

    @Override
    public void putHero(String id, Hero hero) {
        heroMap.put(id, hero);
    }
}
