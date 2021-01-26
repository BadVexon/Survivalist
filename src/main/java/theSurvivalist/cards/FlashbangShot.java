package theSurvivalist.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.BranchingUpgradesCard;

public class FlashbangShot extends AbstractSurvivorCard {

    public final static String ID = makeID("FlashbangShot");

    //stupid intellij stuff ATTACK, ALL_ENEMY, UNCOMMON

    private static final int DAMAGE = 1;
    private static final int MAGIC = 8;
    private static final int UPG_MAGIC = 4;

    public FlashbangShot() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
        isMultiDamage = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        allDmg(AbstractGameAction.AttackEffect.FIRE);
        for (AbstractMonster q : AbstractDungeon.getCurrRoom().monsters.monsters) {
            markEnemy(q, magicNumber);
        }
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPG_MAGIC);
        }
    }
}