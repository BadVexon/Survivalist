package theSurvivalist.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theSurvivalist.DistancePanel;
import theSurvivalist.powers.AdvantagePower;
import theSurvivalist.powers.LowerCardCostPower;

public class QuickThinking extends AbstractSurvivorCard {

    public final static String ID = makeID("QuickThinking");

    //stupid intellij stuff SKILL, SELF, COMMON

    public QuickThinking() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new LowerCardCostPower(magicNumber));
        atb(new DrawCardAction(magicNumber));
        distUp(2);
        applyToSelf(new AdvantagePower(2));
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        if (DistancePanel.getPow() != DistancePanel.DPOW.CLOSE_RANGE) {
            cantUseMessage = "I'm not close enough!";
            return false;
        } else {
            return super.canUse(p, m);
        }
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
        }
    }
}