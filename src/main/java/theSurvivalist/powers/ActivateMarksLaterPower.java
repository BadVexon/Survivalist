package theSurvivalist.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.watcher.MarkPower;
import theSurvivalist.SurvivalMod;
import theSurvivalist.util.TextureLoader;

import static theSurvivalist.SurvivalMod.getModID;

public class ActivateMarksLaterPower extends AbstractPower implements CloneablePowerInterface {

    public static final String POWER_ID = SurvivalMod.makeID("ActivateMarksLaterPower");

    private static final Texture tex84 = TextureLoader.getTexture("survmodResources/images/powers/" + POWER_ID.replaceAll(getModID() + ":", "") + "84.png");
    private static final Texture tex32 = TextureLoader.getTexture("survmodResources/images/powers/" + POWER_ID.replaceAll(getModID() + ":", "") + "32.png");

    private boolean justApplied = true;

    public ActivateMarksLaterPower(final AbstractCreature owner, final int amount) {
        this.name = "Activate Marks Later!";
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.BUFF;
        this.isTurnBased = false;
        this.canGoNegative = false;

        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        this.updateDescription();
    }


    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (justApplied) {
            justApplied = false;
        }
        else if (owner.hasPower(MarkPower.POWER_ID)) {
            int x = owner.getPower(MarkPower.POWER_ID).amount * amount;
            flash();
            addToBot(new LoseHPAction(owner, owner, x));
            addToBot(new RemoveSpecificPowerAction(owner, owner, POWER_ID));
        }
    }

    @Override
    public void updateDescription() {
        if (amount == 1) {
            description = "Next turn, at the end of its turn, this enemy loses HP equal to its #yMark.";
        } else
            description = "Next turn, at the end of its turn, this enemy loses HP equal to #b" + amount + " times its #yMark.";
    }

    @Override
    public AbstractPower makeCopy() {
        return new ActivateMarksLaterPower(owner, amount);
    }
}