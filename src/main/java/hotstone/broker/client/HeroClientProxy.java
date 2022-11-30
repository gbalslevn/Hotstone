package hotstone.broker.client;

import frds.broker.ClientProxy;
import frds.broker.Requestor;
import hotstone.broker.common.OperationNames;
import hotstone.framework.Hero;
import hotstone.framework.Player;

public class HeroClientProxy implements Hero, ClientProxy {
    private final String objectId;
    private final Requestor requestor;
    public HeroClientProxy(Requestor requestor, String objectId) {
        this.requestor = requestor;
        this.objectId = objectId;
    }

    @Override
    public int getMana() {
        int mana = requestor.sendRequestAndAwaitReply(objectId, OperationNames.HERO_GET_MANA, Integer.class);
        return mana;
    }

    @Override
    public int getHealth() {
        int health = requestor.sendRequestAndAwaitReply(objectId, OperationNames.HERO_GET_HEALTH, Integer.class);
        return health;
    }

    @Override
    public boolean isActive() {
        boolean activeStatus = requestor.sendRequestAndAwaitReply(objectId, OperationNames.HERO_IS_ACTIVE, Boolean.class);
        return activeStatus;
    }

    @Override
    public String getType() {
        String type = requestor.sendRequestAndAwaitReply(objectId, OperationNames.HERO_GET_TYPE, String.class);
        return type;
    }

    @Override
    public Player getOwner() {
        Player player = requestor.sendRequestAndAwaitReply(objectId, OperationNames.HERO_GET_OWNER, Player.class);
        return player;
    }

    @Override
    public String getEffectDescription() {
        String description = requestor.sendRequestAndAwaitReply(objectId, OperationNames.HERO_GET_EFFECT_DESCRIPTION, String.class);
        return description;
    }

    @Override
    public String getId() {
        return objectId;
    }
}
