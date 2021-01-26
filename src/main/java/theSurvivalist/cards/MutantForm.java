package theSurvivalist.cards;

import com.megacrit.cardcrawl.actions.animations.AnimateJumpAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theSurvivalist.powers.FakeFlightPower;

public class MutantForm extends AbstractSurvivorCard {

    public final static String ID = makeID("MutantForm");

    //stupid intellij stuff POWER, SELF, UNCOMMON

    public MutantForm() {
        super(ID, 3, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        isEthereal = true;
        baseMagicNumber = magicNumber = 3;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new AnimateJumpAction(p));
        applyToSelf(new FakeFlightPower(magicNumber));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
        }
    }
}