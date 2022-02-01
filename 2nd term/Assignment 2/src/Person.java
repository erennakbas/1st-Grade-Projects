import java.util.*;
public class Person {
    public String uniqueID,name,surname,country;
    HashMap<String, Double> ratedFilms = new HashMap<String, Double>();
    public Person(String uniqueID,String name, String surname, String country)
    {   
        this.uniqueID=uniqueID;
        this.name=name;
        this.surname=surname;
        this.country=country;
        
    }
    
}
