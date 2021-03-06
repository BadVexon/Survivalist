package theSurvivalist.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theSurvivalist.SurvivalMod;
import theSurvivalist.util.TextureLoader;

import static theSurvivalist.SurvivalMod.getModID;

public class AdaptabilityPower extends AbstractPower implements CloneablePowerInterface {

    public static final String POWER_ID = SurvivalMod.makeID("AdaptabilityPower");

    private static final Texture tex84 = TextureLoader.getTexture("survmodResources/images/powers/" + POWER_ID.replaceAll(getModID() + ":", "") + "84.png");
    private static final Texture tex32 = TextureLoader.getTexture("survmodResources/images/powers/" + POWER_ID.replaceAll(getModID() + ":", "") + "32.png");


    public AdaptabilityPower(final int amount) {
        this.name = "Adaptability";
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
    public void onEvokeOrb(AbstractOrb orb) {
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(owner, amount));
    }

    @Override
    public void updateDescription() {
        if (amount == 1) {
            description = "Whenever a #yTrap is #yActivated, draw #b1 card.";
        } else
            description = "Whenever a #yTrap is #yActivated, draw #b" + amount + " cards.";
    }

    @Override
    public AbstractPower makeCopy() {
        return new AdaptabilityPower(amount);
    }
}