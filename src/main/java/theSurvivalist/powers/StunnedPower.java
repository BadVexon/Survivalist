package theSurvivalist.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.cardManip.ExhaustCardEffect;
import static theSurvivalist.SurvivalMod.getModID;
public class StunnedPower extends AbstractPower {
    public static final String POWER_ID = "survmod:StunnedPower";

    public StunnedPower(AbstractCreature owner, int amount) {
        this.name = "Stunned!";
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        updateDescription();
        this.type = PowerType.DEBUFF;
        this.isTurnBased = true;
        this.loadRegion("entangle");
    }

    @Override
    public void atEndOfRound() {
        if (this.amount == 0) {
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
        } else {
            AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(this.owner, this.owner, POWER_ID, 1));
        }
    }

    @Override
    public void atStartOfTurnPostDraw() {
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                AbstractDungeon.actionManager.cardQueue.clear();
                for (final AbstractCard c : AbstractDungeon.player.limbo.group) {
                    AbstractDungeon.effectList.add(new ExhaustCardEffect(c));
                }
                AbstractDungeon.player.limbo.group.clear();
                AbstractDungeon.player.releaseCard();
                AbstractDungeon.overlayMenu.endTurnButton.disable(true);
            }
        });
    }

    @Override
    public void updateDescription() {
        this.description = "Skip your next turn.";
    }
}