public class Troll extends Piece{
    
    public Troll(int position1, int position2)
    {
        super(position1, position2);
        AP=Constants.trollAP;
        maxMove=Constants.trollMaxMove;
        this.health=150;
        this.initialHP=Constants.trollInitialHP;
    }
    public void Attack(Piece calliancePiece)
    {
        calliancePiece.health-=this.AP;
    }
}
