package theSurvivalist.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import theSurvivalist.DistancePanel;
import theSurvivalist.SurvivalMod;
import theSurvivalist.util.TextureLoader;

import static theSurvivalist.SurvivalMod.makeRelicOutlinePath;
import static theSurvivalist.SurvivalMod.makeRelicPath;

public class RunningShoes extends CustomRelic {

    public static final String ID = SurvivalMod.makeID("RunningShoes");
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("RunningShoes.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("RunningShoes.png"));

    public RunningShoes() {
        super(ID, IMG, OUTLINE, RelicTier.COMMON, LandingSound.FLAT);
    }

    @Override
    public void atTurnStart() {
        grayscale = false;
    }

    @Override
    public void onTrigger() {
        flash();
        grayscale = true;
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
