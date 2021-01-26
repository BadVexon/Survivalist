package theSurvivalist.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.BranchingUpgradesCard;

public class RejectWeakness extends AbstractSurvivorCard {

    public final static String ID = makeID("RejectWeakness");

    //stupid intellij stuff SKILL, SELF, UNCOMMON

    public RejectWeakness() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (AbstractCard q : p.hand.group) {
            if (q.type == CardType.STATUS || q.type == CardType.CURSE || q.color == CardColor.CURSE) {
                atb(new ExhaustSpecificCardAction(q, p.hand));
                atb(new DrawCardAction(1));
            }
        }
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(0);
        }
    }
}