package theSurvivalist.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RemoveAllBlockAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.powers.watcher.MarkPower;
import theSurvivalist.DistancePanel;

public class PenetratingShot extends AbstractSurvivorCard {

    public final static String ID = makeID("PenetratingShot");

    //stupid intellij stuff SKILL, ENEMY, UNCOMMON

    public PenetratingShot() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 10;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
        if (m.hasPower(MarkPower.POWER_ID)) {
            applyToEnemy(m, autoVuln(m, 2));
        }
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        if (DistancePanel.getPow() != DistancePanel.DPOW.LONG_RANGE) {
            cantUseMessage = "I'm too close!";
            return false;
        } else {
            return super.canUse(p, m);
        }
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(5);
        }
    }
}