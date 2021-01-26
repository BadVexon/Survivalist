package theSurvivalist.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theSurvivalist.SurvivalMod;
import theSurvivalist.util.TextureLoader;
import static theSurvivalist.SurvivalMod.getModID;
public class MutantPower extends AbstractPower implements CloneablePowerInterface {

    public static final String POWER_ID = SurvivalMod.makeID("MutantPower");

    private static final Texture tex84 = TextureLoader.getTexture("survmodResources/images/powers/" + POWER_ID.replaceAll(getModID() + ":", "") + "84.png");
    private static final Texture tex32 = TextureLoader.getTexture("survmodResources/images/powers/" + POWER_ID.replaceAll(getModID() + ":", "") + "32.png");


    public MutantPower(final int amount) {
        this.name = "Mutant Energy";
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
    public void onCardDraw(AbstractCard card) {
        if (card.color == AbstractCard.CardColor.CURSE || card.type == AbstractCard.CardType.STATUS || card.type == AbstractCard.CardType.CURSE) {
            flash();
            addToBot(new GainEnergyAction(amount));
        }
    }

    @Override
    public void updateDescription() {
        description = "Whenever you draw a #yCurse or #yStatus card, gain #b" + amount + " [E] .";
    }

    @Override
    public AbstractPower makeCopy() {
        return new MutantPower(amount);
    }
}