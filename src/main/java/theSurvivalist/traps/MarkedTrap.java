package theSurvivalist.traps;

import com.evacipated.cardcrawl.mod.stslib.actions.defect.EvokeSpecificOrbAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.watcher.MarkPower;

public class MarkedTrap extends AbstractTrap {
    public static String ID = "survmod:MarkedTrap";

    public MarkedTrap() {
        super(ID, "Marked Trap", "survmodResources/images/Marked.png", -1, -1);
    }

    @Override
    public void onNotAttacked() {
        AbstractDungeon.actionManager.addToBottom(new EvokeSpecificOrbAction(this));
    }

    @Override
    public void onEvok() {
        AbstractDungeon.actionManager.addToBottom(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                for (AbstractMonster q : AbstractDungeon.getCurrRoom().monsters.monsters) {
                    if (!q.isDying && !q.isDead) {
                        if (q.hasPower(MarkPower.POWER_ID)) {
                            addToTop(new LoseHPAction(q, AbstractDungeon.player, q.getPower(MarkPower.POWER_ID).amount));
                        }
                    }
                }
            }
        });
    }

    @Override
    public void updateDescription() {
        description = "When you aren't attacked for a turn, ALL enemies lose HP equal to their Mark.";
    }

    @Override
    public AbstractOrb makeCopy() {
        return new MarkedTrap();
    }
}
