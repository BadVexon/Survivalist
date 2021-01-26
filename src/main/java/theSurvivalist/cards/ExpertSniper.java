package theSurvivalist.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theSurvivalist.powers.AdaptabilityPower;
import theSurvivalist.powers.ExpertPower;

public class ExpertSniper extends AbstractSurvivorCard {

    public final static String ID = makeID("ExpertSniper");

    //stupid intellij stuff POWER, SELF, UNCOMMON

    private static final int MAGIC = 6;
    private static final int UPG_MAGIC = 3;

    public ExpertSniper() {
        super(ID, 1, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new ExpertPower(magicNumber));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPG_MAGIC);
        }
    }
}