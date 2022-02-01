

public class Documentary extends Film {
    public String releaseDate;
    public Documentary(String uniqueFilmID, String filmTitle, String language,String[] newDirectors, String runtime, String country, String[] newPerformers, String releaseDate)
    {
        super(uniqueFilmID, filmTitle, language, newDirectors, runtime, country, newPerformers);
        this.releaseDate=releaseDate;

    }
    public String ShowFilmType()
    {
        return "Documentary";
    }
    
}
