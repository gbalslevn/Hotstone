package hotstone.broker.server;

import hotstone.framework.Card;

public interface NameService {

    void putCard(String id, Card card);

    Card getCard(String id);
}
