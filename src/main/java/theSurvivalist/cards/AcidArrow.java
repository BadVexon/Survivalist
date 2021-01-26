package theSurvivalist.cards;

import com.megacrit.cardcrawl.actions.common.RemoveAllBlockAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.BranchingUpgradesCard;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.PoisonPower;

public class AcidArrow extends AbstractSurvivorCard {

    public final static String ID = makeID("AcidArrow");

    //stupid intellij stuff SKILL, ENEMY, UNCOMMON

    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 1;

    public AcidArrow() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new RemoveAllBlockAction(m, p));
        atb(new RemoveSpecificPowerAction(m, p, ArtifactPower.POWER_ID));
        applyToEnemy(m, new PoisonPower(m, p, 4));
        applyToEnemy(m, autoVuln(m, magicNumber));
        applyToEnemy(m, autoWeak(m, magicNumber));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPG_MAGIC);
        }
    }
}