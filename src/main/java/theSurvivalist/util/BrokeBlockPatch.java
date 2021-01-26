package theSurvivalist.util;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.watcher.MarkPower;
import theSurvivalist.cards.AbstractSurvivorCard;
import theSurvivalist.traps.AbstractTrap;

@SpirePatch(
        clz = AbstractCreature.class,
        method = "brokeBlock"
)
public class BrokeBlockPatch {
    public static void Postfix(AbstractCreature __instance) {
        if (__instance instanceof AbstractPlayer)
        for (AbstractOrb o : AbstractDungeon.player.orbs) {
            if (o instanceof AbstractTrap) {
                ((AbstractTrap) o).onBlockBroken();
            }
        }
    }
}