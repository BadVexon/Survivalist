package theSurvivalist.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theSurvivalist.SurvivalMod;
import theSurvivalist.actions.DistanceAction;
import theSurvivalist.util.TextureLoader;

import static theSurvivalist.SurvivalMod.getModID;

public class EscapeMechanismPower extends AbstractPower implements CloneablePowerInterface {

    public static final String POWER_ID = SurvivalMod.makeID("EscapeMechanismPower");

    private static final Texture tex84 = TextureLoader.getTexture("survmodResources/images/powers/" + POWER_ID.replaceAll(getModID() + ":", "") + "84.png");
    private static final Texture tex32 = TextureLoader.getTexture("survmodResources/images/powers/" + POWER_ID.replaceAll(getModID() + ":", "") + "32.png");


    public EscapeMechanismPower(final int amount) {
        this.name = "Escape Mechanism";
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
        AbstractDungeon.actionManager.addToBottom(new DistanceAction(amount));
    }

    @Override
    public void updateDescription() {
        description = "Whenever a #yTrap is #yActivated, gain #b" + amount + " Distance.";
    }

    @Override
    public AbstractPower makeCopy() {
        return new EscapeMechanismPower(amount);
    }
}