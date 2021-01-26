package theSurvivalist.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theSurvivalist.powers.FlareQuiverPower;

public class FlareQuiver extends AbstractSurvivorCard {

    public final static String ID = makeID("FlareQuiver");

    //stupid intellij stuff POWER, UNCOMMON, SELF

    public FlareQuiver() {
        super(ID, 2, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new FlareQuiverPower(4));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(1);
        }
    }
}