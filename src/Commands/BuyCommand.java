public class BuyCommand extends Command {

    public BuyCommand() {
        super("buy");
    }

    @Override
    boolean run() {
        NPCharacter NPCharacter = Main.getPlayer().getInteracting();
        if(NPCharacter != null) {
            NPCharacter.sell(getModifier());
            return true;
        }
        return false;
    }
}
