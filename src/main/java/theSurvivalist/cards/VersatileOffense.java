package theSurvivalist.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.watcher.TriggerMarksAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theSurvivalist.DistancePanel;

public class VersatileOffense extends AbstractSurvivorCard {

    public final static String ID = makeID("VersatileOffense");

    //stupid intellij stuff ATTACK, ENEMY, BASIC

    public VersatileOffense(boolean showCard) {
        super(ID, 2, CardType.ATTACK, CardRarity.BASIC, CardTarget.ENEMY);
        if (showCard) {
            cardsToPreview = new VersatileOffense(false);
        }
    }

    @Override
    public void triggerWhenDrawn() {
        applyPowers();
    }

    public VersatileOffense() {
        this(true);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (DistancePanel.getPow() == DistancePanel.DPOW.CLOSE_RANGE) {
            dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
            distUp(magicNumber);
        } else if (DistancePanel.getPow() == DistancePanel.DPOW.MID_RANGE) {
            dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        } else {
            dmg(m, AbstractGameAction.AttackEffect.FIRE);
            markEnemy(m, magicNumber);
            atb(new TriggerMarksAction(this));
            distUp(-2);
        }
    }

    @Override
    public void applyPowers() {
        if (CardCrawlGame.isInARun()) {
            if (DistancePanel.getPow() == DistancePanel.DPOW.CLOSE_RANGE) {
                baseDamage = 8;
                if (upgraded)
                    baseMagicNumber = magicNumber = 4;
                else
                    baseMagicNumber = magicNumber = 2;
                name = "Hit and Run";
                rawDescription = "Deal !D! damage. NL Gain !M! survmod:Distance.";
                loadCardImage("survmodResources/images/cards/HitAndRun.png");
            } else if (DistancePanel.getPow() == DistancePanel.DPOW.MID_RANGE) {
                if (upgraded)
                    baseDamage = 18;
                else
                    baseDamage = 14;
                name = "Power Shot";
                rawDescription = "Deal !D! damage.";
                loadCardImage("survmodResources/images/cards/PowerShot.png");
            } else {
                if (upgraded)
                    baseMagicNumber = magicNumber = 12;
                else
                    baseMagicNumber = magicNumber = 8;
                baseDamage = 5;
                name = "Flared Shot";
                rawDescription = "Deal !D! damage and apply !M! *Mark. NL ALL enemies lose HP equal to their *Mark. NL Lose 2 Distance.";
                loadCardImage("survmodResources/images/cards/FlaredShot.png");
            }
            initializeTitle();
            initializeDescription();
        }
        super.applyPowers();
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
        }
    }
}