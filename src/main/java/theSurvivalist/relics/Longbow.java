package theSurvivalist.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theSurvivalist.SurvivalMod;
import theSurvivalist.actions.DistanceAction;
import theSurvivalist.util.TextureLoader;

import static theSurvivalist.SurvivalMod.makeRelicOutlinePath;
import static theSurvivalist.SurvivalMod.makeRelicPath;

public class Longbow extends CustomRelic {

    public static final String ID = SurvivalMod.makeID("Longbow");
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("Longbow.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("Longbow.png"));

    public Longbow() {
        super(ID, IMG, OUTLINE, RelicTier.RARE, LandingSound.FLAT);
    }

    @Override
    public void atBattleStart() {
        AbstractDungeon.actionManager.addToBottom(new DistanceAction(1));
    }

    public void onLongRange() {
        addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, 3), 3));
    }

    public void onExitLongRange() {
        addToBot(new ReducePowerAction(AbstractDungeon.player, AbstractDungeon.player, StrengthPower.POWER_ID, 3));
    }

    public void onCloseRange() {
        addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, -1), -1));
    }

    public void onExitCloseRange() {
        addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, 1), 1));
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
