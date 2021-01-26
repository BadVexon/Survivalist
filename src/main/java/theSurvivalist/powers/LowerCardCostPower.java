package theSurvivalist.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theSurvivalist.SurvivalMod;
import theSurvivalist.util.TextureLoader;

import static theSurvivalist.SurvivalMod.getModID;

public class LowerCardCostPower extends AbstractPower implements CloneablePowerInterface {

    public static final String POWER_ID = SurvivalMod.makeID("MuddleDrawnCardsPower");

    private static final Texture tex84 = TextureLoader.getTexture("survmodResources/images/powers/" + POWER_ID.replaceAll(getModID() + ":", "") + "84.png");
    private static final Texture tex32 = TextureLoader.getTexture("survmodResources/images/powers/" + POWER_ID.replaceAll(getModID() + ":", "") + "32.png");


    public LowerCardCostPower(final int amount) {
        this.name = "Reduce Drawn Card Cost";
        this.ID = POWER_ID;
        this.owner = AbstractDungeon.player;
        this.amount = amount;
        this.type = PowerType.BUFF;
        this.isTurnBased = true;

        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        this.updateDescription();
    }

    @Override
    public void onCardDraw(AbstractCard card) {
        this.amount -= 1;
        if (amount >= 0 && card.costForTurn > 0 && !card.freeToPlay()) {
            flash();
            card.costForTurn -= 1;
            this.updateDescription();
            if (amount == 0)
                addToTop(new RemoveSpecificPowerAction(owner, owner, this));
        } else {
            addToTop(new RemoveSpecificPowerAction(owner, owner, this));
        }
    }

    @Override
    public void updateDescription() {
        if (amount == 1)
            description = "Reduce the cost of the next card you draw by #b1 this turn.";
        else
            description = "Reduce the cost of the next #b" + amount + " cards you draw by #b1 this turn.";
    }

    @Override
    public AbstractPower makeCopy() {
        return new LowerCardCostPower(amount);
    }
}