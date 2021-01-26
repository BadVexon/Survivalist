package theSurvivalist.traps;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.PoisonPower;

public class PoisonTrap extends AbstractTrap {
    public static String ID = "survmod:PoisonTrap";

    public PoisonTrap(int amount) {
        super(ID, "Poison Trap", "survmodResources/images/Poison.png", 3, amount);
    }

    @Override
    public void onUseCard(AbstractCard q) {
        if (q.type == AbstractCard.CardType.ATTACK) {
            decrement();
        }
    }

    @Override
    public void onEvok() {
        for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (!m.isDead && !m.isDying) {
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, AbstractDungeon.player, new PoisonPower(m, AbstractDungeon.player, evokeAmount), evokeAmount));
            }
        }
    }

    @Override
    public void updateDescription() {
        description = "After playing #b" + passiveAmount + " #yAttacks in a turn, apply #b" + evokeAmount + " #yPoison to ALL enemies.";
    }

    @Override
    public AbstractOrb makeCopy() {
        return new PoisonTrap(evokeAmount);
    }

    @Override
    public void onStartOfTurn() {
        passiveAmount = 3;
    }
}
