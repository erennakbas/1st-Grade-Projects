import java.util.*;
import java.io.*;
public class Main {
    public static HashMap<String, Piece> piecesOfZorde= new HashMap<String, Piece>();
    public static HashMap<String, Piece> piecesOfCalliance= new HashMap<String, Piece>();
    public static Boolean boundaryError;
    public static Boolean didItMove=false;

    //Method for executing moving sequence
    public static void executeMoveSequence(String[] movingSequence, Boolean enemyFound, Boolean friendFound, Boolean fightToDeath,String[][] board, String pieceID, Piece thePiece, HashMap<String, Piece> friendPieces, HashMap<String, Piece> enemyPieces)
    {
        didItMove=false;
        for (int i=1; i<movingSequence.length; i+=2)
        {
            //if it doesn't try to go outside of board.
            if (0<=thePiece.positionA+Integer.valueOf(movingSequence[i-1]) && thePiece.positionA+Integer.valueOf(movingSequence[i-1])<=board[0].length && 0<=thePiece.positionB+Integer.valueOf(movingSequence[i]) && thePiece.positionB+Integer.valueOf(movingSequence[i])<=board[0].length)
            {
                String piece=""; 
                boolean deletePiece=false;
                //Fight to death
                for (Map.Entry<String, Piece> me : enemyPieces.entrySet()) {
                    if (me.getValue().positionA==thePiece.positionA+Integer.valueOf(movingSequence[i-1]) && me.getValue().positionB==thePiece.positionB+Integer.valueOf(movingSequence[i]))
                    {   
                        int tempHealth= me.getValue().health;
                        me.getValue().health-=thePiece.health+thePiece.AP;
                        if ((tempHealth-thePiece.AP)>=0) thePiece.health-=(tempHealth-thePiece.AP); //to avoid substracting negative value.
                        piece=me.getKey();
                        enemyFound=true;
                        if (me.getValue().health<=0)
                        {
                        deletePiece=true; //boolean for deleting the defender outside of loop to avoid crashing 
                        board[me.getValue().positionB][me.getValue().positionA]="  ";//update board
                        }
                        if (thePiece.health<=0)
                        {
                        friendPieces.remove(pieceID); //delete the attacker
                        board[thePiece.positionB][thePiece.positionA]="  ";//update board
                        }
                        else
                        fightToDeath=true;
                        break;
                    }
                }
                if (deletePiece) enemyPieces.remove(piece);//delete the defender.
                for (Map.Entry<String, Piece> me : friendPieces.entrySet()) {
                    if (me.getValue().positionA==thePiece.positionA+Integer.valueOf(movingSequence[i-1]) && me.getValue().positionB==thePiece.positionB+Integer.valueOf(movingSequence[i]))
                    {
                        friendFound=true;
                        break;
                    }
                }
            //if there is no enemy on place the attacker tries to go or attacker did a fight to death and won it.
            if ((friendFound==false && enemyFound==false) || (enemyFound==true && fightToDeath==true)) 
            {   
                //do the necessary operations.
                board[thePiece.positionB][thePiece.positionA]="  ";
                thePiece.Move(Integer.valueOf(movingSequence[i-1]), Integer.valueOf(movingSequence[i]));
                didItMove=true;
                board[thePiece.positionB][thePiece.positionA]=pieceID;
                friendPieces.remove(pieceID);
                friendPieces.put(pieceID,thePiece);
            //specific range attack for elves.
            if (thePiece instanceof Elf)
            {
                if (i==movingSequence.length-1 && enemyFound==false)
                    {
                    for (int j=thePiece.positionA-2; j<=thePiece.positionA+2; j++)
                    {
                        for (int k=thePiece.positionB-2; k<=thePiece.positionB+2; k++)
                        {
                            String enemyID="";
                            boolean delete=false;
                            for (Map.Entry<String, Piece>me : enemyPieces.entrySet()) {
                                if (me.getValue().positionA==j && me.getValue().positionB==k)
                                {
                                    enemyID=me.getKey();
                                    //attack to defender.
                                    thePiece.Attack(me.getValue());
                                    if (me.getValue().health<=0)
                                    {
                                    delete=true; //Boolean for deleting enemy
                                    board[me.getValue().positionB][me.getValue().positionA]="  "; //update the board.
                                    }
                                    break;
                                }
                            }
                            if (delete) enemyPieces.remove(enemyID); //
                        }
                    }
                }
            //attack normal if elf is not in his/her last step.
                else if (enemyFound==false)
            {
                for (int j=thePiece.positionA-1; j<=thePiece.positionA+1; j++)
                {
                    for (int k=thePiece.positionB-1; k<=thePiece.positionB+1; k++)
                    {
                        String zordeID="";
                        boolean delete=false;
                        for (Map.Entry<String, Piece>me : enemyPieces.entrySet()) {
                            if (me.getValue().positionA==j && me.getValue().positionB==k)
                            {
                                zordeID=me.getKey();
                                thePiece.Attack(me.getValue());
                                if (me.getValue().health<=0)
                                {
                                delete=true;
                                board[me.getValue().positionB][me.getValue().positionA]="  ";
                                }
                                break;
                            }
                        }
                        if (delete) piecesOfZorde.remove(zordeID);
                    }
                }
            }
            }
            //Attacking code if it's human,troll or ork and they are in their last step or if it's goblin or dwarf
            if ((((thePiece instanceof Troll || thePiece instanceof Ork || thePiece instanceof Human) && i==movingSequence.length-1) || (thePiece instanceof Goblin || thePiece instanceof Dwarf)) && enemyFound==false){
            for (int j=thePiece.positionA-1; j<=thePiece.positionA+1; j++)
            {
                for (int k=thePiece.positionB-1; k<=thePiece.positionB+1;k++)
                {
                    String enemyID="";
                    boolean delete=false;
                    for (Map.Entry<String, Piece>me : enemyPieces.entrySet()) {

                        if (me.getValue().positionA==j && me.getValue().positionB==k)
                        {
                            enemyID=me.getKey();
                            thePiece.Attack(me.getValue());
                            if (me.getValue().health<=0) //if enemy died.
                            {
                            delete=true; //Boolean for deleting enemy piece.
                            board[me.getValue().positionB][me.getValue().positionA]="  "; //update board.
                            }
                            break;
                        }
                    }
                    if (delete) //delete enemy.
                    enemyPieces.remove(enemyID);
                }
            }
        }
        }
        else if (enemyFound==true) break;
         //if enemy is found on the square that attacker tries to go then stop moving sequence which means fight to death happened
    }
        else
        {
            Main.boundaryError=true; //if there is boundary error then stop moving sequence.
            break;
        }
        }
    }

