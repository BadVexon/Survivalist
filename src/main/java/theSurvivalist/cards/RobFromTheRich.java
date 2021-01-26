package theSurvivalist.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainGoldAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theSurvivalist.DistancePanel;

public class RobFromTheRich extends AbstractSurvivorCard {

    public final static String ID = makeID("RobFromTheRich");

    //stupid intellij stuff ATTACK, ENEMY, UNCOMMON

    public RobFromTheRich() {
        super(ID, 1, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = 8;
        baseMagicNumber = magicNumber = 5;
        exhaust = true;
    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.FIRE);
        int x = (damage - m.currentBlock) * magicNumber;
        atb(new GainGoldAction(x));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(5);
        }
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

}