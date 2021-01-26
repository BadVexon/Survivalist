package theSurvivalist.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.relics.OnApplyPowerRelic;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.watcher.MarkPower;
import theSurvivalist.SurvivalMod;
import theSurvivalist.util.TextureLoader;

import static theSurvivalist.SurvivalMod.makeRelicOutlinePath;
import static theSurvivalist.SurvivalMod.makeRelicPath;

public class SignalFlare extends CustomRelic implements OnApplyPowerRelic {

    public static final String ID = SurvivalMod.makeID("SignalFlare");
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("SignalFlare.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("SignalFlare.png"));

    public SignalFlare() {
        super(ID, IMG, OUTLINE, RelicTier.UNCOMMON, LandingSound.FLAT);
    }

    @Override
    public void atTurnStart() {
        counter = 0;
        grayscale = false;
    }

    @Override
    public void onVictory() {
        counter = -1;
        grayscale = false;
    }

    @Override
    public boolean onApplyPower(AbstractPower abstractPower, AbstractCreature target, AbstractCreature source) {
        if (abstractPower.ID.equals(MarkPower.POWER_ID) && !grayscale) {
            counter += abstractPower.amount;
            if (counter >= 10 && !grayscale) {
                flash();
                grayscale = true;
                counter = -1;
                addToBot(new GainEnergyAction(1));
            }
        }
        return true;
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
