package theSurvivalist.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theSurvivalist.DistancePanel;
import theSurvivalist.powers.CamouflagePower;

public class Camouflage extends AbstractSurvivorCard {

    public final static String ID = makeID("Camouflage");

    //stupid intellij stuff POWER, SELF, UNCOMMON


    public Camouflage() {
        super(ID, 3, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new CamouflagePower(magicNumber));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(2);
        }
    }
}