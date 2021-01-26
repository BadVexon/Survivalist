package theSurvivalist.traps;

import basemod.abstracts.CustomOrb;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.mod.stslib.actions.defect.EvokeSpecificOrbAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;

public abstract class AbstractTrap extends CustomOrb {
    public AbstractTrap(String ID, String name, int timer, int evokeAmt) {
        this(ID, name, "survmodResources/images/Orb.png", timer, evokeAmt);
    }

    public AbstractTrap(String ID, String name, String IMG, int timer, int evokeAmt) {
        super(ID, name, timer, evokeAmt, "", "", IMG);
    }

    public abstract void updateDescription();

    @Override
    public void onEvoke() {
        onEvok();
        for (AbstractOrb o : AbstractDungeon.player.orbs) {
            if (o instanceof TrapTrap && o != this && !(this instanceof TrapTrap)) {
                AbstractDungeon.actionManager.addToBottom(new EvokeSpecificOrbAction(o));
            }
        }
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c.cardID.toLowerCase().contains("liver")) {
                c.superFlash();
                c.baseDamage += c.magicNumber;
                c.upgradedDamage = true;
            }
        }
        for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
            if (c.cardID.toLowerCase().contains("liver")) {
                c.baseDamage += c.magicNumber;
                c.upgradedDamage = true;
            }
        }
        for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
            if (c.cardID.toLowerCase().contains("liver")) {
                c.baseDamage += c.magicNumber;
                c.upgradedDamage = true;
            }
        }
    }

    @Override
    protected void renderText(SpriteBatch sb) {
        if ((showEvokeValue && evokeAmount >= 0) || passiveAmount >= 0) {
            super.renderText(sb);
        }
    }

    public abstract void onEvok();

    public void onUseCard(AbstractCard q) {

    }

    public void onBlockBroken() {

    }

    public void onNotAttacked() {

    }

    public void onDistanceChange(int amt) {

    }

    public void decrement() {
        passiveAmount -= 1;
        if (passiveAmount == 0) {
            AbstractDungeon.actionManager.addToBottom(new EvokeSpecificOrbAction(this));
        }
    }

    public void playChannelSFX() {
        CardCrawlGame.sound.play("ORB_LIGHTNING_CHANNEL", 0.1F);
    }
}
