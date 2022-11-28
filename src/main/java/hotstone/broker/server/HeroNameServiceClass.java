package hotstone.broker.server;

import hotstone.framework.Card;
import hotstone.framework.Hero;

import java.util.HashMap;

public class HeroNameServiceClass implements HeroNameService {

    private HashMap<String, Hero> heroMap;
    public HeroNameServiceClass(){
        heroMap = new HashMap<>();
    }
    @Override
    public void putHero(String id, Hero hero) {
        heroMap.put(id, hero);
    }
    @Override
    public Hero getHero(String id) {
        return heroMap.get(id);
    }
}
