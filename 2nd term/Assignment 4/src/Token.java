public class Token {
    public int value;
    public String tokenID;
    public String itemType;
    public int index;
    public static int callingTime=0;
    Token(String tokenID, String itemType,int value)
    {
        this.value = value;
        this.tokenID=tokenID;
        this.itemType=itemType;
        this.index=callingTime;
        callingTime++;
    }
    //Methods to use when it's needed to sort token priority queue.
    public int getValue()
    {
        return value;
    }
    public int getIndex()
    {
        return -index;
    }
}
