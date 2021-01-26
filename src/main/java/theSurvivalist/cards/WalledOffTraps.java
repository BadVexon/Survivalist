package theSurvivalist.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.BranchingUpgradesCard;
import theSurvivalist.powers.WalledTrapsPower;

public class WalledOffTraps extends AbstractSurvivorCard {

    public final static String ID = makeID("WalledOffTraps");

    //stupid intellij stuff POWER, SELF, UNCOMMON

    private static final int MAGIC = 4;
    private static final int UPG_MAGIC = 2;

    public WalledOffTraps() {
        super(ID, 2, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new WalledTrapsPower(magicNumber));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPG_MAGIC);
        }
    }
}