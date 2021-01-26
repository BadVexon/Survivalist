package theSurvivalist.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;
import com.megacrit.cardcrawl.powers.IntangiblePower;
import theSurvivalist.DistancePanel;

public class FindCover extends AbstractSurvivorCard {

    public final static String ID = makeID("FindCover");

    //stupid intellij stuff SKILL, SELF, COMMON

    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 1;

    public FindCover() {
        super(ID, 2, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new IntangiblePlayerPower(p, magicNumber));
        for (AbstractMonster q : AbstractDungeon.getCurrRoom().monsters.monsters)
            applyToEnemy(q, new IntangiblePower(q, 1));
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
            upgradeMagicNumber(UPG_MAGIC);
        }
    }
}