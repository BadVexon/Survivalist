package theSurvivalist.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theSurvivalist.SurvivalMod;

public class FakeFlightPower extends AbstractPower implements CloneablePowerInterface {

    public static final String POWER_ID = SurvivalMod.makeID("FakeFlightPower");

    public FakeFlightPower(final int amount) {
        this.name = "Flight";
        this.ID = POWER_ID;
        this.owner = AbstractDungeon.player;
        this.amount = amount;
        this.type = PowerType.BUFF;
        this.isTurnBased = false;
        this.canGoNegative = false;

        loadRegion("flight");
        priority = 50;

        this.updateDescription();
    }

    public void playApplyPowerSfx() {
        CardCrawlGame.sound.play("POWER_FLIGHT", 0.05F);
    }

    @Override
    public float modifyBlock(float blockAmount) {
        return 0;
    }

    public int onAttacked(DamageInfo info, int damageAmount) {
        if (info.owner != null && info.type != DamageInfo.DamageType.HP_LOSS && info.type != DamageInfo.DamageType.THORNS) {
            this.flash();
            this.addToBot(new ReducePowerAction(this.owner, this.owner, this.ID, 1));
        }

        return damageAmount;
    }

    @Override
    public void updateDescription() {
        description = "You don't lose #yDistance at the start of your turn and can't gain #yBlock. NL Takes #b50% less damage. NL Removed after taking #b" + amount + " more hits.";
    }

    @Override
    public AbstractPower makeCopy() {
        return new FakeFlightPower(amount);
    }
}