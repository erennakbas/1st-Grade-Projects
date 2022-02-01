public class Director extends Artist {
    public String agent;
    public Director(String uniqueID,String name,String surname,String country,  String agent)
    {
        super(uniqueID,name,surname,country);
        this.agent=agent;
    }
}
