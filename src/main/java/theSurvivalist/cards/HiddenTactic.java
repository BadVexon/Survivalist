package theSurvivalist.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.BranchingUpgradesCard;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;
import theSurvivalist.DistancePanel;

public class HiddenTactic extends AbstractSurvivorCard {

    public final static String ID = makeID("HiddenTactic");

    //stupid intellij stuff SKILL, SELF, UNCOMMON

    private static final int MAGIC = 2;
    private static final int UPG_MAGIC = 2;

    public HiddenTactic() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new IntangiblePlayerPower(p, 1));
        atb(new DrawCardAction(magicNumber));
        distUp(-3);
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        if (DistancePanel.getPow() != DistancePanel.DPOW.LONG_RANGE) {
            cantUseMessage = "I'm too close!";
            return false;
        } else {
            return super.canUse(p, m);
        }
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPG_MAGIC);
        }
    }
}