package theSurvivalist.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.EmptyOrbSlot;
import java.util.Iterator;

public class WorseChannelAction extends AbstractGameAction {
    private AbstractOrb orbType;
    private boolean autoEvoke;

    public WorseChannelAction(AbstractOrb newOrbType) {
        this(newOrbType, true);
    }

    public WorseChannelAction(AbstractOrb newOrbType, boolean autoEvoke) {
        this.autoEvoke = false;
        this.duration = Settings.ACTION_DUR_FAST;
        this.orbType = newOrbType;
        this.autoEvoke = autoEvoke;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            if (this.autoEvoke) {
                AbstractDungeon.player.channelOrb(this.orbType);
            } else {
                for (AbstractOrb o : AbstractDungeon.player.orbs) {
                    if (o instanceof EmptyOrbSlot) {
                        AbstractDungeon.player.channelOrb(this.orbType);
                        break;
                    }
                }
            }

            if (Settings.FAST_MODE) {
                this.isDone = true;
                return;
            }
        }

        this.tickDuration();
    }
}
