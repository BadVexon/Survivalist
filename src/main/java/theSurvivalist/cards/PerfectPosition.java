package theSurvivalist.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theSurvivalist.DistancePanel;

public class PerfectPosition extends AbstractSurvivorCard {

    public final static String ID = makeID("PerfectPosition");

    //stupid intellij stuff SKILL, ENEMY, UNCOMMON

    public PerfectPosition() {
        super(ID, 1, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = 10;
        baseBlock = 5;
        exhaust = true;
        isEthereal = true;
        //cardsToPreview = new PerfectPosition();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SMASH);
        blck();
        AbstractCard c = this.makeCopy();
        if (upgraded) c.upgrade();
        makeInHand(c);
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        if (DistancePanel.getPow() == DistancePanel.DPOW.CLOSE_RANGE) {
            cantUseMessage = "I'm too close!";
            return false;
        } else {
            return super.canUse(p, m);
        }
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            isEthereal = false;
            rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}