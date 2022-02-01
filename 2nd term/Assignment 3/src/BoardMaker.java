import java.io.*;
import java.util.*;
public class BoardMaker {
    public int boardSize;
    public String repeat(int count, String with) { //method for printing edges.
        return new String(new char[count]).replace("\0", with);
    }

    public void readInitial(String initialTxt)
    {
        try{
        FileReader initialFile= new FileReader(initialTxt);
        BufferedReader brForBoard=new BufferedReader(initialFile);
        String s= brForBoard.readLine();
        while (s!=null)
        {   
            //reading the file and instantiating objects.
            if (s.equals("BOARD"))
            {
                s=brForBoard.readLine();
                boardSize=Integer.valueOf(s.split("x")[0]);
            }
            if (s.equals("CALLIANCE"))
            {   
                s=brForBoard.readLine();
                while (!s.equals("ZORDE"))
                {   
                    String parameters[]=s.split(" ");
                    if (parameters[0].equals("ELF"))
                        Main.piecesOfCalliance.put(parameters[1],new Elf(Integer.valueOf(parameters[2]),Integer.valueOf(parameters[3])));
                    else if (parameters[0].equals("HUMAN"))
                        Main.piecesOfCalliance.put(parameters[1],new Human(Integer.valueOf(parameters[2]),Integer.valueOf(parameters[3])));
                    else if (parameters[0].equals("DWARF"))
                        Main.piecesOfCalliance.put(parameters[1],new Dwarf(Integer.valueOf(parameters[2]),Integer.valueOf(parameters[3])));
                    s=brForBoard.readLine();
                }
            }
            if (s.equals("ZORDE"))
            {
                s=brForBoard.readLine();
                while (s!=null)
                {  
                    String parameters[]=s.split(" ");
                    if (parameters[0].equals("GOBLIN"))
                        Main.piecesOfZorde.put(parameters[1],new Goblin(Integer.valueOf(parameters[2]),Integer.valueOf(parameters[3])));
                    else if (parameters[0].equals("TROLL"))
                        Main.piecesOfZorde.put(parameters[1],new Troll(Integer.valueOf(parameters[2]),Integer.valueOf(parameters[3])));
                    else if (parameters[0].equals("ORK"))
                        Main.piecesOfZorde.put(parameters[1],new Ork(Integer.valueOf(parameters[2]),Integer.valueOf(parameters[3])));
                    s=brForBoard.readLine();
                }
            }
            s=brForBoard.readLine();
        }
        brForBoard.close();
    }
        catch (Exception ex)
        {
            System.out.println(ex);
            ex.printStackTrace();
        }
    }

    public String[][] makeBoard() //Create the board.
    {
        String[][] board= new String[boardSize][boardSize];
        for (int i=0; i<boardSize; i+=1)
        {
            for (int j=0; j<boardSize; j+=1)
            {
                board[i][j]="  ";
            }
        }
        for (Map.Entry<String, Piece> me : Main.piecesOfCalliance.entrySet()) {
           board[me.getValue().positionB][me.getValue().positionA]=me.getKey();
          }
        for (Map.Entry<String, Piece> me : Main.piecesOfZorde.entrySet()) {
            board[me.getValue().positionB][me.getValue().positionA]=me.getKey();
        }
        return board;
    }

    public String printBoard(String[][] board) //Printing board
    {
        int index=0;
        String a="";
        a+=(repeat(board.length*2+2, "*")+"\n");
        for (String[] row: board)
        { 
            a+="*";
            for (String square:row)
            {
                a+=square;
            }
            a+="*\n";
            }
        a+=(repeat(board.length*2+2, "*")+"\n\n");
        //Code for showing piece healths.
        String[] pieceHealths= new String[Main.piecesOfCalliance.size()+Main.piecesOfZorde.size()];
        for (Map.Entry<String, Piece> me : Main.piecesOfZorde.entrySet()) {
            pieceHealths[index]=me.getKey()+"\t"+me.getValue().health+"\t"+"("+me.getValue().initialHP+")\n";
            index+=1;
        }
        for (Map.Entry<String, Piece> me : Main.piecesOfCalliance.entrySet()) {
            pieceHealths[index]=me.getKey()+"\t"+me.getValue().health+"\t"+"("+me.getValue().initialHP+")\n";
            index+=1;
        }
        Arrays.sort(pieceHealths);
        for (String p: pieceHealths) a+=p;
        a+="\n";
        return a;
    }

}
