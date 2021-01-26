package theSurvivalist.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.status.Dazed;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theSurvivalist.DistancePanel;

public class BatteringStrike extends AbstractSurvivorCard {

    public final static String ID = makeID("BatteringStrike");

    //stupid intellij stuff SKILL, ENEMY, UNCOMMON

    public BatteringStrike() {
        super(ID, 1, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = 15;
        baseBlock = 5;
        tags.add(CardTags.STRIKE);
        cardsToPreview = new Dazed();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SMASH);
        blck();
        distUp(2);
        shuffleIn(new Dazed(), 3);
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


    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(2);
            upgradeBlock(2);
        }
    }
}