package hotstone.standard;

import hotstone.framework.Player;

public class Stats {

    private static int findusDamageOutput, peddersonDamageOutput;

    public static void setDamageOutput(Player who, int damageOutput) {
        if (who == Player.FINDUS) findusDamageOutput += damageOutput;
        else peddersonDamageOutput += damageOutput;
    }

    public static int getDamageOutput(Player who) {
        return who == Player.FINDUS ? findusDamageOutput : peddersonDamageOutput;
    }
}
