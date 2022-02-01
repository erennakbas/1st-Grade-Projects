import java.io.*;
import java.util.*;
public class Main {
    
    public static void main(String args[]) throws IOException
    {
        //Setting up the files.
        FileReader taskFile= new FileReader(args[3]), partsFile= new FileReader(args[0]),tokensFile=new FileReader(args[2]),itemsFile=new FileReader(args[1]);
        FileWriter output=new FileWriter(args[4]);
        BufferedReader brForTasks=new BufferedReader(taskFile), brForParts=new BufferedReader(partsFile), brForTokens= new BufferedReader(tokensFile), brForItems= new BufferedReader(itemsFile);
        String s=brForParts.readLine();
        Stack<Part> partStack= new Stack<Part>();
        Comparator<Token> tokenSorter= Comparator.comparing(Token::getValue).thenComparing(Token::getIndex); //Comparator for sorting tokens.
        //Reading files, instantiating objects and adding them to relevant stack-queues.
        while (s!=null)
        {
            partStack.push(new Part(s));
            s=brForParts.readLine();
        }
        brForParts.close();

        s=brForItems.readLine();
        while (s!=null)
        {
            String[] parameters= s.split(" ");
            for (Part p: partStack)
                if (p.name.equals(parameters[1]))
                    p.itemStack.push(new Item(parameters[0],parameters[1]));
            
            s=brForItems.readLine();
        }
        brForItems.close();

        s=brForTokens.readLine();
        PriorityQueue<Token> tokenPQueue= new PriorityQueue<Token>();
        while (s!=null)
        {
            String[] parameters= s.split(" ");
            tokenPQueue.enqueue(new Token(parameters[0],parameters[1], Integer.valueOf(parameters[2])));
            s=brForTokens.readLine();
        }
        brForTokens.close(); //Reading files finishes here.
        //Sorting priority queue.
        tokenPQueue.sort(tokenSorter);
        tokenPQueue.reverse();

        s=brForTasks.readLine();
        //Read tasks.
        while (s!=null)
        {
            String[] parameters= s.split("\t");
            //Buy operation.
            if (parameters[0].equals("BUY"))
            {
                for(int i=1; i<parameters.length; i++)
                {
                    String itemType= parameters[i].split(",")[0];
                    Integer itemCount= Integer.valueOf(parameters[i].split(",")[1]);
                    int itemCountForTokens=itemCount;
                    for (int tIndex=0; tIndex<tokenPQueue.size(); tIndex++)
                    {
                        //if relevant token found.
                        if (itemType.equals(tokenPQueue.get(tIndex).itemType))
                        {
                            //if token won't be zero after the process then change it's value and index for sorting priority queue.
                        if (tokenPQueue.get(tIndex).value-itemCountForTokens>0)
                        {   
                            Token.callingTime++;
                            tokenPQueue.get(tIndex).value-=itemCountForTokens;
                            tokenPQueue.get(tIndex).index=Token.callingTime;
                            itemCountForTokens=0;
                            tokenPQueue.enqueue(tokenPQueue.get(tIndex));
                            tokenPQueue.dequeue(tIndex);
                        }
                            //if token will be zero or minus value then dequeue it.
                        else if ( tokenPQueue.get(tIndex).value-itemCountForTokens<=0)
                        {
                            itemCountForTokens-=tokenPQueue.get(tIndex).value;
                            tokenPQueue.dequeue(tIndex);
                            tIndex--;
                        }
                        }
                    //After the process sort the token priority queue.
                    if (itemCountForTokens<=0)
                    {
                        tokenPQueue.sort(tokenSorter);
                        tokenPQueue.reverse();
                        break;
                    }
                    }
                    //Pop items from item stack until the wanted value of item buying finishes.
                    for (Part p: partStack)
                    {
                        if (itemType.equals(p.name))
                        {
                        for (int j=0; j<itemCount;j++)
                            p.itemStack.pop();
                        }
                    }
                }
            }

            else // if it's PUT operation.
            {
                for(int i=1; i<parameters.length; i++)
                {
                    String[] itemParameters=parameters[i].split(",");
                    String itemType= itemParameters[0]; 
                    //Add items to relevant part.
                    for (Part p: partStack)
                        for (int j=1; j<itemParameters.length;j++)
                           if (itemType.equals(p.name))
                                p.itemStack.push(new Item(itemParameters[j], itemType));               
                }
            }
            s=brForTasks.readLine();
        }
        partStack.reverse();
        for (Part p: partStack) //write output.
        {
            output.write(p.name+":"+"\n");
            for (Item i: p.itemStack)
                output.write(i.itemID+"\n");
            output.write("---------------"+"\n");
        }
        output.write("Token Box:"); //write output.
        tokenPQueue.reverse();
        for (Token t: tokenPQueue)
            output.write("\n"+t.tokenID+" "+ t.itemType+ " "+ t.value);
        brForTasks.close();
        output.close();
    }
}
