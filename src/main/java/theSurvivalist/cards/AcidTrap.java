package theSurvivalist.cards;

import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class AcidTrap extends AbstractSurvivorCard {

    public final static String ID = makeID("AcidTrap");

    //stupid intellij stuff SKILL, SELF, COMMON

    public AcidTrap() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new ChannelAction(new theSurvivalist.traps.AcidTrap(magicNumber)));
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
            upgradeMagicNumber(2);
        }
    }
}