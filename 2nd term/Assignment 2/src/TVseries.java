
public class TVseries extends Film {
    
    public String[] genres;
    public String[] writers;
    public String startDate, endDate;
    public String seasons, episodes;

    public TVseries(String uniqueFilmID, String filmTitle, String language, String[] newDirectors, String runtime, String country, String[] newPerformers, String[] newGenres, String[] newWriters, String startDate, String endDate, String seasons, String episodes )
    {
        super(uniqueFilmID, filmTitle, language, newDirectors, runtime, country, newPerformers);
        genres=newGenres;
        writers=newWriters;
        this.startDate=startDate;
        this.endDate=endDate;
        this.seasons= seasons;
        this.episodes=episodes;
    }
    public String ShowFilmType()
    {
        return "TVSeries";
    }
}
