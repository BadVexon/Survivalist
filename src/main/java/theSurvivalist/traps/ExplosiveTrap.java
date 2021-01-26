package theSurvivalist.traps;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;

public class ExplosiveTrap extends AbstractTrap {
    public static String ID = "survmod:ExplosiveTrap";

    public ExplosiveTrap(int amount) {
        super(ID, "Explosive Trap","survmodResources/images/Explosive.png", 4, amount);
    }

    @Override
    public void onDistanceChange(int amt) {
        if (amt > 4) amt = 4;
        if (amt < 0) amt = 0;
        for (int i = 0; i < amt; i++) decrement();
    }

    @Override
    public void onStartOfTurn() {
        passiveAmount = 4;
    }

    @Override
    public void onEvok() {
        AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(AbstractDungeon.player, DamageInfo.createDamageMatrix(evokeAmount, true, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.FIRE));
    }

    @Override
    public void updateDescription() {
        description = "When you gain #b4 #yDistance in a turn, deal #b" + evokeAmount + " damage to ALL enemies.";
    }

    @Override
    public AbstractOrb makeCopy() {
        return new ExplosiveTrap(evokeAmount);
    }
}
