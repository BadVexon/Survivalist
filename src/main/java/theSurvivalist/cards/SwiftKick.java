package theSurvivalist.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theSurvivalist.DistancePanel;
import theSurvivalist.powers.AdvantagePower;

public class SwiftKick extends AbstractSurvivorCard {

    public final static String ID = makeID("SwiftKick");

    //stupid intellij stuff ATTACK, ENEMY, COMMON

    private static final int DAMAGE = 5;
    private static final int UPG_DAMAGE = 1;

    private static final int MAGIC = 2;
    private static final int UPG_MAGIC = 1;

    public SwiftKick() {
        super(ID, 0, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        applyToEnemy(m, autoVuln(m, magicNumber));
        applyToSelf(new AdvantagePower(2));
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        if (DistancePanel.getPow() != DistancePanel.DPOW.CLOSE_RANGE) {
            cantUseMessage = "I'm not close enough!";
            return false;
        } else {
            return super.canUse(p, m);
        }
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPG_DAMAGE);
            upgradeMagicNumber(UPG_MAGIC);
        }
    }
}