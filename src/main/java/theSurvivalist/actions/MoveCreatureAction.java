package theSurvivalist.actions;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.MathHelper;

public class MoveCreatureAction extends AbstractGameAction {
    AbstractCreature ac;

    float startX;
    float endX;

    float cur;
    float time;

    public MoveCreatureAction(AbstractCreature ac, float x, float time) {
        this.ac = ac;

        this.endX = x;

        this.time = time;
        this.cur = 0;
    }

    public void update() {
        if (startX == 0.0F) {
            this.startX = ac.drawX;
        }

        cur += Gdx.graphics.getDeltaTime();

        if (cur >= time) {
            this.isDone = true;
            return;
        }
        ac.drawX = MathUtils.lerp(startX, endX, cur / time);
        if (ac instanceof AbstractPlayer) {
            for (int i = 0; i < AbstractDungeon.player.orbs.size(); i++) {
                AbstractDungeon.player.orbs.get(i).setSlot(i, AbstractDungeon.player.maxOrbs);
            }
        }
        ac.dialogX = (ac.drawX + 0.0F * Settings.scale);
    }
}