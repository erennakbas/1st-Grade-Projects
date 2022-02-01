public class Writer extends Artist{
    public String writingStyle;
    public Writer(String uniqueID,String name,String surname,String country, String writingStyle)
    {
        super(uniqueID,name,surname,country);
        this.writingStyle=writingStyle;
    }
}
