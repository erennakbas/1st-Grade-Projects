public class Human extends Piece {
    public Human(int position1,int position2)
    {
        super(position1, position2);
        this.initialHP=Constants.humanInitialHP;
        this.AP=Constants.humanAP;
        this.maxMove=Constants.humanMaxMove;
        this.health=100;
    }
    public void Attack(Piece zordePiece)
    {
        zordePiece.health-=this.AP;
    }
}
