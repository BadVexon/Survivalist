package theSurvivalist.util;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import javassist.CtBehavior;
import theSurvivalist.cards.ComplexMutation;

public class DoTheThingGood {
@SpirePatch(clz = AbstractPlayer.class, method = "renderHand")
    public static class AlwaysRenderSwitchcardTip {
        @SpireInsertPatch(locator = Locator.class)
        public static void patch(AbstractPlayer __instance, SpriteBatch sb) {
            AbstractCard c = __instance.hoveredCard;
            if ((__instance.isDraggingCard || __instance.inSingleTargetMode) && c instanceof ComplexMutation) {
                ((ComplexMutation) c).doThing = true;
                c.renderCardPreview(sb);
                ((ComplexMutation) c).doThing = false;
            }
        }

        private static class Locator extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctBehavior) throws Exception {
                Matcher finalMatcher = new Matcher.MethodCallMatcher(AbstractCard.class, "renderHoverShadow");
                return LineFinder.findInOrder(ctBehavior, finalMatcher);
            }
        }
    }
}