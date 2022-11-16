package hotstone.broker.client;

import frds.broker.ClientProxy;
import frds.broker.Requestor;
import hotstone.broker.common.OperationNames;
import hotstone.framework.Hero;
import hotstone.framework.Player;

public class HeroClientProxy implements Hero, ClientProxy {
    public static final String GAME_OPBJECTID = "singleton";
    private String id;


    private final Requestor requestor;
    public HeroClientProxy(Requestor requestor) {
        this.requestor = requestor;
        id = "pending";
    }

    @Override
    public int getMana() {
        int mana = requestor.sendRequestAndAwaitReply(GAME_OPBJECTID, OperationNames.HERO_GET_MANA, Integer.class);
        return mana;
    }

    @Override
    public int getHealth() {
        return 0;
    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public String getType() {
        return null;
    }

    @Override
    public Player getOwner() {
        return null;
    }

    @Override
    public String getEffectDescription() {
        return null;
    }
}
