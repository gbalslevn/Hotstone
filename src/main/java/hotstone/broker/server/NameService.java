package hotstone.broker.server;

import hotstone.framework.Card;
import hotstone.framework.Hero;

public interface NameService {

    void putCard(String id, Card card);

    Card getCard(String id);

    Hero getHero(String id);

    void putHero(String id, Hero hero);
}
