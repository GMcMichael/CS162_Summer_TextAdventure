import java.util.ArrayList;

public class InteractCommand extends Command{

    public InteractCommand() {
        super("interact");
    }

    public InteractCommand(String activator){
        super(activator);
    }

    @Override
    boolean run() {
        return false;
    }
}
