package theSurvivalist.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theSurvivalist.DistancePanel;

public class PinningSnipe extends AbstractSurvivorCard {

    public final static String ID = makeID("PinningSnipe");

    //stupid intellij stuff ATTACK, ENEMY, COMMON

    private static final int DAMAGE = 10;
    private static final int UPG_DAMAGE = 5;

    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 1;

    public PinningSnipe() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HEAVY);
        applyToEnemy(m, autoWeak(m, magicNumber));
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        cantUseMessage = "I'm too close!";
        if (DistancePanel.getPow() == DistancePanel.DPOW.LONG_RANGE) {
            return super.canUse(p, m);
        } else
            return false;
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPG_DAMAGE);
            upgradeMagicNumber(UPG_MAGIC);
        }
    }
}