package theSurvivalist.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.watcher.MarkPower;
import theSurvivalist.SurvivalMod;
import theSurvivalist.util.TextureLoader;
import static theSurvivalist.SurvivalMod.getModID;
public class SuperStrongAttacksPower extends AbstractPower implements CloneablePowerInterface {

    public static final String POWER_ID = SurvivalMod.makeID("SuperStrongAttacksPower");

    private static final Texture tex84 = TextureLoader.getTexture("survmodResources/images/powers/" + POWER_ID.replaceAll(getModID() + ":", "") + "84.png");
    private static final Texture tex32 = TextureLoader.getTexture("survmodResources/images/powers/" + POWER_ID.replaceAll(getModID() + ":", "") + "32.png");


    public SuperStrongAttacksPower(final int amount) {
        this.name = "Advantage";
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

    public void atEndOfTurn(boolean isPlayer) {
        this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, ID));// 81
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.type == AbstractCard.CardType.ATTACK) {
            flash();
            addToBot(new AbstractGameAction() {
                @Override
                public void update() {
                    isDone = true;
                    for (AbstractMonster q : AbstractDungeon.getCurrRoom().monsters.monsters) {
                        if (!q.isDying && !q.isDead) {
                            if (q.hasPower(MarkPower.POWER_ID)) {
                                addToTop(new LoseHPAction(q, AbstractDungeon.player, q.getPower(MarkPower.POWER_ID).amount));
                            }
                        }
                    }
                }
            });
        }
    }

    @Override
    public float atDamageGive(float damage, DamageInfo.DamageType type, AbstractCard card) {
        return type == DamageInfo.DamageType.NORMAL ? damage + (float) this.amount : damage;
    }

    @Override
    public void updateDescription() {
        description = "Attacks deal #b" + amount + " additional damage this turn and trigger Mark.";
    }

    @Override
    public AbstractPower makeCopy() {
        return new SuperStrongAttacksPower(amount);
    }
}