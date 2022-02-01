public class StuntPerformer extends Performer{
    public String height;
    public String[] realID;
    public StuntPerformer(String uniqueID,String name, String surname, String country, String height, String[] realID)
    {
        
        super(uniqueID,name,surname,country);
        this.height=height;
        this.realID=realID;
    }
}
