package theSurvivalist.cards;

import com.evacipated.cardcrawl.mod.stslib.actions.common.StunMonsterAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.EntanglePower;
import theSurvivalist.DistancePanel;

public class ChokingBow extends AbstractSurvivorCard {

    public final static String ID = makeID("ChokingBow");

    //stupid intellij stuff ATTACK, ENEMY, RARE

    public ChokingBow() {
        super(ID, 2, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = 8;
        exhaust = true;
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        if (DistancePanel.getPow() != DistancePanel.DPOW.CLOSE_RANGE) {
            cantUseMessage = "I'm not close enough!";
            return false;
        } else {
            return super.canUse(p, m);
        }
    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
        atb(new StunMonsterAction(m, p));
        applyToSelf(new EntanglePower(p));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(1);
        }
    }
}