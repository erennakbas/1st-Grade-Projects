public class ChildActor extends Performer {
    public String age;
    public ChildActor(String uniqueID,String name, String surname, String country, String age)
    {
        super(uniqueID,name,surname,country);
        this.age=age;
    }
}
