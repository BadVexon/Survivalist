package theSurvivalist.cards;

import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class BearTrap extends AbstractSurvivorCard {

    public final static String ID = makeID("BearTrap");

    //stupid intellij stuff SKILL, SELF, COMMON

    private static final int MAGIC = 2;
    private static final int UPG_MAGIC = 2;

    public BearTrap() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new ChannelAction(new theSurvivalist.traps.BearTrap(magicNumber)));
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