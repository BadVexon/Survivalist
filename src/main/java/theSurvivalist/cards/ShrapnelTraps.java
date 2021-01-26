package theSurvivalist.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.BranchingUpgradesCard;
import theSurvivalist.powers.ShrapnelTrapsPower;

public class ShrapnelTraps extends AbstractSurvivorCard {

    public final static String ID = makeID("ShrapnelTraps");

    //stupid intellij stuff POWER, SELF, UNCOMMON

    private static final int MAGIC = 4;
    private static final int UPG_MAGIC = 2;

    public ShrapnelTraps() {
        super(ID, 2, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new ShrapnelTrapsPower(magicNumber));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPG_MAGIC);
        }
    }
}