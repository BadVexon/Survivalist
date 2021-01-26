package theSurvivalist.cards;

import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ExplosiveTrap extends AbstractSurvivorCard {

    public final static String ID = makeID("ExplosiveTrap");

    //stupid intellij stuff SKILL, SELF, COMMON

    private static final int MAGIC = 14;
    private static final int UPG_MAGIC = 4;

    public ExplosiveTrap() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new ChannelAction(new theSurvivalist.traps.ExplosiveTrap(magicNumber)));
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        if (orbsFull()) {
            cantUseMessage = "I have no space to prepare more traps.";
            return false;
        }
        return super.canUse(p, m);
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPG_MAGIC);
        }
    }
}