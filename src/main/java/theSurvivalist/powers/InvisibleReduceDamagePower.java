package theSurvivalist.powers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.InvisiblePower;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theSurvivalist.DistancePanel;
import theSurvivalist.SurvivalMod;
import theSurvivalist.TheSurvivor;
import theSurvivalist.util.TextureLoader;

import static theSurvivalist.SurvivalMod.getModID;
import static theSurvivalist.SurvivalMod.renderStuff;

public class InvisibleReduceDamagePower extends AbstractPower implements InvisiblePower {

    public static final String POWER_ID = SurvivalMod.makeID("InvisibleReduceDamagePower");

    private static final Texture tex84 = TextureLoader.getTexture("survmodResources/images/powers/" + POWER_ID.replaceAll(getModID() + ":", "") + "84.png");
    private static final Texture tex32 = TextureLoader.getTexture("survmodResources/images/powers/" + POWER_ID.replaceAll(getModID() + ":", "") + "32.png");


    public InvisibleReduceDamagePower(final int amount) {
        this.name = "???";
        this.ID = POWER_ID;
        this.owner = AbstractDungeon.player;
        this.amount = amount;
        this.type = PowerType.BUFF;
        this.isTurnBased = false;
        this.canGoNegative = false;

        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        this.updateDescription();
    }

    @Override
    public float atDamageReceive(float damage, DamageInfo.DamageType type) {
        System.out.println(DistancePanel.distance);
        if ((AbstractDungeon.player instanceof TheSurvivor || renderStuff) && type == DamageInfo.DamageType.NORMAL && DistancePanel.distance > 0) {
            int x = Math.min(DistancePanel.distance, 7);
            if (AbstractDungeon.player.hasPower(FakeFlightPower.POWER_ID)) {
                x += 5;
            }
            return Math.round(damage * (1 - (Math.min(1, x / 10f))));
        }
        System.out.println(AbstractDungeon.player instanceof TheSurvivor);
        System.out.println(renderStuff);
        System.out.println(type);
        System.out.println(DistancePanel.distance);
        System.out.println(Math.round(damage * (1 - (Math.min(DistancePanel.distance, 7) / 10f))));
        return damage;
    }

    @Override
    public void updateDescription() {
        description = "CANNOT SEE";
    }
}