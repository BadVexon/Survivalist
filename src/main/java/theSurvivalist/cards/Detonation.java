package theSurvivalist.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.watcher.MarkPower;
import theSurvivalist.powers.ActivateMarksLaterPower;

public class Detonation extends AbstractSurvivorCard {

    public final static String ID = makeID("Detonation");

    //stupid intellij stuff SKILL, ENEMY, UNCOMMON

    public Detonation() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.ENEMY);
        exhaust = true;
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (m.hasPower(MarkPower.POWER_ID)) {
            applyToEnemy(m, new MarkPower(m, m.getPower(MarkPower.POWER_ID).amount));
        }
        for (AbstractMonster q : monsterList()) {
            applyToEnemy(q, new ActivateMarksLaterPower(q, magicNumber));
        }
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(0);
        }
    }
}