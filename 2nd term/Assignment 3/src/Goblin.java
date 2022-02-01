public class Goblin extends Piece{
    public Goblin(int position1,int position2)
    {
        super(position1, position2);
        AP=Constants.goblinAP;
        maxMove=Constants.goblinMaxMove;
        this.health=80;
        this.initialHP=Constants.goblinInitialHP;
    }
    public void Attack(Piece calliancePiece)
    {
        calliancePiece.health-=this.AP;
    }
}
