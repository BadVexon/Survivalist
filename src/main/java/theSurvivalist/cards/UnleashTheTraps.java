package theSurvivalist.cards;

import com.megacrit.cardcrawl.actions.defect.EvokeAllOrbsAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theSurvivalist.DistancePanel;

public class UnleashTheTraps extends AbstractSurvivorCard {

    public final static String ID = makeID("UnleashTheTraps");

    //stupid intellij stuff SKILL, SELF, COMMON

    public UnleashTheTraps() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        isEthereal = true;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new EvokeAllOrbsAction());
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            exhaust = false;
            rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}