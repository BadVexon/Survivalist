package theSurvivalist.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import theSurvivalist.SurvivalMod;
import theSurvivalist.util.TextureLoader;

import static theSurvivalist.SurvivalMod.makeRelicOutlinePath;
import static theSurvivalist.SurvivalMod.makeRelicPath;

public class InnateMutation extends CustomRelic {

    public static final String ID = SurvivalMod.makeID("InnateMutation");
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("InnateMutation.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("InnateMutation.png"));

    public InnateMutation() {
        super(ID, IMG, OUTLINE, RelicTier.STARTER, LandingSound.FLAT);
    }

    @Override
    public void atTurnStart() {
        grayscale = false;
    }

    @Override
    public void onCardDraw(AbstractCard drawnCard) {
        if ((drawnCard.type == AbstractCard.CardType.STATUS || drawnCard.type == AbstractCard.CardType.CURSE) && !grayscale) {
            flash();
            grayscale = true;
            addToBot(new DrawCardAction(1));
        }
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
