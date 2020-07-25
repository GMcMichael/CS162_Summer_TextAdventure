public class QuestItem extends Item{

    private Quest quest;

    public QuestItem(Quest quest){
        this.quest = quest;
        setType("questItem");
    }

}
