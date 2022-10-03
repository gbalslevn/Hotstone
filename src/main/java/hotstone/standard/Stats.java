package hotstone.standard;

import hotstone.framework.Player;

public class Stats {

    private int findusDamageOutput, peddersonDamageOutput;

    public void setDamageOutput(Player who, int damageOutput) {
        if (who == Player.FINDUS) findusDamageOutput += damageOutput;
        else peddersonDamageOutput += damageOutput;
    }

    public int getDamageOutput(Player who) {
        return who == Player.FINDUS ? findusDamageOutput : peddersonDamageOutput;
    }
}
