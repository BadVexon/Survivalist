package theSurvivalist.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.ThornsPower;
import theSurvivalist.SurvivalMod;
import theSurvivalist.util.TextureLoader;
import static theSurvivalist.SurvivalMod.getModID;
public class LoseThornsPower extends AbstractPower implements CloneablePowerInterface {

    public static final String POWER_ID = SurvivalMod.makeID("LoseThornsPower");

    private static final Texture tex84 = TextureLoader.getTexture("survmodResources/images/powers/" + POWER_ID.replaceAll(getModID() + ":", "") + "84.png");
    private static final Texture tex32 = TextureLoader.getTexture("survmodResources/images/powers/" + POWER_ID.replaceAll(getModID() + ":", "") + "32.png");


    public LoseThornsPower(final int amount) {
        this.name = "Lose Thorns";
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

    @Override
    public void atStartOfTurnPostDraw() {
        this.flash();// 32
        addToBot(new ReducePowerAction(owner, owner, ThornsPower.POWER_ID, amount));
        addToBot(new RemoveSpecificPowerAction(owner, owner, this));
    }

    @Override
    public void updateDescription() {
        description = "Next turn, lose #b" + amount + " #yThorns.";
    }

    @Override
    public AbstractPower makeCopy() {
        return new LoseThornsPower(amount);
    }
}