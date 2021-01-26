package theSurvivalist.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theSurvivalist.cards.OctoChoiceCard;
import theSurvivalist.util.CenterGridCardSelectScreen;

import java.util.ArrayList;

public class OctoChoiceAction extends AbstractGameAction {
    private boolean pickCard = false;
    private CardGroup group = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
    private ArrayList<OctoChoiceCard> cardArrayList;

    public OctoChoiceAction(ArrayList<OctoChoiceCard> cardList) {
        duration = Settings.ACTION_DUR_XFAST;
        actionType = ActionType.WAIT;
        cardArrayList = cardList;
    }

    @Override
    public void update() {
        for (OctoChoiceCard q : cardArrayList) {
            group.addToTop(q);
        }
        if (duration == Settings.ACTION_DUR_XFAST && !group.isEmpty()) {
            pickCard = true;
            CenterGridCardSelectScreen.centerGridSelect = true;
            AbstractDungeon.gridSelectScreen.open(group, 1, ("Choose a Wish to use."), false);
        } else if ((pickCard && !AbstractDungeon.gridSelectScreen.selectedCards.isEmpty())) {
            OctoChoiceCard cardChoice = new OctoChoiceCard("null", "null", "survmodResources/images/cards/Adaptability.png", "You should never see this.", new GainEnergyAction(1));
            if (pickCard && !AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
                pickCard = false;
                cardChoice = (OctoChoiceCard) AbstractDungeon.gridSelectScreen.selectedCards.get(0);
                AbstractDungeon.gridSelectScreen.selectedCards.clear();
                CenterGridCardSelectScreen.centerGridSelect = false;
            }
            cardChoice.use(AbstractDungeon.player, AbstractDungeon.getRandomMonster());

            isDone = true;
        } else if (group.isEmpty()) {
            isDone = true;
        }
        tickDuration();
    }
}