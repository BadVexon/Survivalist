package theSurvivalist.cards;

import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.watcher.MarkPower;

public class MarkedForDeath extends AbstractSurvivorCard {

    public final static String ID = makeID("MarkedForDeath");

    //stupid intellij stuff POWER, SELF, UNCOMMON

    public MarkedForDeath() {
        super(ID, 2, CardType.SKILL, CardRarity.RARE, CardTarget.ENEMY);
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (m.hasPower(MarkPower.POWER_ID)) {
            int x = m.getPower(MarkPower.POWER_ID).amount * 3;
            addToBot(new LoseHPAction(m, p, x));
            addToBot(new RemoveSpecificPowerAction(m, p, MarkPower.POWER_ID));
        }
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            exhaust = false;
            rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}