package theSurvivalist.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theSurvivalist.DistancePanel;
import theSurvivalist.actions.DistanceAction;
import theSurvivalist.actions.XCostAction;
import theSurvivalist.powers.NextTurnNoDistancePowerPower;

public class DesperateAmbush extends AbstractSurvivorCard {

    public final static String ID = makeID("DesperateAmbush");

    //stupid intellij stuff SKILL, SELF, COMMON

    public DesperateAmbush() {
        super(ID, -1, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = -1;
    }

    @Override
    public void applyPowers() {
        baseDamage = DistancePanel.distance;
        super.applyPowers();
        rawDescription = (upgraded ? UPGRADE_DESCRIPTION : DESCRIPTION) + " NL (Deals !D! damage.)";
        initializeDescription();
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        baseDamage = DistancePanel.distance;
        super.applyPowers();
        rawDescription = (upgraded ? UPGRADE_DESCRIPTION : DESCRIPTION) + " NL (Deals !D! damage.)";
        initializeDescription();
    }

    @Override
    public void onMoveToDiscard() {
        rawDescription = (upgraded ? UPGRADE_DESCRIPTION : DESCRIPTION);
        initializeDescription();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new XCostAction(this, (effect, params) -> {
            int x = effect;
            if (upgraded) x *= 2;
            for (int i = 0; i < x; ++i) {
                dmg(m, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
            }
            addToBot(new DistanceAction(-DistancePanel.distance));
            applyToSelf(new NextTurnNoDistancePowerPower(1));
            return true;
        }));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}