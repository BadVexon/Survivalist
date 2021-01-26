package theSurvivalist.cards;

import com.megacrit.cardcrawl.cards.status.Wound;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theSurvivalist.actions.PlayAttacksAction;
import theSurvivalist.powers.NextTurnNoDistancePowerPower;

public class EmptyQuiver extends AbstractSurvivorCard {

    public final static String ID = makeID("EmptyQuiver");

    //stupid intellij stuff POWER, SELF, UNCOMMON

    private static final int MAGIC = 3;
    private static final int UPG_MAGIC = -1;

    public EmptyQuiver() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
        cardsToPreview = new Wound();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new PlayAttacksAction());
        shuffleIn(new Wound(), magicNumber);
        applyToSelf(new NextTurnNoDistancePowerPower(1));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPG_MAGIC);
        }
    }
}