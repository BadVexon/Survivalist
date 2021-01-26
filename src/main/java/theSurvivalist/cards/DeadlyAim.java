package theSurvivalist.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theSurvivalist.powers.NextTurnSuperStrongAttacksPower;

public class DeadlyAim extends AbstractSurvivorCard {

    public final static String ID = makeID("DeadlyAim");

    //stupid intellij stuff SKILL, ENEMY, UNCOMMON


    public DeadlyAim() {
        super(ID, 2, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new NextTurnSuperStrongAttacksPower(8));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(1);
        }
    }
}