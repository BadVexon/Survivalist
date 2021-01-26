package theSurvivalist.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.cards.status.Dazed;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Sprint extends AbstractSurvivorCard {

    public final static String ID = makeID("Sprint");

    //stupid intellij stuff SKILL, SELF, BASIC

    public Sprint() {
        super(ID, 1, CardType.SKILL, CardRarity.BASIC, CardTarget.SELF);
        cardsToPreview = new Dazed();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        distUp(3);
        atb(new DrawCardAction(p, 1));
        atb(new MakeTempCardInDiscardAction(new Dazed(), 1));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(0);
        }
    }
}