public class Article {
    private String paperid,name,publisherName, publisherYear;
    public Article(String Paperid, String Name, String PublisherName, String PublisherYear){
        paperid=Paperid;
        name=Name;
        publisherName= PublisherName;
        publisherYear=PublisherYear;
    }
    public String getPaperID()
    {
        return paperid;
    }
    public String getPublisherYear()
    {
        return publisherYear;
    }
    public String getPublisherName()
    {
        return publisherName;
    }
    public String getName()
    {
        return name;
    }
    public void setPaperID(String newID)
    {
        paperid=newID;
    }
    public void setName(String newName)
    {
        name=newName;
    }
    public void setPublisherYear(String newPublisherYear)
    {
        publisherYear=newPublisherYear;
    }
    public void setPublisherName(String newPublisherName)
    {
        publisherName=newPublisherName;
    }
}
