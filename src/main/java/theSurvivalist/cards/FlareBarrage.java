package theSurvivalist.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.watcher.MarkPower;
import theSurvivalist.actions.XCostAction;

public class FlareBarrage extends AbstractSurvivorCard {

    public final static String ID = makeID("FlareBarrage");

    //stupid intellij stuff SKILL, SELF, COMMON

    public FlareBarrage() {
        super(ID, -1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.SELF);
        baseDamage = 2;
        baseMagicNumber = magicNumber = 8;
        isMultiDamage = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new XCostAction(this, (effect, params) -> {
            for (int i = 0; i < effect; ++i) {
                allDmg(AbstractGameAction.AttackEffect.FIRE);
                for (AbstractMonster q : monsterList()) {
                    applyToEnemy(q, new MarkPower(q, magicNumber));
                }
            }
            distUp(-effect);
            return true;
        }));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(1);
            upgradeMagicNumber(2);
        }
    }
}