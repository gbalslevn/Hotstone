package hotstone.framework.Strategies;

import hotstone.framework.Player;
import hotstone.standard.HeroImpl;

public interface TypeStrategy {


    HeroImpl chooseType(Player who);
}
