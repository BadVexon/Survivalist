package theSurvivalist.traps;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

public class AcidTrap extends AbstractTrap {
    public static String ID = "survmod:AcidTrap";

    public AcidTrap(int amount) {
        super(ID, "Acid Trap", "survmodResources/images/Acid.png", 2, amount);
    }

    @Override
    public void onUseCard(AbstractCard q) {
        decrement();
    }

    @Override
    public void onEvok() {
        for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (!m.isDead && !m.isDying) {
                AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(m, AbstractDungeon.player, ArtifactPower.POWER_ID));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, AbstractDungeon.player, new PoisonPower(m, AbstractDungeon.player, evokeAmount), evokeAmount));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, AbstractDungeon.player, new WeakPower(m,1, false), 1));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, AbstractDungeon.player, new VulnerablePower(m, 1, false),1));
            }
        }
    }

    @Override
    public void updateDescription() {
        description = "After playing #b" + passiveAmount + " cards in a turn, remove ALL #yArtifact from ALL enemies, and apply #b" + evokeAmount + " #yPoison, #b1 #yWeak and #b1 #yVulnerable.";
    }

    @Override
    public AbstractOrb makeCopy() {
        return new AcidTrap(evokeAmount);
    }

    @Override
    public void onStartOfTurn() {
        passiveAmount = 2;
    }
}
