package theSurvivalist.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RemoveAllBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class NeedleShot extends AbstractSurvivorCard {

    public final static String ID = makeID("NeedleShot");

    //stupid intellij stuff SKILL, ENEMY, UNCOMMON


    public NeedleShot() {
        super(ID, 1, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = 12;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new RemoveAllBlockAction(m, p));
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
        applyToEnemy(m, autoVuln(m, 3));
        distUp(-1);
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(3);
        }
    }
}