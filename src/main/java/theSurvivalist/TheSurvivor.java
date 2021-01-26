package theSurvivalist;

import basemod.abstracts.CustomEnergyOrb;
import basemod.abstracts.CustomPlayer;
import basemod.animations.SpriterAnimation;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import theSurvivalist.cards.Defend;
import theSurvivalist.cards.Sprint;
import theSurvivalist.cards.Strike;
import theSurvivalist.cards.VersatileOffense;
import theSurvivalist.relics.InnateMutation;

import java.util.ArrayList;

import static theSurvivalist.SurvivalMod.*;
import static theSurvivalist.TheSurvivor.Enums.SURVIVOR_BROWNIE;

public class TheSurvivor extends CustomPlayer {
    private static final String[] orbTextures = {
            "survmodResources/images/char/mainChar/orb/layer1.png",
            "survmodResources/images/char/mainChar/orb/layer2.png",
            "survmodResources/images/char/mainChar/orb/layer3.png",
            "survmodResources/images/char/mainChar/orb/layer4.png",
            "survmodResources/images/char/mainChar/orb/layer5.png",
            "survmodResources/images/char/mainChar/orb/layer6.png",
            "survmodResources/images/char/mainChar/orb/layer1d.png",
            "survmodResources/images/char/mainChar/orb/layer2d.png",
            "survmodResources/images/char/mainChar/orb/layer3d.png",
            "survmodResources/images/char/mainChar/orb/layer4d.png",
            "survmodResources/images/char/mainChar/orb/layer5d.png",};
    private static final String ID = makeID("theSurvivalist");
    private static final CharacterStrings characterStrings = CardCrawlGame.languagePack.getCharacterString(ID);
    private static final String[] NAMES = characterStrings.NAMES;
    private static final String[] TEXT = characterStrings.TEXT;

    public TheSurvivor(String name, PlayerClass setClass) {
        super(name, setClass, new CustomEnergyOrb(orbTextures, "survmodResources/images/char/mainChar/orb/vfx.png", null), new SpriterAnimation(
                "survmodResources/images/char/mainChar/artist.scml"));
        initializeClass(null,
                SHOULDER1,
                SHOULDER2,
                CORPSE,
                getLoadout(), 20.0F, -10.0F, 166.0F, 327.0F, new EnergyManager(3));


        dialogX = (drawX + 0.0F * Settings.scale);
        dialogY = (drawY + 240.0F * Settings.scale);
    }

    @Override
    public String getPortraitImageName() {
        return null;
    }

    @Override
    public CharSelectInfo getLoadout() {
        return new CharSelectInfo(NAMES[0], TEXT[0],
                50, 50, 5, 99, 5, this, getStartingRelics(),
                getStartingDeck(), false);
    }

    @Override
    public ArrayList<String> getStartingDeck() {
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add(Strike.ID);
        retVal.add(Strike.ID);
        retVal.add(Strike.ID);
        retVal.add(Strike.ID);
        retVal.add(Defend.ID);
        retVal.add(Defend.ID);
        retVal.add(Defend.ID);
        retVal.add(Defend.ID);
        retVal.add(VersatileOffense.ID);
        retVal.add(Sprint.ID);
        return retVal;
    }

    public ArrayList<String> getStartingRelics() {
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add(InnateMutation.ID);
        return retVal;
    }

    @Override
    public void doCharSelectScreenSelectEffect() {
        CardCrawlGame.sound.playA("UNLOCK_PING", MathUtils.random(-0.2F, 0.2F));
        CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.LOW, ScreenShake.ShakeDur.SHORT,
                false);
    }

    @Override
    public String getCustomModeCharacterButtonSoundKey() {
        return "UNLOCK_PING";
    }

    @Override
    public int getAscensionMaxHPLoss() {
        return 8;
    }

    @Override
    public AbstractCard.CardColor getCardColor() {
        return SURVIVOR_BROWNIE;
    }

    @Override
    public Color getCardTrailColor() {
        return chemColor.cpy();
    }

    @Override
    public BitmapFont getEnergyNumFont() {
        return FontHelper.energyNumFontRed;
    }

    @Override
    public String getLocalizedCharacterName() {
        return NAMES[0];
    }

    @Override
    public AbstractCard getStartCardForEvent() {
        return new Sprint();
    }

    @Override
    public String getTitle(AbstractPlayer.PlayerClass playerClass) {
        return NAMES[1];
    }

    @Override
    public AbstractPlayer newInstance() {
        return new TheSurvivor(name, chosenClass);
    }

    @Override
    public Color getCardRenderColor() {
        return chemColor.cpy();
    }

    @Override
    public Color getSlashAttackColor() {
        return chemColor.cpy();
    }

    @Override
    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
        return new AbstractGameAction.AttackEffect[]{
                AbstractGameAction.AttackEffect.FIRE,
                AbstractGameAction.AttackEffect.BLUNT_HEAVY,
                AbstractGameAction.AttackEffect.FIRE};
    }

    @Override
    public String getSpireHeartText() {
        return TEXT[1];
    }

    @Override
    public String getVampireText() {
        return TEXT[2];
    }

    public static class Enums {
        @SpireEnum
        public static AbstractPlayer.PlayerClass THE_SURVIVOR;
        @SpireEnum(name = "SURVIVOR_BROWN")
        public static AbstractCard.CardColor SURVIVOR_BROWNIE;
        @SpireEnum(name = "SURVIVOR_BROWN")
        @SuppressWarnings("unused")
        public static CardLibrary.LibraryType LIBRARY_COLOR;
    }
}
