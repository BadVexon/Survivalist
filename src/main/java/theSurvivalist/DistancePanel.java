package theSurvivalist;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.MonsterRoomElite;
import theSurvivalist.actions.MoveCreatureAction;
import theSurvivalist.powers.CamouflagePower;
import theSurvivalist.powers.CloseCombatPower;
import theSurvivalist.powers.FakeFlightPower;
import theSurvivalist.powers.ThornyTrailPower;
import theSurvivalist.relics.Longbow;
import theSurvivalist.relics.SharpenedDagger;
import theSurvivalist.util.TextureLoader;

import java.util.ArrayList;

public class DistancePanel {
    public static ArrayList<Float> distances = new ArrayList<>();
    private static Texture bannerLong = TextureLoader.getTexture("survmodResources/images/ui/longBanner.png");
    private static Texture bannerShort = TextureLoader.getTexture("survmodResources/images/ui/closeBanner.png");

    static {
        for (int i = 25; i >= 5; i--) {
            distances.add((float) ((Settings.WIDTH / 60) * i));
        }
    }

    public static int distance;

    public DistancePanel() {
        init();
    }

    public static void init() {
        distance = 5;
        if (!(AbstractDungeon.getCurrRoom() instanceof MonsterRoomElite && AbstractDungeon.actNum == 4)) {
            AbstractDungeon.player.drawX = distances.get(5);
            for (int i = 0; i < AbstractDungeon.player.orbs.size(); i++) {
                AbstractDungeon.player.orbs.get(i).setSlot(i, AbstractDungeon.player.maxOrbs);
            }
            AbstractDungeon.player.dialogX = (AbstractDungeon.player.drawX + 0.0F * Settings.scale);
        }
    }

    public static int getReduction() {
        int bruh = distance;
        if (bruh > 7)
            bruh = 7;
        if (AbstractDungeon.player.hasPower(FakeFlightPower.POWER_ID)) {
            bruh += 5;
        }
        return bruh * 10;
    }

    public static DPOW getPow() {
        if (distance <= 2)
            return DPOW.CLOSE_RANGE;
        else if (distance < 7)
            return DPOW.MID_RANGE;
        else
            return DPOW.LONG_RANGE;
    }

    public static void adjustDistance(int amount) {
        DPOW d = getPow();
        int x = distance;
        distance += amount;
        if (distance < 0)
            distance = 0;
        if (distance > 20)
            distance = 20;
        if (!(AbstractDungeon.getCurrRoom() instanceof MonsterRoomElite && AbstractDungeon.actNum == 4))
            AbstractDungeon.actionManager.addToTop(new MoveCreatureAction(AbstractDungeon.player, distances.get(distance), (Math.abs(AbstractDungeon.player.drawX - distances.get(distance))) / 250));
        if (getPow() == DPOW.CLOSE_RANGE && d != DPOW.CLOSE_RANGE) {
            if (AbstractDungeon.player.hasPower(CloseCombatPower.POWER_ID)) {
                AbstractDungeon.player.getPower(CloseCombatPower.POWER_ID).onSpecificTrigger();
            }
            if (AbstractDungeon.player.hasRelic(Longbow.ID)) {
                ((Longbow) AbstractDungeon.player.getRelic(Longbow.ID)).onCloseRange();
            }
            if (AbstractDungeon.player.hasRelic(SharpenedDagger.ID)) {
                ((SharpenedDagger) AbstractDungeon.player.getRelic(SharpenedDagger.ID)).onCloseRange();
            }
        }
        if (getPow() == DPOW.LONG_RANGE && d != DPOW.LONG_RANGE) {
            if (AbstractDungeon.player.hasPower(CamouflagePower.POWER_ID)) {
                AbstractDungeon.player.getPower(CamouflagePower.POWER_ID).onSpecificTrigger();
            }
            if (AbstractDungeon.player.hasRelic(Longbow.ID)) {
                ((Longbow) AbstractDungeon.player.getRelic(Longbow.ID)).onLongRange();
            }
        }
        if (getPow() != DPOW.LONG_RANGE && d == DPOW.LONG_RANGE) {
            if (AbstractDungeon.player.hasRelic(Longbow.ID)) {
                ((Longbow) AbstractDungeon.player.getRelic(Longbow.ID)).onExitLongRange();
            }
        }
        if (getPow() != DPOW.CLOSE_RANGE && d == DPOW.CLOSE_RANGE) {
            if (AbstractDungeon.player.hasRelic(Longbow.ID)) {
                ((Longbow) AbstractDungeon.player.getRelic(Longbow.ID)).onExitCloseRange();
            }
            if (AbstractDungeon.player.hasRelic(SharpenedDagger.ID)) {
                ((SharpenedDagger) AbstractDungeon.player.getRelic(SharpenedDagger.ID)).onExitCloseRange();
            }
        }
        if (distance < x) {
            if (AbstractDungeon.player.hasPower(ThornyTrailPower.POWER_ID)) {
                AbstractDungeon.player.getPower(ThornyTrailPower.POWER_ID).onSpecificTrigger();
            }
        }

        for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
            m.applyPowers();
        }
    }

    public enum DPOW {
        LONG_RANGE,
        MID_RANGE,
        CLOSE_RANGE
    }
}
