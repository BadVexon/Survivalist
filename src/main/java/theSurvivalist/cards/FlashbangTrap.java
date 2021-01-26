package theSurvivalist.cards;

import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class FlashbangTrap extends AbstractSurvivorCard {

    public final static String ID = makeID("FlashbangTrap");

    //stupid intellij stuff SKILL, SELF, COMMON

    private static final int MAGIC = 10;
    private static final int UPG_MAGIC = 4;

    public FlashbangTrap() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new ChannelAction(new theSurvivalist.traps.FlashbangTrap(magicNumber)));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPG_MAGIC);
        }
    }
}