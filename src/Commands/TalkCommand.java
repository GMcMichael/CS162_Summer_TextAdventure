import java.util.ArrayList;

public class TalkCommand extends Command {

    public TalkCommand() {
        super("talk");
    }

    @Override
    boolean run() {
        ArrayList<NPCharacter> NPCharacters = Main.getPlayer().getCurrLocation().getNPCharacters();
        for (NPCharacter ch: NPCharacters) {
            if(ch.getName().equalsIgnoreCase(getModifier())){
                ch.talk();
                Main.waitForPress();
                return true;
            }
        }
        return false;
    }
}
