public class Dwarf extends Piece {
    public Dwarf(int position1,int position2)
    {
        super(position1, position2);
        AP=Constants.dwarfAP;
        maxMove=Constants.dwarfMaxMove;
        this.health=120;
        this.initialHP=Constants.dwarfInitialHP;
    }
    public void Attack(Piece zordePiece)
    {
        zordePiece.health-=this.AP;
    }
}