    public static void main(String[] args) throws Exception {
        try{
        //Do the file io operations.
        FileWriter output=new FileWriter(args[2]);
        FileReader commandsFile= new FileReader(args[1]);
        BufferedReader brForCommands=new BufferedReader(commandsFile);
        BoardMaker myMaker= new BoardMaker();
        myMaker.readInitial(args[0]);
        String[][] board=myMaker.makeBoard();//Create the board.
        output.write(myMaker.printBoard(board));//print initial board.
        String s=brForCommands.readLine();
        while (s!=null)
        {   
            //Necessary booleans and other data types. 
            boolean fightToDeath=false;
            String pieceID=s.split(" ")[0];
            String[] movingSequence=s.split(" ")[1].split(";");
            boundaryError=false;
            boolean moveCountError=false;
            boolean enemyFound=false;
            boolean friendFound=false;
            //if it's dwarf
            if (pieceID.charAt(0)=='D')
            {
                Dwarf dwarf=(Dwarf) piecesOfCalliance.get(pieceID);
                //Checking move sequence
                if (movingSequence.length==Constants.dwarfMaxMove*2)
                {
                    executeMoveSequence(movingSequence, enemyFound, friendFound, fightToDeath, board, pieceID, dwarf, piecesOfCalliance, piecesOfZorde);
                }
                else moveCountError=true;
            }
            //if it's human
            else if (pieceID.charAt(0)=='H' && movingSequence.length==Constants.humanMaxMove*2)
            {
                Human human=(Human) piecesOfCalliance.get(pieceID);
                //Checking move sequence
                if (movingSequence.length==Constants.humanMaxMove*2)
                {
                    executeMoveSequence(movingSequence, enemyFound, friendFound, fightToDeath, board, pieceID, human,piecesOfCalliance, piecesOfZorde);
                }
                else moveCountError=true;
            }
            //if it's elf
            else if (pieceID.charAt(0)=='E')
            {   
                Elf elf=(Elf) piecesOfCalliance.get(pieceID);
                //Checking move sequence
                if (movingSequence.length==Constants.elfMaxMove*2)
                {
                    executeMoveSequence(movingSequence, enemyFound, friendFound, fightToDeath, board, pieceID, elf, piecesOfCalliance, piecesOfZorde);
                }
                else moveCountError=true;
            }
            //if it's troll
            else if (pieceID.charAt(0)=='T' && movingSequence.length==Constants.trollMaxMove*2)
            {
                Troll troll=(Troll) piecesOfZorde.get(pieceID);
                //Checking move sequence
                if (movingSequence.length==Constants.trollMaxMove*2)
                {
                    executeMoveSequence(movingSequence, enemyFound, friendFound, fightToDeath, board, pieceID, troll, piecesOfZorde, piecesOfCalliance);
                }
                else moveCountError=true;
            }
            //if it's goblin
            else if (pieceID.charAt(0)=='G' && movingSequence.length==Constants.goblinMaxMove*2)
            {
                Goblin goblin=(Goblin) piecesOfZorde.get(pieceID);
                //Checking move sequence
                if (movingSequence.length==Constants.goblinMaxMove*2)
                {
                    executeMoveSequence(movingSequence, enemyFound, friendFound, fightToDeath, board, pieceID, goblin, piecesOfZorde, piecesOfCalliance);
                }
                else moveCountError=true;
            }
            // if it's ork
            else if (pieceID.charAt(0)=='O' && movingSequence.length==Constants.orkMaxMove*2)
            {
                Ork ork=(Ork) piecesOfZorde.get(pieceID);
                //Checking move sequence
                if (movingSequence.length==Constants.orkMaxMove*2)
                {
                    //Code for ork's healing ability.
                    for (int i=1; i<movingSequence.length; i+=2)
                    {  
                        if (0<=ork.positionA+Integer.valueOf(movingSequence[i-1]) && ork.positionA+Integer.valueOf(movingSequence[i-1])<=board[0].length && 0<=ork.positionB+Integer.valueOf(movingSequence[i]) && ork.positionB+Integer.valueOf(movingSequence[i])<=board[0].length)
                        {
                            for (int j=ork.positionA-1; j<=ork.positionA+1; j++)
                            {
                                for (int k=ork.positionB-1; k<=ork.positionB+1;k++)
                                {
                                    for (Map.Entry<String, Piece>me : Main.piecesOfZorde.entrySet()) {
                                        if (me.getValue().positionA==j && me.getValue().positionB==k)
                                        {
                                            me.getValue().health+=ork.healPoints;
                                            if (me.getValue().health>me.getValue().initialHP) //For ensuring piece not to overpass his max hp.
                                            {
                                                me.getValue().health=me.getValue().initialHP;
                                            }
                                            break;
                                        }
                                    }
                                }
                            }
                            executeMoveSequence(movingSequence, enemyFound, friendFound, fightToDeath, board, pieceID, ork, piecesOfZorde, piecesOfCalliance);
                        }
                        else Main.boundaryError=true;
                    }
                }
                else moveCountError=true;
            }
            if (((boundaryError==true && didItMove==true) || boundaryError==false) && moveCountError==false) //print board if conditions are provided to error types.
            output.write(myMaker.printBoard(board));
            if (boundaryError) output.write("Error : Game board boundaries are exceeded. Input line ignored.\n\n"); //Error message for boundary error.
            else if (moveCountError) output.write("Error : Move sequence contains wrong number of move steps. Input line ignored.\n\n"); //Error message for move count error.
            if (piecesOfCalliance.size()==0 || piecesOfZorde.size()==0)
            {
                output.write("\nGame Finished\n");
                if (piecesOfCalliance.size()==0) output.write("Zorde Wins"); // if calliance has no piece left then zorde wins.
                else output.write("Calliance Wins"); //else calliance wins.
                break;
            }
            s=brForCommands.readLine();
        }
        brForCommands.close();
        output.close();
    }
    catch (Exception ex){
    System.out.println(ex);
    ex.printStackTrace();
}
}
}