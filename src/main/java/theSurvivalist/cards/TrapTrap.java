package theSurvivalist.cards;

import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class TrapTrap extends AbstractSurvivorCard {

    public final static String ID = makeID("TrapTrap");

    //stupid intellij stuff SKILL, SELF, COMMON

    public TrapTrap() {
        super(ID, 3, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new ChannelAction(new theSurvivalist.traps.TrapTrap()));
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
            upgradeBaseCost(2);
        }
    }
}