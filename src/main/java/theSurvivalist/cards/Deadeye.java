package theSurvivalist.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PhantasmalPower;
import theSurvivalist.CardIgnore;
import theSurvivalist.DistancePanel;

@CardIgnore
public class Deadeye extends AbstractSurvivorCard {

    public final static String ID = makeID("Deadeye");

    //stupid intellij stuff SKILL, SELF, COMMON

    public Deadeye() {
        super(ID, 3, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (DistancePanel.getPow() == DistancePanel.DPOW.LONG_RANGE) {
            applyToSelf(new PhantasmalPower(p, 1));
        }
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(2);
        }
    }
}