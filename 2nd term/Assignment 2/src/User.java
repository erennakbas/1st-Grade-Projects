import java.util.*;
public class User extends Person{
    LinkedHashMap<String, Double> ratedFilms= new LinkedHashMap<String, Double>();
    public User( String uniqueID,String name,String surname,String country)
    {
        super(uniqueID,name,surname,country);
    }
}
