package theSurvivalist.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theSurvivalist.CardIgnore;

import static theSurvivalist.cards.AbstractSurvivorCard.getCorrectPlaceholderImage;

@CardIgnore
public class OctoChoiceCard extends CustomCard {
    private static final int COST = -2;
    public AbstractGameAction onPick;

    public OctoChoiceCard(String id, String name, String IMG, String description, AbstractGameAction action) {
        super(id, name, getCorrectPlaceholderImage(CardType.SKILL, IMG), COST, description, CardType.SKILL, CardColor.COLORLESS, CardRarity.SPECIAL, CardTarget.NONE);
        onPick = action;
    }

    public OctoChoiceCard(String id, String name, String IMG, String description, int damage, int block) {
        super(id, name, getCorrectPlaceholderImage(CardType.SKILL, IMG), COST, description, CardType.SKILL, CardColor.COLORLESS, CardRarity.SPECIAL, CardTarget.NONE);
        baseDamage = damage;
        baseBlock = block;
    }

    public OctoChoiceCard(String id, String name, String IMG, String description, int damage, int block, int magic) {
        super(id, name, getCorrectPlaceholderImage(CardType.SKILL, IMG), COST, description, CardType.SKILL, CardColor.COLORLESS, CardRarity.SPECIAL, CardTarget.NONE);
        baseDamage = damage;
        baseBlock = block;
        baseMagicNumber = magicNumber = magic;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(onPick);
    }

    @Override
    public void upgrade() {

    }
}