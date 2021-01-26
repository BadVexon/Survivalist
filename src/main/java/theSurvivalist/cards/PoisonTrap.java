package theSurvivalist.cards;

import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class PoisonTrap extends AbstractSurvivorCard {

    public final static String ID = makeID("PoisonTrap");

    //stupid intellij stuff SKILL, SELF, COMMON

    private static final int MAGIC = 8;
    private static final int UPG_MAGIC = 2;

    public PoisonTrap() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        if (orbsFull()) {
            cantUseMessage = "I have no space to prepare more traps.";
            return false;
        }
        return super.canUse(p, m);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new ChannelAction(new theSurvivalist.traps.PoisonTrap(magicNumber)));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPG_MAGIC);
        }
    }
}