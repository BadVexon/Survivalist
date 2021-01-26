package theSurvivalist.traps;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.watcher.MarkPower;
import theSurvivalist.actions.ApplyMarkAction;

public class FlashbangTrap extends AbstractTrap {
    public static String ID = "survmod:FlashbangTrap";

    public FlashbangTrap(int amount) {
        super(ID, "Flashbang Trap", "survmodResources/images/FlashBang.png", 2, amount);
    }

    @Override
    public void onUseCard(AbstractCard q) {
        if (q.type == AbstractCard.CardType.SKILL) {
            decrement();
        }
    }

    @Override
    public void onEvok() {
        for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (!m.isDead && !m.isDying) {
                AbstractDungeon.actionManager.addToBottom(new ApplyMarkAction(evokeAmount, m));
            }
        }
    }

    @Override
    public void updateDescription() {
        description = "After playing #b" + passiveAmount + " #ySkills in a turn, apply #b" + evokeAmount + " #yMarked to ALL enemies.";
    }

    @Override
    public AbstractOrb makeCopy() {
        return new FlashbangTrap(evokeAmount);
    }

    @Override
    public void onStartOfTurn() {
        passiveAmount = 2;
    }
}
