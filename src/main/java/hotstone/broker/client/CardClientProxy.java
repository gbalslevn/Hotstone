package hotstone.broker.client;

import frds.broker.ClientProxy;
import frds.broker.Requestor;
import hotstone.broker.common.OperationNames;
import hotstone.framework.Card;
import hotstone.framework.Player;

public class CardClientProxy implements Card, ClientProxy {
    private final String objectId;
    private final Requestor requestor;
    public CardClientProxy(Requestor requestor, String objectId) {
        this.requestor = requestor;
        this.objectId = objectId;
    }


    @Override
    public String getName() {
        String name = requestor.sendRequestAndAwaitReply(objectId, OperationNames.CARD_GET_NAME, String.class);
        return name;
    }

    @Override
    public int getManaCost() {
        int mana = requestor.sendRequestAndAwaitReply(objectId, OperationNames.CARD_GET_MANA_COST, Integer.class);
        return mana;
    }

    @Override
    public int getAttack() {
        int attack = requestor.sendRequestAndAwaitReply(objectId, OperationNames.CARD_GET_ATTACK, Integer.class);
        return attack;
    }

    @Override
    public int getHealth() {
        int health = requestor.sendRequestAndAwaitReply(objectId, OperationNames.CARD_GET_HEALTH, Integer.class);
        return health;
    }

    @Override
    public boolean isActive() {
        boolean isActive = requestor.sendRequestAndAwaitReply(objectId, OperationNames.CARD_IS_ACTIVE, Boolean.class);
        return isActive;
    }

    @Override
    public Player getOwner() {
        Player owner = requestor.sendRequestAndAwaitReply(objectId,OperationNames.CARD_GET_OWNER,Player.class);
        return owner;
    }

    @Override
    public String getId() {
        return objectId;
    }
}
