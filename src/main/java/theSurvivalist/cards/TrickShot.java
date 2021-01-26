package theSurvivalist.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.BranchingUpgradesCard;

public class TrickShot extends AbstractSurvivorCard {

    public final static String ID = makeID("TrickShot");

    //stupid intellij stuff ATTACK, ALL_ENEMY, UNCOMMON

    private static final int DAMAGE = 13;
    private static final int UPG_DAMAGE = 2;

    public TrickShot() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        baseDamage = DAMAGE;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractMonster q = AbstractDungeon.getRandomMonster();
        dmg(q, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        if (q.getIntentBaseDmg() < 0) {
            AbstractMonster r = AbstractDungeon.getRandomMonster();
            dmg(r, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        }
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPG_DAMAGE);
        }
    }
}