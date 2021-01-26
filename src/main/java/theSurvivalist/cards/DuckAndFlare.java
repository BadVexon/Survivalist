package theSurvivalist.cards;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class DuckAndFlare extends AbstractSurvivorCard {

    public final static String ID = makeID("DuckAndFlare");

    //stupid intellij stuff ATTACK, ENEMY, COMMON

    private static final int BLOCK = 5;
    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 1;

    public DuckAndFlare() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = BLOCK;
        baseMagicNumber = magicNumber = MAGIC;
        cardsToPreview = new Flare();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        atb(new MakeTempCardInHandAction(new Flare(), magicNumber));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPG_MAGIC);
            rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}