package theSurvivalist.cards;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.cards.status.Dazed;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theSurvivalist.actions.DistanceAction;
import theSurvivalist.actions.OctoChoiceAction;

import java.util.ArrayList;

public class TacticalManeuver extends AbstractSurvivorCard {

    public final static String ID = makeID("TacticalManeuver");

    //stupid intellij stuff SKILL, SELF, UNCOMMON

    public TacticalManeuver() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        cardsToPreview = new Dazed();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        ArrayList<OctoChoiceCard> cardList = new ArrayList<>();
        OctoChoiceCard cardChoice = new OctoChoiceCard("3Distance", "Gain 3 Distance", "survmodResources/images/cards/Adaptability.png", "Gain 3 Distance.", new DistanceAction(3));
        OctoChoiceCard cardChoice2 = new OctoChoiceCard("-3Distance", "Lose 3 Distance", "survmodResources/images/cards/Adaptability.png", "Lose 3 Distance.", new DistanceAction(-3));
        cardList.add(cardChoice);
        cardList.add(cardChoice2);
        atb(new OctoChoiceAction(cardList));
        atb(new MakeTempCardInDiscardAction(new Dazed(), 1));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(0);
        }
    }
}