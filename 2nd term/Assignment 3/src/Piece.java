public abstract class Piece {
    public int AP;
	public int maxMove;
    public int positionA;
    public int positionB;
    public int initialHP;
    public int health;
    public Piece (int position1,int position2)
    {
        positionA=position1;
        positionB=position2;
    }
    public void Move(int moveA, int moveB)
    {
        this.positionA+=moveA;
        this.positionB+=moveB;
    }
    public abstract void Attack(Piece enemyPiece);
}
