package theSurvivalist.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.watcher.MarkPower;

public class KnowThyEnemy extends AbstractSurvivorCard {

    public final static String ID = makeID("KnowThyEnemy");

    //stupid intellij stuff SKILL, ENEMY, UNCOMMON

    public KnowThyEnemy() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (m.hasPower(MarkPower.POWER_ID)) {
            addToBot(new GainBlockAction(p, m.getPower(MarkPower.POWER_ID).amount * magicNumber));
            addToBot(new RemoveSpecificPowerAction(m, p, MarkPower.POWER_ID));
        }
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(2);
        }
    }
}