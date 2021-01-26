package theSurvivalist.cards;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.mod.stslib.actions.common.StunMonsterAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.cards.curses.Injury;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.BufferPower;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theSurvivalist.powers.NextTurnIntangiblePower;

public class ComplexMutation extends AbstractSurvivorCard {

    public final static String ID = makeID("ComplexMutation");

    //stupid intellij stuff ATTACK, ENEMY, COMMON

    private static final int DAMAGE = 5;
    private static final int BLOCK = 12;

    public ComplexMutation() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        baseBlock = BLOCK;
        exhaust = true;
    }

    public boolean doThing = false;

    @Override
    public void renderCardPreview(SpriteBatch sb) {
        if (doThing && cardsToPreview != null) {
            super.renderCardPreview(sb);
        }
    }

    private OctoChoiceCard cardsList(AbstractMonster.Intent i) {
        switch (i) {
            case ATTACK:
                return new OctoChoiceCard("notImportant", "Complex Mutation - ATTACK", ID, "Gain 1 Buffer. NL Add an *Injury into your discard pile.", damage, block);
            case ATTACK_BUFF:
                return new OctoChoiceCard("notImportant", "Complex Mutation - ATTACK+BUFF", ID, "Gain !B! Block. NL Apply 5 Poison. NL Add an *Injury into your discard pile.", damage, block);
            case ATTACK_DEBUFF:
                return new OctoChoiceCard("notImportant", "Complex Mutation - ATTACK+DEBUFF", ID, "Gain !B! Block. NL Gain 1 Artifact. NL Add an *Injury into your discard pile.", damage, block);
            case ATTACK_DEFEND:
                return new OctoChoiceCard("notImportant", "Complex Mutation - ATTACK+DEFEND", ID, "Gain !B! Block. NL Apply 5 Poison. NL Add an *Injury into your discard pile.", damage, block);
            case BUFF:
                return new OctoChoiceCard("notImportant", "Complex Mutation - BUFF", ID, "Enemy loses 4 Strength. NL Add an *Injury into your discard pile.", damage, block);
            case DEBUFF:
            case STRONG_DEBUFF:
                return new OctoChoiceCard("notImportant", "Complex Mutation - DEBUFFS", ID, "Deal !D! damage 3 times. NL Gain 1 Artifact. NL Add an *Injury into your discard pile.", damage, block);
            case DEBUG:
                return null;
            case DEFEND:
                return new OctoChoiceCard("notImportant", "Complex Mutation - DEFEND", ID, "Deal !D! damage. NL Apply 15 Poison. NL Add an *Injury into your discard pile.", damage, block);
            case DEFEND_DEBUFF:
                return new OctoChoiceCard("notImportant", "Complex Mutation - DEFEND+DEBUFF", ID, "Gain 1 Artifact. NL Deal !D! damage. NL Add an *Injury into your discard pile.", damage, block);
            case DEFEND_BUFF:
                return new OctoChoiceCard("notImportant", "Complex Mutation - DEFEND+BUFF", ID, "Apply 8 Poison. NL Enemy loses 2 Strength.", damage, block);
            case ESCAPE:
                return new OctoChoiceCard("notImportant", "Complex Mutation - ESCAPING", ID, "*Stun the enemy for 1 turn. NL Add an *Injury into your discard pile.", damage, block);
            case MAGIC:
                return null;
            case NONE:
                return null;
            case SLEEP:
                return new OctoChoiceCard("notImportant", "Complex Mutation - SLEEP", ID, "Deal !D! damage 3 times. NL Gain !B! Block. NL Add an *Injury into your discard pile.", damage, block);
            case STUN:
                return new OctoChoiceCard("notImportant", "Complex Mutation - STUNNED", ID, "Deal !D! damage 3 times. NL Gain !B! Block. NL Add an *Injury into your discard pile.", damage, block);
            case UNKNOWN:
                return new OctoChoiceCard("notImportant", "Complex Mutation - ???", ID, "Next turn, gain 1 Intangible. NL Add an *Injury into your discard pile.", damage, block);
        }
        return null;
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        super.calculateCardDamage(mo);
        if (mo == null) {
            cardsToPreview = null;
        } else {
            cardsToPreview = cardsList(mo.intent);
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        switch (m.intent) {
            case ATTACK:
                applyToSelf(new BufferPower(p, 1));
                break;
            case ATTACK_BUFF:
            case ATTACK_DEFEND:
                blck();
                applyToEnemy(m, new PoisonPower(m, p, 5));
                break;
            case ATTACK_DEBUFF:
                blck();
                applyToSelf(new ArtifactPower(p, 1));
                break;
            case BUFF:
                applyToEnemy(m, new StrengthPower(m, -4));
                break;
            case DEBUFF:
            case STRONG_DEBUFF:
                for (int i = 0; i < 3; i++) {
                    dmg(m, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
                }
                applyToSelf(new ArtifactPower(p, 1));
                break;
            case DEBUG:
                break;
            case DEFEND:
                dmg(m, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
                applyToEnemy(m, new PoisonPower(m, p, 15));
                break;
            case DEFEND_DEBUFF:
                applyToSelf(new ArtifactPower(p, 1));
                dmg(m, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
                break;
            case DEFEND_BUFF:
                applyToEnemy(m, new PoisonPower(m, p, 8));
                applyToEnemy(m, new StrengthPower(m, -2));
                break;
            case ESCAPE:
                atb(new StunMonsterAction(m, p));
            case MAGIC:
                break;
            case NONE:
                break;
            case SLEEP:
            case STUN:
                for (int i = 0; i < 3; i++) {
                    dmg(m, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
                }
                blck();
                break;
            case UNKNOWN:
                applyToSelf(new NextTurnIntangiblePower(1));
                break;
        }
        addToBot(new MakeTempCardInDiscardAction(new Injury(), 1));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(1);
        }
    }
}