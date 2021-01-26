package theSurvivalist.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theSurvivalist.DistancePanel;
import theSurvivalist.powers.FlaredArrowheadsPower;
import theSurvivalist.powers.MartialDefensePower;
import theSurvivalist.powers.ThornyTrailPower;

public class VersatilePower extends AbstractSurvivorCard {

    public final static String ID = makeID("VersatilePower");

    //stupid intellij stuff ATTACK, ENEMY, BASIC

    public VersatilePower(boolean showCard) {
        super(ID, 2, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        if (showCard) {
            cardsToPreview = new VersatilePower(false);
        }
    }

    @Override
    public void triggerWhenDrawn() {
        applyPowers();
    }

    public VersatilePower() {
        this(false);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (DistancePanel.getPow() == DistancePanel.DPOW.CLOSE_RANGE) {
            applyToSelf(new MartialDefensePower(magicNumber));
        } else if (DistancePanel.getPow() == DistancePanel.DPOW.MID_RANGE) {
            applyToSelf(new ThornyTrailPower(magicNumber));
        } else {
            applyToSelf(new FlaredArrowheadsPower(magicNumber));
        }
    }

    @Override
    public void applyPowers() {
        if (CardCrawlGame.isInARun()) {
            if (DistancePanel.getPow() == DistancePanel.DPOW.CLOSE_RANGE) {
                if (upgraded)
                    baseMagicNumber = magicNumber = 8;
                else
                    baseMagicNumber = magicNumber = 6;
                name = "Martial Defense";
                rawDescription = "Whenever you play an Attack in Close Range, gain !M! Block.";
            } else if (DistancePanel.getPow() == DistancePanel.DPOW.MID_RANGE) {
                if (upgraded)
                    baseMagicNumber = magicNumber = 7;
                else
                    baseMagicNumber = magicNumber = 5;
                name = "Thorny Trail";
                rawDescription = "Whenever you lose survmod:Distance, deal !M! damage to ALL enemies.";
            } else {
                if (upgraded)
                    baseMagicNumber = magicNumber = 10;
                else
                    baseMagicNumber = magicNumber = 6;
                name = "Flared Arrowheads";
                rawDescription = "While you are in Long Range, whenever you deal unblocked damage, apply !M! Mark.";
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