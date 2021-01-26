package theSurvivalist.cards;

import com.megacrit.cardcrawl.cards.status.Slimed;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class UnfortunateEscape extends AbstractSurvivorCard {

    public final static String ID = makeID("UnfortunateEscape");

    //stupid intellij stuff SKILL, SELF, COMMON

    private static final int BLOCK = 10;
    private static final int UPG_BLOCK = 2;

    public UnfortunateEscape() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = BLOCK;
        cardsToPreview = new Slimed();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        makeInHand(new Slimed(), 2);
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(UPG_BLOCK);
        }
    }
}