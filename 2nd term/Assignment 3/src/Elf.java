public class Elf extends Piece {
	public int rangedAP;
    public Elf(int position1,int position2)
    {
        super(position1, position2);
        this.AP=Constants.elfAP;
        this.rangedAP=Constants.elfRangedAP;
        this.maxMove=Constants.elfMaxMove;
        this.health=70;
        this.initialHP=Constants.elfInitialHP;
    }
    public void Attack(Piece zordePiece)
    {
        zordePiece.health-=this.AP;
    }
}
