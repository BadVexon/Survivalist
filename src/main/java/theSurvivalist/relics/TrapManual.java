package theSurvivalist.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.relics.OnChannelRelic;
import com.megacrit.cardcrawl.actions.defect.IncreaseMaxOrbAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import theSurvivalist.SurvivalMod;
import theSurvivalist.actions.DistanceAction;
import theSurvivalist.traps.AbstractTrap;
import theSurvivalist.util.TextureLoader;

import static theSurvivalist.SurvivalMod.makeRelicOutlinePath;
import static theSurvivalist.SurvivalMod.makeRelicPath;

public class TrapManual extends CustomRelic implements OnChannelRelic  {

    public static final String ID = SurvivalMod.makeID("TrapManual");
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("TrapManual.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("TrapManual.png"));

    public TrapManual() {
        super(ID, IMG, OUTLINE, RelicTier.COMMON, LandingSound.FLAT);
        counter = -1;
    }

    @Override
    public void atBattleStart() {
        counter = 0;
    }

    @Override
    public void onChannel(AbstractOrb abstractOrb) {
        if (abstractOrb instanceof AbstractTrap) {
            counter++;
            if (counter == 3) {
                flash();
                addToBot(new IncreaseMaxOrbAction(1));
                counter = 0;
            }
        }
    }

    @Override
    public void onVictory() {
        counter = -1;
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
