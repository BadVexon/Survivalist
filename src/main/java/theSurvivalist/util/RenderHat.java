package theSurvivalist.util;

import basemod.animations.SpriterAnimation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import javassist.CtBehavior;
import theSurvivalist.TheSurvivor;
import theSurvivalist.relics.Longbow;


@SpirePatch(
        clz = AbstractPlayer.class,
        method = "render"
)
public class RenderHat {
    private static SpriterAnimation cat = new SpriterAnimation("survmodResources/images/char/mainChar/artist2.scml");

    @SpireInsertPatch(
            locator = Locator.class
    )
    public static SpireReturn renderAlt(AbstractPlayer __instance, SpriteBatch sb) {
        if (AbstractDungeon.player instanceof TheSurvivor && AbstractDungeon.player.hasRelic(Longbow.ID) && AbstractDungeon.player.currentHealth > 0) {
            cat.setFlip(__instance.flipHorizontal, __instance.flipVertical);
            cat.renderSprite(sb, __instance.drawX + __instance.animX, __instance.drawY + __instance.animY + AbstractDungeon.sceneOffsetY);

            __instance.hb.render(sb);
            __instance.healthHb.render(sb);
            return SpireReturn.Return(null);
        }
        return SpireReturn.Continue();
    }

    private static class Locator extends SpireInsertLocator {
        @Override
        public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
            Matcher finalMatcher = new Matcher.FieldAccessMatcher(AbstractPlayer.class, "atlas");
            return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
        }
    }
}