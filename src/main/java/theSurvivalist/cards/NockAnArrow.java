package theSurvivalist.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.watcher.MarkPower;

public class NockAnArrow extends AbstractSurvivorCard {

    public final static String ID = makeID("NockAnArrow");

    //stupid intellij stuff ATTACK, ENEMY, COMMON

    private static final int DAMAGE = 5;
    private static final int UPG_DAMAGE = 3;

    public NockAnArrow() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        boolean bruh = false;
        for (AbstractMonster q : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (q.hasPower(MarkPower.POWER_ID))
                bruh = true;
        }
        if (bruh) {
            atb(new GainEnergyAction(1));
            atb(new DrawCardAction(p, 1));
        }
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPG_DAMAGE);
        }
    }
}