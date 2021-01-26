package theSurvivalist.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theSurvivalist.DistancePanel;

public class MercifulShot extends AbstractSurvivorCard {

    public final static String ID = makeID("MercifulShot");

    //stupid intellij stuff ATTACK, ENEMY, COMMON

    public MercifulShot() {
        super(ID, 2, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        exhaust = true;
        baseDamage = damage = 15;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (m.currentHealth >= m.maxHealth / 2) {
            addToBot(new LoseHPAction(m, p, m.maxHealth / 3));
        }else {
            dmg(m, AbstractGameAction.AttackEffect.FIRE);
        }
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        if (DistancePanel.getPow() != DistancePanel.DPOW.LONG_RANGE) {
            cantUseMessage = "I'm too close!";
            return false;
        } else {
            return super.canUse(p, m);
        }
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            selfRetain = true;
            rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}