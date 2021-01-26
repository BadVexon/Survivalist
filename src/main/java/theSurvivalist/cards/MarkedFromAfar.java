package theSurvivalist.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.watcher.TriggerMarksAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theSurvivalist.DistancePanel;

public class MarkedFromAfar extends AbstractSurvivorCard {

    public final static String ID = makeID("MarkedFromAfar");

    //stupid intellij stuff ATTACK, ENEMY, UNCOMMON

    private static final int DAMAGE = 1;

    public MarkedFromAfar() {
        super(ID, 0, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.FIRE);
        atb(new TriggerMarksAction(this));
        atb(new TriggerMarksAction(this));
        distUp(-1);
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        cantUseMessage = "I'm too close!";
        if (DistancePanel.getPow() == DistancePanel.DPOW.CLOSE_RANGE) {
            return false;
        } else if (DistancePanel.getPow() == DistancePanel.DPOW.MID_RANGE) {
            if (upgraded)
                return super.canUse(p, m);
            else
                return false;
        } else
            return true;
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}