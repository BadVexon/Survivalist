package theSurvivalist.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theSurvivalist.DistancePanel;
import theSurvivalist.powers.NextTurnDistancePower;

public class CloseCall extends AbstractSurvivorCard {

    public final static String ID = makeID("CloseCall");

    //stupid intellij stuff SKILL, ENEMY, UNCOMMON

    public CloseCall() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        exhaust = true;
        baseBlock = 10;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        applyToSelf(new NextTurnDistancePower(3));
    }

    public void applyPowersToBlock() {
        int realBaseBlock = this.baseBlock;
        if (AbstractDungeon.player.currentHealth < AbstractDungeon.player.maxHealth) {
            baseBlock += (AbstractDungeon.player.maxHealth - AbstractDungeon.player.currentHealth);
        }
        super.applyPowersToBlock();
        baseBlock = realBaseBlock;
        isBlockModified = block != baseBlock;
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
            upgradeBaseCost(0);
        }
    }
}