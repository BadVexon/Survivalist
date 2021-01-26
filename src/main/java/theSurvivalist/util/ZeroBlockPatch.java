package theSurvivalist.util;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import theSurvivalist.powers.FakeFlightPower;

@SpirePatch(
        clz = GainBlockAction.class,
        method = "update"
)
public class ZeroBlockPatch {
    public static SpireReturn Prefix(GainBlockAction __instance) {
        if (__instance.target.hasPower(FakeFlightPower.POWER_ID)) {
            __instance.isDone = true;
            return SpireReturn.Return(null);
        }
        return SpireReturn.Continue();
    }
}