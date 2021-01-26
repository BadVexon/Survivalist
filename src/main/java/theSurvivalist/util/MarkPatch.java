package theSurvivalist.util;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.watcher.MarkPower;
import theSurvivalist.cards.AbstractSurvivorCard;

@SpirePatch(
        clz = MarkPower.class,
        method = "triggerMarks"
)
public class MarkPatch {
    public static void Postfix(MarkPower __instance, AbstractCard callingCard) {
        if (callingCard instanceof AbstractSurvivorCard) {
            AbstractDungeon.actionManager.addToBottom(new LoseHPAction(__instance.owner, (AbstractCreature) null, __instance.amount, AbstractGameAction.AttackEffect.FIRE));// 34
        }
    }
}