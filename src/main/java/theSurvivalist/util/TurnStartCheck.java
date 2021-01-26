package theSurvivalist.util;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import javassist.CtBehavior;
import theSurvivalist.SurvivalMod;
import theSurvivalist.TheSurvivor;
import theSurvivalist.actions.DistanceAction;
import theSurvivalist.powers.AdvantagePower;
import theSurvivalist.powers.FakeFlightPower;
import theSurvivalist.relics.IronskinOutfit;
import theSurvivalist.traps.AbstractTrap;

@SpirePatch(
        clz = GameActionManager.class,
        method = "getNextAction"
)
public class TurnStartCheck {
    @SpireInsertPatch(
            locator = Locator.class
    )
    public static void Insert(GameActionManager __instance) {
        if (AbstractDungeon.player instanceof TheSurvivor || SurvivalMod.renderStuff) {
            int bruh = -2;
            if (AbstractDungeon.player.hasPower(AdvantagePower.POWER_ID))
                bruh += 1;
            if (AbstractDungeon.player.hasRelic(IronskinOutfit.ID)) {
                bruh -= 1;
            }
            if (AbstractDungeon.player.hasPower(FakeFlightPower.POWER_ID)) {
                bruh = 0;
            }
            AbstractDungeon.actionManager.addToBottom(new DistanceAction(bruh));
            if (!SurvivalMod.beingAttacked) {
                for (AbstractOrb o : AbstractDungeon.player.orbs) {
                    if (o instanceof AbstractTrap) {
                        ((AbstractTrap) o).onNotAttacked();
                    }
                }
            }
            SurvivalMod.beingAttacked = false;
        }
    }

    private static class Locator extends SpireInsertLocator {
        public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
            Matcher finalMatcher = new Matcher.MethodCallMatcher(AbstractPlayer.class, "applyStartOfTurnRelics");
            return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
        }
    }
}