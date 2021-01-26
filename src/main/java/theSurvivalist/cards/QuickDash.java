package theSurvivalist.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theSurvivalist.DistancePanel;
import theSurvivalist.powers.AdvantagePower;

public class QuickDash extends AbstractSurvivorCard {

    public final static String ID = makeID("QuickDash");

    //stupid intellij stuff SKILL, SELF, COMMON

    private static final int BLOCK = 4;
    private static final int UPG_BLOCK = 1;

    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 1;

    public QuickDash() {
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF_AND_ENEMY);
        baseBlock = BLOCK;
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        applyToEnemy(m, autoWeak(m, magicNumber));
        distUp(2);
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
            upgradeBlock(UPG_BLOCK);
            upgradeMagicNumber(UPG_MAGIC);
        }
    }
}