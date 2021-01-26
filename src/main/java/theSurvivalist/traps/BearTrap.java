package theSurvivalist.traps;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

public class BearTrap extends AbstractTrap {
    public static String ID = "survmod:BearTrap";

    public BearTrap(int amount) {
        super(ID, "Bear Trap","survmodResources/images/Bear.png", 4, amount);
    }

    @Override
    public void onStartOfTurn() {
        passiveAmount = 4;
    }

    @Override
    public void onDistanceChange(int amt) {
        if (amt < -4) amt = -4;
        if (amt > 0) amt = 0;
        for (int i = 0; i > amt; i--) decrement();
    }

    @Override
    public void onEvok() {
        for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (!m.isDead && !m.isDying) {
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, AbstractDungeon.player, new WeakPower(m, evokeAmount, false), evokeAmount));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, AbstractDungeon.player, new VulnerablePower(m, evokeAmount, false), evokeAmount));
            }
        }
    }

    @Override
    public void updateDescription() {
        description = "When you lose 4 Distance in a turn, apply #b" + evokeAmount + " #yWeak and #yVulnerable to ALL enemies.";
    }

    @Override
    public AbstractOrb makeCopy() {
        return new BearTrap(evokeAmount);
    }
}
