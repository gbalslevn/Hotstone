package hotstone.standard;

import hotstone.framework.Game;
import hotstone.framework.Player;

public class Stats {

    private static int findusDamageOutputUntil6, peddersenDamageOutputUntil6, findusDamageOutputAfter6, peddersenDamageOutputAfter6;


    public static void changeDamageOutput(Player who, int damageOutput, int turnNumber) {
        if (turnNumber >= 13) {
            if (who == Player.FINDUS) findusDamageOutputAfter6 += damageOutput;
            else peddersenDamageOutputAfter6 += damageOutput;
        } else {
            if (who == Player.FINDUS) findusDamageOutputUntil6 += damageOutput;
            else peddersenDamageOutputUntil6 += damageOutput;
        }
    }

    public static void setDamageOutput(Player who, int newDamageOutput, int turnNumber) {
        if (turnNumber >= 13) {
            if (who == Player.FINDUS) findusDamageOutputAfter6 = newDamageOutput;
            else peddersenDamageOutputAfter6 = newDamageOutput;
        } else {
            if (who == Player.FINDUS) findusDamageOutputUntil6 = newDamageOutput;
            else peddersenDamageOutputUntil6 = newDamageOutput;
        }
    }

    public static int getDamageOutput(Player who, int turnNumber) {
        if (turnNumber >= 13) {
            return who == Player.FINDUS ? findusDamageOutputAfter6 : peddersenDamageOutputAfter6;
        } else {
            return who == Player.FINDUS ? findusDamageOutputUntil6 : peddersenDamageOutputUntil6;
        }
    }
}
