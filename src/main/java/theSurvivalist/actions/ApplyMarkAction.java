package theSurvivalist.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.watcher.MarkPower;
import theSurvivalist.cards.Hawkeye;
import theSurvivalist.powers.HawkeyePower;
import theSurvivalist.powers.MarksmanshipPower;

public class ApplyMarkAction extends AbstractGameAction {
    public ApplyMarkAction(int bruh, AbstractMonster m) {
        amount = bruh;
        target = m;
    }

    @Override
    public void update() {
        isDone = true;
        int q = amount;
        if (AbstractDungeon.player.hasPower(MarksmanshipPower.POWER_ID)) {
            q += AbstractDungeon.player.getPower(MarksmanshipPower.POWER_ID).amount;
        }
        if (AbstractDungeon.player.hasPower(HawkeyePower.POWER_ID)) {
            q *= AbstractDungeon.player.getPower(HawkeyePower.POWER_ID).amount;
        }
        Hawkeye.myList.put((AbstractMonster) target, amount);
        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(target, AbstractDungeon.player, new MarkPower(target, q), q));
    }
}
