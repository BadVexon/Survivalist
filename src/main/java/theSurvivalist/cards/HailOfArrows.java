package theSurvivalist.cards;

import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theSurvivalist.actions.XCostAction;

public class HailOfArrows extends AbstractSurvivorCard {

    public final static String ID = makeID("HailOfArrows");

    //stupid intellij stuff SKILL, SELF, COMMON

    public HailOfArrows() {
        super(ID, -1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        baseDamage = 9;
        baseMagicNumber = magicNumber = 0;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new XCostAction(this, (effect, params) -> {
            for (int i = 0; i < effect + params[0]; ++i) {
                AbstractDungeon.actionManager.addToTop(new AttackDamageRandomEnemyAction(this));
            }
            distUp(-1);
            return true;
        }, magicNumber));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
            rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}