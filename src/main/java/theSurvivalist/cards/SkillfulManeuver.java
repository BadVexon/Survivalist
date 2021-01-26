package theSurvivalist.cards;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.cards.status.Dazed;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.EnergizedBluePower;

public class SkillfulManeuver extends AbstractSurvivorCard {

    public final static String ID = makeID("SkillfulManeuver");

    //stupid intellij stuff SKILL, SELF, COMMON

    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 1;

    public SkillfulManeuver() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
        cardsToPreview = new Dazed();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        distUp(2);
        applyToSelf(new EnergizedBluePower(p, magicNumber));
        atb(new MakeTempCardInDiscardAction(new Dazed(), 2));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPG_MAGIC);
            rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}