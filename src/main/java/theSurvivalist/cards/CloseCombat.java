package theSurvivalist.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theSurvivalist.powers.AdaptabilityPower;
import theSurvivalist.powers.CloseCombatPower;

public class CloseCombat extends AbstractSurvivorCard {

    public final static String ID = makeID("CloseCombat");

    //stupid intellij stuff POWER, SELF, UNCOMMON

    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 1;

    public CloseCombat() {
        super(ID, 1, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new CloseCombatPower(magicNumber));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPG_MAGIC);
        }
    }
}