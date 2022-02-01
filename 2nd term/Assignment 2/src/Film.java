import java.util.*;
public class Film {
    public String uniqueFilmID, filmTitle, language, country;
    public String runtime;
    String[] directors;
    String[] performers;
    public double rating=0;
    public double filmsRate=0;
    public HashMap<String, Double> id_Rate=new HashMap<String, Double>();
    public Film(String uniqueFilmID, String filmTitle, String language, String[] newDirectors, String runtime, String country, String[] newPerformers)
    {
        this.uniqueFilmID=uniqueFilmID;
        this.filmTitle=filmTitle;
        this.language= language;
        this.runtime=runtime;
        this.directors=newDirectors;
        this.performers=newPerformers;
        this.country=country;
    }
    public void Rate(double rate, String id)
    {   if (!id_Rate.containsKey(id))
        {
        rating+=rate;
        id_Rate.put(id,rate);
        filmsRate=rating/id_Rate.size();
        }
    }
    public double showRate()
    {
        return filmsRate;
        
    }
    public void EditRate(double rate,String id)
    {
        Double oldRate= this.id_Rate.get(id);
        rating-=oldRate;
        rating+=rate;
        this.id_Rate.remove(id);
        this.id_Rate.put(id,rate);
        filmsRate=rating/id_Rate.size();
    }
    public String ShowFilmType()
    {
        return "Film";
    }



}
