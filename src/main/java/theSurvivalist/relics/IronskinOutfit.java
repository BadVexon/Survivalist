package theSurvivalist.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.relics.OnApplyPowerRelic;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import com.megacrit.cardcrawl.powers.watcher.MarkPower;
import org.lwjgl.opencl.CL;
import theSurvivalist.DistancePanel;
import theSurvivalist.SurvivalMod;
import theSurvivalist.util.TextureLoader;

import static theSurvivalist.SurvivalMod.makeRelicOutlinePath;
import static theSurvivalist.SurvivalMod.makeRelicPath;

public class IronskinOutfit extends CustomRelic {

    public static final String ID = SurvivalMod.makeID("IronskinOutfit");
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("IronskinOutfit.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("IronskinOutfit.png"));

    public IronskinOutfit() {
        super(ID, IMG, OUTLINE, RelicTier.RARE, LandingSound.FLAT);
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        if (info.type == DamageInfo.DamageType.NORMAL && info.owner != null && info.owner != AbstractDungeon.player && damageAmount > 0) {
            if (DistancePanel.getPow() == DistancePanel.DPOW.CLOSE_RANGE) {
                flash();
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new PlatedArmorPower(AbstractDungeon.player, 5), 5));
            }
        }
        return damageAmount;
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
