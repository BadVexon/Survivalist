package theSurvivalist.traps;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.EmptyOrbSlot;
import com.megacrit.cardcrawl.orbs.Lightning;

import static theSurvivalist.util.Wiz.atb;

public class TrapTrap extends AbstractTrap {
    public static String ID = "survmod:TrapTrap";

    public TrapTrap() {
        super(ID, "Trap Trap", "survmodResources/images/TrapTrap.png", -1, -1);
    }

    @Override
    public void onEvok() {
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                atb(new AbstractGameAction() {
                    @Override
                    public void update() {
                        isDone = true;
                        for (AbstractOrb o : AbstractDungeon.player.orbs) {
                            if (o instanceof EmptyOrbSlot) {
                                AbstractDungeon.actionManager.addToTop(new ChannelAction(getRandomTrap()));
                            }
                        }
                    }
                });
            }
        });
    }

    public static AbstractOrb getRandomTrap() {
        int x = AbstractDungeon.cardRandomRng.random(0, 6);
        switch (x) {
            case 0:
                return new AcidTrap(1);
            case 1:
                return new BearTrap(2);
            case 2:
                return new ExplosiveTrap(14);
            case 3:
                return new FenceTrap(9);
            case 4:
                return new FlashbangTrap(10);
            case 5:
                return new MarkedTrap();
            case 6:
                return new PoisonTrap(8);
        }
        return new Lightning();
    }

    @Override
    public void updateDescription() {
        description = "After you activate a Trap, fill your Trap slots with random Traps.";
    }

    @Override
    public AbstractOrb makeCopy() {
        return new TrapTrap();
    }
}
