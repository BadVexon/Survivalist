package theSurvivalist.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theSurvivalist.SurvivalMod;
import theSurvivalist.util.TextureLoader;
import static theSurvivalist.SurvivalMod.getModID;
public class NextTurnNoDistancePowerPower extends AbstractPower implements CloneablePowerInterface {

    public static final String POWER_ID = SurvivalMod.makeID("NextTurnNoDistancePowerPower");

    private static final Texture tex84 = TextureLoader.getTexture("survmodResources/images/powers/" + POWER_ID.replaceAll(getModID() + ":", "") + "84.png");
    private static final Texture tex32 = TextureLoader.getTexture("survmodResources/images/powers/" + POWER_ID.replaceAll(getModID() + ":", "") + "32.png");


    public NextTurnNoDistancePowerPower(final int amount) {
        this.name = "No Distance Next Turn";
        this.ID = POWER_ID;
        this.owner = AbstractDungeon.player;
        this.amount = amount;
        this.type = PowerType.BUFF;
        this.isTurnBased = false;
        this.canGoNegative = false;

        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        this.updateDescription();
    }

    public void atEndOfTurn(boolean isPlayer) {
        this.flash();// 79
        this.addToBot(new ApplyPowerAction(this.owner, this.owner, new NoDistancePower(this.amount), this.amount));// 80
        this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, ID));// 81
    }

    @Override
    public void updateDescription() {
        description = "Next turn, you cannot gain Distance.";
    }

    @Override
    public AbstractPower makeCopy() {
        return new NextTurnNoDistancePowerPower(amount);
    }
}