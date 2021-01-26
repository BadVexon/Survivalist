package theSurvivalist.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.watcher.MarkPower;

public class Flare extends AbstractSurvivorCard {

    public final static String ID = makeID("Flare");

    //stupid intellij stuff SKILL, ENEMY, SPECIAL

    private static final int MAGIC = 4;
    private static final int UPG_MAGIC = 2;

    public Flare() {
        super(ID, 0, CardType.SKILL, CardRarity.SPECIAL, CardTarget.ENEMY, CardColor.COLORLESS);
        baseMagicNumber = magicNumber = MAGIC;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        markEnemy(m, magicNumber);
        atb(new DrawCardAction(p, 1));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPG_MAGIC);
        }
    }
}