

public class FeatureFilm extends Film{
   
    public String[] genre;
    public String releaseDate;
    public String budget;
    public String[] writers;

    public FeatureFilm(String uniqueFilmID, String filmTitle, String language, String[]newDirectors, String runtime, String country, String[] newPerformers,String[] newGenre, String releaseDate, String[] newWriters, String budget )
    {
        super(uniqueFilmID, filmTitle, language, newDirectors, runtime, country, newPerformers);
        this.releaseDate=releaseDate;
        this.budget= budget;
        this.writers= newWriters;
        this.genre=newGenre;
    }
    public String ShowFilmType()
    {
        return "FeatureFilm";
    }
}