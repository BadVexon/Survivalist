package theSurvivalist.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.actions.watcher.TriggerMarksAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.deprecated.DEPRECATEDPathToVictory;
import com.megacrit.cardcrawl.cards.purple.PressurePoints;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import theSurvivalist.SurvivalMod;
import theSurvivalist.actions.DistanceAction;
import theSurvivalist.util.TextureLoader;

import static theSurvivalist.SurvivalMod.makeRelicOutlinePath;
import static theSurvivalist.SurvivalMod.makeRelicPath;

public class GoodBoy extends CustomRelic {

    public static final String ID = SurvivalMod.makeID("GoodBoy");
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("GoodBoy.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("GoodBoy.png"));

    public GoodBoy() {
        super(ID, IMG, OUTLINE, RelicTier.SHOP, LandingSound.FLAT);
    }

    private boolean triggered = false;

    @Override
    public void atTurnStart() {
        triggered = false;
    }

    @Override
    public void onUseCard(AbstractCard targetCard, UseCardAction useCardAction) {
        if (targetCard.type == AbstractCard.CardType.ATTACK && !triggered) {
            triggered = true;
            addToBot(new TriggerMarksAction(CardLibrary.getCard(PressurePoints.ID)));
        }
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
