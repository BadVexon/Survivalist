package theSurvivalist.util;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
import com.megacrit.cardcrawl.events.beyond.SensoryStone;
import javassist.CannotCompileException;
import javassist.CtBehavior;

import java.util.ArrayList;
import java.util.Collections;

@SpirePatch(
        clz = SensoryStone.class,
        method = "getRandomMemory"
)
public class SensoryStoneTextPatch {
    @SpireInsertPatch(
            locator = Locator.class,
            localvars = {"memories"}
    )
    public static void Insert(SensoryStone obj, ArrayList<String> memories) {
        String MEMORY_TEXT =
                "Your bow holds steady, your eyes not leaving the creature’s sight as it runs towards you. You fire a flare into the beast’s side, then turn and run into the distance. NL" +

                "Tree branches fall around out, as a blast erupts from where you once stood. After picking yourself up, you turn to see the gaping maw still charging, enraged. NL" +

                "You hold your arms up, which erupt in pain as the skin hardens to a metallic shape, causing the beast to break its teeth upon you. Another burst of pain as the skin turns toxic, and the beast falls back with a thud. NL" +

                "These creatures had been going further and further from the Spire. It was time to put an end to it. NL";
        memories.add(MEMORY_TEXT);
    }

    public static class Locator extends SpireInsertLocator {
        public int[] Locate(CtBehavior ctMethodToPatch) throws CannotCompileException, PatchingException {
            Matcher finalMatcher = new Matcher.MethodCallMatcher(Collections.class, "shuffle");

            return LineFinder.findInOrder(ctMethodToPatch, new ArrayList<>(), finalMatcher);
        }
    }
}