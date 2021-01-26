package theSurvivalist.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.BranchingUpgradesCard;
import theSurvivalist.powers.HawkeyePower;

import java.util.HashMap;

public class Hawkeye extends AbstractSurvivorCard {

    public static HashMap<AbstractMonster, Integer> myList = new HashMap<>();

    public final static String ID = makeID("Hawkeye");

    //stupid intellij stuff SKILL, SELF, UNCOMMON

    public Hawkeye() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        myList.clear();
        applyToSelf(new HawkeyePower(1));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(0);
        }
    }
}