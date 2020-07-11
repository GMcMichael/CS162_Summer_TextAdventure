public class SwordItem extends WeaponItem {

    private static int baseDamage = 2;
    private static int baseWorth = 10;
    private static int baseDurability = 10;
    private static int baseWeight = 1;
    private static String[] descs = {"dull", "common", "sharp", "serrated"};

    public SwordItem(int rarity){
        setType(descs[rarity] + "Sword");
        setDamage(baseDamage * rarity);
        setDesc("A " + descs[rarity] + " sword.");
        setWorth(baseWorth * rarity);
        setDurability(baseDurability * rarity);
        setWeight(baseWeight * rarity);
    }

}
