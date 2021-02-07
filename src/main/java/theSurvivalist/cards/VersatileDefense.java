package theSurvivalist.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import com.megacrit.cardcrawl.powers.EnergizedBluePower;
import com.megacrit.cardcrawl.powers.NextTurnBlockPower;
import theSurvivalist.DistancePanel;
import theSurvivalist.powers.NextTurnDistancePower;

public class VersatileDefense extends AbstractSurvivorCard {

    public final static String ID = makeID("VersatileDefense");

    //stupid intellij stuff ATTACK, ENEMY, BASIC

    public VersatileDefense(boolean showCard) {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        if (showCard) {
            cardsToPreview = new VersatileDefense(false);
        }
         cardsToPreview = new BobandWeave(true); new LeapingDodge(true); new HideandWait(true)
    }


    @Override
    public void triggerWhenDrawn() {
        applyPowers();
    }


    public VersatileDefense() {
        this(true);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        if (DistancePanel.getPow() == DistancePanel.DPOW.CLOSE_RANGE) {
            applyToSelf(new NextTurnDistancePower(magicNumber));
        } else if (DistancePanel.getPow() == DistancePanel.DPOW.MID_RANGE) {
            applyToSelf(new NextTurnBlockPower(p, block));
        } else {
            applyToSelf(new EnergizedBluePower(p, magicNumber));
            applyToSelf(new DrawCardNextTurnPower(p, magicNumber));
        }
    }

    @Override
    public void applyPowers() {
        if (CardCrawlGame.isInARun()) {
            if (DistancePanel.getPow() == DistancePanel.DPOW.CLOSE_RANGE) {
                baseBlock = 15;
                if (upgraded)
                    baseMagicNumber = magicNumber =3;
                else
                    baseMagicNumber = magicNumber = 2;
                name = "Bob and Weave";
                rawDescription = "Gain !B! Block. NL Next turn, gain !M! Distance.";
            } else if (DistancePanel.getPow() == DistancePanel.DPOW.MID_RANGE) {
                if (upgraded)
                    baseBlock = 13;
                else
                    baseBlock = 10;
                name = "Leaping Dodge";
                rawDescription = "Gain !B! Block. NL Next turn, gain !B! Block.";
            } else {
                if (upgraded)
                    baseMagicNumber = magicNumber =  3;
                else
                    baseMagicNumber = magicNumber = 2;
                baseBlock = 5;
                name = "Hide and Wait";
                rawDescription = "Gain !B! Block. NL Next turn, draw !M! cards and gain !M! [E] .";
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
