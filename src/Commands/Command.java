public abstract class Command {

    private String activator;
    private String modifier;

    public Command(String activator){
        this.activator = activator;
        Controller.addCommand(activator, this);
    }

    abstract boolean run();

    public void setModifier(String modifier){
        this.modifier = modifier;
    }

    public String getModifier(){
        return modifier;
    }
}
