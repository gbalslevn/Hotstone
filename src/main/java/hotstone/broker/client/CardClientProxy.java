package hotstone.broker.client;

import frds.broker.ClientProxy;
import frds.broker.Requestor;
import hotstone.broker.common.OperationNames;
import hotstone.framework.Card;
import hotstone.framework.Player;

public class CardClientProxy implements Card, ClientProxy {
    public static final String CARD_OPBJECTID = "pending";

    private final Requestor requestor;
    public CardClientProxy(Requestor requestor) {
        this.requestor = requestor;

    }

    @Override
    public String getName() {
        String name = requestor.sendRequestAndAwaitReply(CARD_OPBJECTID, OperationNames.CARD_GET_NAME, String.class);
        return name;
    }

    @Override
    public int getManaCost() {
        return 0;
    }

    @Override
    public int getAttack() {
        return 0;
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
    public Player getOwner() {
        return null;
    }
}
