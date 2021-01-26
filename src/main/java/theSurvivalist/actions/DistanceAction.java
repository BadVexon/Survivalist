package theSurvivalist.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import theSurvivalist.DistancePanel;
import theSurvivalist.powers.NoDistancePower;
import theSurvivalist.relics.RunningShoes;
import theSurvivalist.traps.AbstractTrap;

public class DistanceAction extends AbstractGameAction {

    private int amt;

    public DistanceAction(int amount) {
        amt = amount;
    }

    public void update() {
        if (AbstractDungeon.player.hasPower(NoDistancePower.POWER_ID) && amt > 0) {
            AbstractDungeon.player.getPower(NoDistancePower.POWER_ID).flash();
        } else {
            if (AbstractDungeon.player.hasRelic(RunningShoes.ID) && amt > 0 && !AbstractDungeon.player.getRelic(RunningShoes.ID).grayscale) {
                amt++;
                AbstractDungeon.player.getRelic(RunningShoes.ID).onTrigger();
            }
            for (AbstractOrb o : AbstractDungeon.player.orbs) {
                if (o instanceof AbstractTrap) {
                    ((AbstractTrap) o).onDistanceChange(amt);
                }
            }
            DistancePanel.adjustDistance(amt);
        }
        isDone = true;
    }
}
