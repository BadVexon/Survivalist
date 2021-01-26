package theSurvivalist.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.cards.curses.Injury;
import com.megacrit.cardcrawl.cards.curses.Pain;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;

public class SimpleMutation extends AbstractSurvivorCard {

    public final static String ID = makeID("SimpleMutation");

    //stupid intellij stuff ATTACK, ENEMY, COMMON

    private static final int DAMAGE = 15;
    private static final int BLOCK = 12;

    public SimpleMutation() {
        super(ID, 2, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        baseBlock = BLOCK;
        baseMagicNumber = magicNumber = 1;
        cardsToPreview = new Injury();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (m.getIntentBaseDmg() > -1) {
            blck();
        } else {
            dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
            applyToSelf(new ArtifactPower(p, magicNumber));
        }
        addToBot(new MakeTempCardInDiscardAction(new Injury(), 1));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(4);
            upgradeMagicNumber(1);
        }
    }
}