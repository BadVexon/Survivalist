package theSurvivalist.util;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import theSurvivalist.TheSurvivor;

import static theSurvivalist.SurvivalMod.newHitbox;
import static theSurvivalist.SurvivalMod.renderStuff;

@SpirePatch(
        clz = EnergyPanel.class,
        method = "update"
)
public class DragUpdatePatch {
    public static void Prefix(EnergyPanel __instance) {
        if ((AbstractDungeon.player instanceof TheSurvivor || renderStuff) && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            newHitbox.update();
            newHitbox.dragUpdate();
        }
    }
}