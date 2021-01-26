package theSurvivalist.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theSurvivalist.SurvivalMod;
import theSurvivalist.util.TextureLoader;

import static theSurvivalist.SurvivalMod.makeRelicOutlinePath;
import static theSurvivalist.SurvivalMod.makeRelicPath;

public class AdvancedEvolution extends CustomRelic {

    public static final String ID = SurvivalMod.makeID("AdvancedEvolution");
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("AdvancedEvolution.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("AdvancedEvolution.png"));

    public AdvancedEvolution() {
        super(ID, IMG, OUTLINE, RelicTier.BOSS, LandingSound.FLAT);
    }

    @Override
    public void atTurnStart() {
        grayscale = false;
    }

    @Override
    public void onCardDraw(AbstractCard drawnCard) {
        if ((drawnCard.type == AbstractCard.CardType.STATUS || drawnCard.type == AbstractCard.CardType.CURSE)) {
            flash();
            addToBot(new DrawCardAction(1));
            if (!grayscale) {
                grayscale = true;
                addToBot(new GainEnergyAction(1));
            }
        }
    }

    @Override
    public void obtain() {
        if (AbstractDungeon.player.hasRelic(InnateMutation.ID)) {
            for (int i = 0; i < AbstractDungeon.player.relics.size(); ++i) {
                if (AbstractDungeon.player.relics.get(i).relicId.equals(InnateMutation.ID)) {
                    instantObtain(AbstractDungeon.player, i, true);
                    break;
                }
            }
        } else {
            super.obtain();
        }
    }

    @Override
    public boolean canSpawn() {
        return AbstractDungeon.player.hasRelic(InnateMutation.ID);
    }

    @Override
    public String getUpdatedDescription() {
        // Colorize the starter relic's name
        String name = new InnateMutation().name;
        StringBuilder sb = new StringBuilder();
        for (String word : name.split(" ")) {
            sb.append("[#").append(SurvivalMod.chemColor.toString()).append("]").append(word).append("[] ");
        }
        sb.setLength(sb.length() - 1);
        sb.append("[#").append(SurvivalMod.chemColor.toString()).append("]");

        return DESCRIPTIONS[0] + sb.toString() + DESCRIPTIONS[1];
    }
}
