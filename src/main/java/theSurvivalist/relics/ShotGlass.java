package theSurvivalist.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theSurvivalist.SurvivalMod;
import theSurvivalist.actions.DistanceAction;
import theSurvivalist.util.TextureLoader;

import static theSurvivalist.SurvivalMod.makeRelicOutlinePath;
import static theSurvivalist.SurvivalMod.makeRelicPath;

public class ShotGlass extends CustomRelic {

    public static final String ID = SurvivalMod.makeID("ShotGlass");
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("ShotGlass.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("ShotGlass.png"));

    public ShotGlass() {
        super(ID, IMG, OUTLINE, RelicTier.UNCOMMON, LandingSound.FLAT);
    }

    @Override
    public float atDamageModify(float damage, AbstractCard c) {
        return c.name.toLowerCase().contains("shot") ? damage + 3.0F : damage;// 20 21
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
