public abstract class Command {

    private String activator;
    private String modifier;

    public Command(String activator){
        this.activator = activator;
        Controller.addCommand(activator, this);
    }

    abstract void run();

    public void setModifier(String modifier){
        if(modifier != null) this.modifier = modifier;
    }
}
