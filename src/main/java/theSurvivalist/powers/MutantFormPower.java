package theSurvivalist.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theSurvivalist.SurvivalMod;
import theSurvivalist.actions.ReplayThisAction;
import theSurvivalist.cards.ComplexMutation;
import theSurvivalist.util.TextureLoader;
import static theSurvivalist.SurvivalMod.getModID;
public class MutantFormPower extends AbstractPower implements CloneablePowerInterface {

    public static final String POWER_ID = SurvivalMod.makeID("MutantFormPower");

    private static final Texture tex84 = TextureLoader.getTexture("survmodResources/images/powers/" + POWER_ID.replaceAll(getModID() + ":", "") + "84.png");
    private static final Texture tex32 = TextureLoader.getTexture("survmodResources/images/powers/" + POWER_ID.replaceAll(getModID() + ":", "") + "32.png");


    public MutantFormPower(final int amount) {
        this.name = "Mutant Form";
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
        addToBot(new ReplayThisAction(AbstractDungeon.getRandomMonster(), new ComplexMutation()));
    }

    @Override
    public void updateDescription() {
        if (amount == 1) {
            description = "At the start of your turn, play a #yComplex #yMutation on a random enemy.";
        } else
            description = "At the start of your turn, play #b" + amount + " #yComplex #yMutations on random enemies.";
    }

    @Override
    public AbstractPower makeCopy() {
        return new MutantFormPower(amount);
    }
}