public class Ork extends Piece{
    public int healPoints;
    public Ork(int position1, int position2)
    {
        super(position1, position2);
        this.AP=Constants.orkAP;
        this.maxMove=Constants.orkMaxMove;
        this.healPoints=Constants.orkHealPoints;
        this.health=200;
        this.initialHP=Constants.orkInitialHP;
    }
    public void Attack(Piece calliancePiece)
    {
        calliancePiece.health-=this.AP;
    }
}
