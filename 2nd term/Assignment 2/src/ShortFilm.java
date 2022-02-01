
public class ShortFilm extends Film {
    public String[] genre;
    public String releaseDate;
    public String[] writers;

    public ShortFilm(String uniqueFilmID, String filmTitle, String language, String[] newDirectors, String runtime, String country, String[] newPerformers, String[] newGenre, String releaseDate, String[] newWriters)
    {
        super(uniqueFilmID, filmTitle, language, newDirectors, runtime, country, newPerformers);
        this.releaseDate=releaseDate;
        this.writers= newWriters;
        this.genre=newGenre;
    }
    public String ShowFilmType()
    {
        return "ShortFilm";
    }
}
