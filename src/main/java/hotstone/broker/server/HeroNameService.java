package hotstone.broker.server;

import hotstone.framework.Hero;

public interface HeroNameService {
    void putHero(String id, Hero hero);

    Hero getHero(String id);
}
