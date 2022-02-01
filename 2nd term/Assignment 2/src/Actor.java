public class Actor extends Performer {
    public String height;
    public Actor(String uniqueID,String name, String surname, String country, String height)
    {
        super(uniqueID,name,surname,country);
        this.height=height;
    }
}
