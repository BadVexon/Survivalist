package theSurvivalist.traps;

import com.evacipated.cardcrawl.mod.stslib.actions.defect.EvokeSpecificOrbAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.BlurPower;

public class FenceTrap extends AbstractTrap {
    public static String ID = "survmod:FenceTrap";

    public FenceTrap(int amount) {
        super(ID, "Fence Trap", "survmodResources/images/Fence.png",-1, amount);
    }

    @Override
    public void onBlockBroken() {
        AbstractDungeon.actionManager.addToBottom(new EvokeSpecificOrbAction(this));
    }

    @Override
    public void onEvok() {
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(AbstractDungeon.player, evokeAmount));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new BlurPower(AbstractDungeon.player, 1), 1));
    }

    @Override
    public void updateDescription() {
        description = "When your #yBlock is broken, gain #b" + evokeAmount + " #yBlock and #b1 Blur.";
    }

    @Override
    public AbstractOrb makeCopy() {
        return new FenceTrap(evokeAmount);
    }
}
