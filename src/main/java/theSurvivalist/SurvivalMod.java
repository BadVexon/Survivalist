package theSurvivalist;

import basemod.BaseMod;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.mod.stslib.Keyword;
import com.evacipated.cardcrawl.modthespire.Loader;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import javassist.CtClass;
import javassist.Modifier;
import javassist.NotFoundException;
import org.clapper.util.classutil.*;
import theSurvivalist.powers.FakeFlightPower;
import theSurvivalist.powers.InvisibleReduceDamagePower;
import theSurvivalist.relics.*;
import theSurvivalist.traps.AbstractTrap;
import theSurvivalist.util.DraggableStatDisplay;
import theSurvivalist.util.TipHelperCopy;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;

@SuppressWarnings({"ConstantConditions", "unused", "WeakerAccess"})
@SpireInitializer
public class SurvivalMod implements
        EditCardsSubscriber,
        EditRelicsSubscriber,
        EditStringsSubscriber,
        EditKeywordsSubscriber,
        EditCharactersSubscriber,
        StartGameSubscriber,
        PostBattleSubscriber,
        PreRoomRenderSubscriber,
        OnCardUseSubscriber, PreMonsterTurnSubscriber,
        OnStartBattleSubscriber {
    public static final String SHOULDER1 = "survmodResources/images/char/mainChar/shoulder.png";
    public static final String SHOULDER2 = "survmodResources/images/char/mainChar/shoulder2.png";
    public static final String CORPSE = "survmodResources/images/char/mainChar/corpse.png";
    private static final String ATTACK_S_ART = "survmodResources/images/512/canvas_attack_s.png";
    private static final String SKILL_S_ART = "survmodResources/images/512/canvas_skill_s.png";
    private static final String POWER_S_ART = "survmodResources/images/512/canvas_power_s.png";
    private static final String CARD_ENERGY_S = "survmodResources/images/512/card_default_gray_orb.png";
    private static final String TEXT_ENERGY = "survmodResources/images/512/card_small_orb.png";
    private static final String ATTACK_L_ART = "survmodResources/images/1024/canvas_attack.png";
    private static final String SKILL_L_ART = "survmodResources/images/1024/canvas_skill.png";
    private static final String POWER_L_ART = "survmodResources/images/1024/canvas_power.png";
    private static final String CARD_ENERGY_L = "survmodResources/images/1024/card_default_gray_orb.png";
    private static final String CHARSELECT_BUTTON = "survmodResources/images/charSelect/charButton.png";
    private static final String CHARSELECT_PORTRAIT = "survmodResources/images/charSelect/charBG.png";
    private static String modID;
    private static boolean thindone;

    public static boolean renderStuff;

    public static boolean beingAttacked = false;

    public static DraggableStatDisplay newHitbox;

    public static Color chemColor = new Color(0.749F, 0.709F, 0.133F, 1);

    public SurvivalMod() {

        BaseMod.subscribe(this);

        modID = "survmod";

        BaseMod.addColor(TheSurvivor.Enums.SURVIVOR_BROWNIE, chemColor, chemColor, chemColor,
                chemColor, chemColor, chemColor, chemColor,
                ATTACK_S_ART, SKILL_S_ART, POWER_S_ART, CARD_ENERGY_S,
                ATTACK_L_ART, SKILL_L_ART, POWER_L_ART,
                CARD_ENERGY_L, TEXT_ENERGY);

        newHitbox = new DraggableStatDisplay();

    }

    public static String makeCardPath(String resourcePath) {
        return getModID() + "Resources/images/cards/" + resourcePath;
    }

    public static String makeRelicPath(String resourcePath) {
        return getModID() + "Resources/images/relics/" + resourcePath;
    }

    public static String makeRelicOutlinePath(String resourcePath) {
        return getModID() + "Resources/images/relics/outline/" + resourcePath;
    }

    public static String makePowerPath(String resourcePath) {
        return getModID() + "Resources/images/powers/" + resourcePath;
    }

    public static String getModID() {
        return modID;
    }

    public static void initialize() {
        SurvivalMod chemistMod = new SurvivalMod();
    }

    public static String makeID(String idText) {
        return getModID() + ":" + idText;
    }

    @Override
    public void receiveEditCharacters() {
        BaseMod.addCharacter(new TheSurvivor("the Survivalist", TheSurvivor.Enums.THE_SURVIVOR),
                CHARSELECT_BUTTON, CHARSELECT_PORTRAIT, TheSurvivor.Enums.THE_SURVIVOR);
    }

    @Override
    public void receiveEditRelics() {
        BaseMod.addRelicToCustomPool(new InnateMutation(), TheSurvivor.Enums.SURVIVOR_BROWNIE);
        BaseMod.addRelicToCustomPool(new AdvancedEvolution(), TheSurvivor.Enums.SURVIVOR_BROWNIE);
        BaseMod.addRelicToCustomPool(new GoodBoy(), TheSurvivor.Enums.SURVIVOR_BROWNIE);
        BaseMod.addRelicToCustomPool(new Longbow(), TheSurvivor.Enums.SURVIVOR_BROWNIE);
        BaseMod.addRelicToCustomPool(new SharpenedDagger(), TheSurvivor.Enums.SURVIVOR_BROWNIE);
        BaseMod.addRelicToCustomPool(new ShotGlass(), TheSurvivor.Enums.SURVIVOR_BROWNIE);
        BaseMod.addRelicToCustomPool(new TrapManual(), TheSurvivor.Enums.SURVIVOR_BROWNIE);
        BaseMod.addRelicToCustomPool(new RunningShoes(), TheSurvivor.Enums.SURVIVOR_BROWNIE);
        BaseMod.addRelicToCustomPool(new IronskinOutfit(), TheSurvivor.Enums.SURVIVOR_BROWNIE);
        BaseMod.addRelicToCustomPool(new SignalFlare(), TheSurvivor.Enums.SURVIVOR_BROWNIE);
    }

    @Override
    public void receiveEditCards() {
        try {
            autoAddCards();
        } catch (URISyntaxException | IllegalAccessException | InstantiationException | NotFoundException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static void autoAddCards()
            throws URISyntaxException, IllegalAccessException, InstantiationException, NotFoundException, ClassNotFoundException {
        ClassFinder finder = new ClassFinder();
        URL url = SurvivalMod.class.getProtectionDomain().getCodeSource().getLocation();
        finder.add(new File(url.toURI()));

        ClassFilter filter =
                new AndClassFilter(
                        new NotClassFilter(new InterfaceOnlyClassFilter()),
                        new NotClassFilter(new AbstractClassFilter()),
                        new ClassModifiersClassFilter(Modifier.PUBLIC),
                        new CardFilter()
                );
        Collection<ClassInfo> foundClasses = new ArrayList<>();
        finder.findClasses(foundClasses, filter);

        for (ClassInfo classInfo : foundClasses) {
            CtClass cls = Loader.getClassPool().get(classInfo.getClassName());
            if (cls.hasAnnotation(CardIgnore.class)) {
                continue;
            }
            boolean isCard = false;
            CtClass superCls = cls;
            while (superCls != null) {
                superCls = superCls.getSuperclass();
                if (superCls == null) {
                    break;
                }
                if (superCls.getName().equals(AbstractCard.class.getName())) {
                    isCard = true;
                    break;
                }
            }
            if (!isCard) {
                continue;
            }
            System.out.println(classInfo.getClassName());
            AbstractCard card = (AbstractCard) Loader.getClassPool().getClassLoader().loadClass(cls.getName()).newInstance();
            BaseMod.addCard(card);
            if (cls.hasAnnotation(CardNoSeen.class)) {
                UnlockTracker.hardUnlockOverride(card.cardID);
            } else {
                UnlockTracker.unlockCard(card.cardID);
            }
        }
    }


    @Override
    public void receiveEditStrings() {
        BaseMod.loadCustomStringsFile(CardStrings.class, getModID() + "Resources/localization/eng/Cardstrings.json");

        BaseMod.loadCustomStringsFile(RelicStrings.class, getModID() + "Resources/localization/eng/Relicstrings.json");

        BaseMod.loadCustomStringsFile(CharacterStrings.class, getModID() + "Resources/localization/eng/Charstrings.json");
    }

    @Override
    public void receiveEditKeywords() {
        Gson gson = new Gson();
        String json = Gdx.files.internal(getModID() + "Resources/localization/eng/Keywordstrings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        com.evacipated.cardcrawl.mod.stslib.Keyword[] keywords = gson.fromJson(json, com.evacipated.cardcrawl.mod.stslib.Keyword[].class);

        if (keywords != null) {
            for (Keyword keyword : keywords) {
                BaseMod.addKeyword("survmod", keyword.PROPER_NAME, keyword.NAMES, keyword.DESCRIPTION);
            }
        }
    }

    @Override
    public void receiveStartGame() {
        if (!thindone) {
            newHitbox.hb.resize(400 * Settings.scale, 160 * Settings.scale);
            newHitbox.hb.move(256 * Settings.scale, 600 * Settings.scale);
            thindone = true;
        }
    }

    @Override
    public void receivePreRoomRender(SpriteBatch sb) {
        if ((AbstractDungeon.player instanceof TheSurvivor || renderStuff) && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            String s = "";
            if (DistancePanel.distance == 0) {
                s = ("Current Distance: #b" + DistancePanel.distance + " NL Current Phase: " + DistancePanel.getPow().toString()).replaceAll("_", " ");
                if (AbstractDungeon.player.hasPower(FakeFlightPower.POWER_ID)) {
                    s += " NL Damage reduced by #g+50%.";
                }
            } else {
                if (AbstractDungeon.player.hasPower(FakeFlightPower.POWER_ID))
                    s = ("Current Distance: #b" + DistancePanel.distance + " NL Current Phase: " + DistancePanel.getPow().toString()).replaceAll("_", " ") + " NL Damage reduced by #b" + (DistancePanel.getReduction() - 50) + " #g+50%.";
                else
                    s = ("Current Distance: #b" + DistancePanel.distance + " NL Current Phase: " + DistancePanel.getPow().toString()).replaceAll("_", " ") + " NL Damage reduced by #b" + DistancePanel.getReduction() + "%.";
            }
            TipHelperCopy.renderGenericTip(SurvivalMod.newHitbox.hb.x, SurvivalMod.newHitbox.hb.y + SurvivalMod.newHitbox.hb.height, "Distance Info", s);
            TipHelperCopy.render(sb);
        }
    }

    @Override
    public void receivePostBattle(AbstractRoom abstractRoom) {
        DistancePanel.distance = 0;
        AbstractDungeon.player.drawX = DistancePanel.distances.get(5);
    }

    @Override
    public void receiveCardUsed(AbstractCard abstractCard) {
        for (AbstractOrb o : AbstractDungeon.player.orbs) {
            if (o instanceof AbstractTrap) ((AbstractTrap) o).onUseCard(abstractCard);
        }
    }

    @Override
    public boolean receivePreMonsterTurn(AbstractMonster abstractMonster) {
        if (abstractMonster.getIntentBaseDmg() > -1) beingAttacked = true;
        return true;
    }

    @Override
    public void receiveOnBattleStart(AbstractRoom abstractRoom) {
        DistancePanel.init();
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new InvisibleReduceDamagePower(1)));
    }
}
