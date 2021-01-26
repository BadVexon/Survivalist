package theSurvivalist.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.watcher.MarkPower;
import theSurvivalist.DistancePanel;
import theSurvivalist.SurvivalMod;
import theSurvivalist.util.TextureLoader;
import static theSurvivalist.SurvivalMod.getModID;
public class FlaredArrowheadsPower extends AbstractPower implements CloneablePowerInterface {

    public static final String POWER_ID = SurvivalMod.makeID("FlaredArrowheadsPower");

    private static final Texture tex84 = TextureLoader.getTexture("survmodResources/images/powers/" + POWER_ID.replaceAll(getModID() + ":", "") + "84.png");
    private static final Texture tex32 = TextureLoader.getTexture("survmodResources/images/powers/" + POWER_ID.replaceAll(getModID() + ":", "") + "32.png");


    public FlaredArrowheadsPower(final int amount) {
        this.name = "Flared Arrowheads";
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

    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        if (damageAmount > 0 && target != this.owner && info.type == DamageInfo.DamageType.NORMAL && DistancePanel.getPow() == DistancePanel.DPOW.LONG_RANGE) {// 32
            this.flash();// 33
            this.addToTop(new ApplyPowerAction(target, this.owner, new MarkPower(target, this.amount), this.amount, true));// 34
        }

    }// 36

    @Override
    public void onEvokeOrb(AbstractOrb orb) {
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(owner, 1));
    }

    @Override
    public void updateDescription() {
        description = "Whenever an #yAttack deals unblocked damage in Long Range, apply #b" + amount + " #yMark.";
    }

    @Override
    public AbstractPower makeCopy() {
        return new FlaredArrowheadsPower(amount);
    }
}