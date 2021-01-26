package theSurvivalist.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theSurvivalist.powers.NextTurnVigorPower;

public class TakeAim extends AbstractSurvivorCard {

    public final static String ID = makeID("TakeAim");

    //stupid intellij stuff SKILL, ENEMY, UNCOMMON

    private static final int MAGIC = 8;
    private static final int UPG_MAGIC = 7;

    public TakeAim() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new NextTurnVigorPower(magicNumber));
        distUp(-1);
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPG_MAGIC);
        }
    }
}