import java.io.*;
import java.util.*;

public class Main {
    public static void ListAuthors(classManager theClassManager)
    {   try{
        
            
            FileWriter output=new FileWriter("output.txt",true);
            output.write("----------------------------------------------List---------------------------------------------\n");
        
        for (Author theAuthor: theClassManager.authorArray)
        {   
            if (theAuthor.getName()!=null)
            {
                 output.write("Author:"+theAuthor.getid()+"\t"+ theAuthor.getName()+ "\t"+theAuthor.getUniversity()+"\t"+ theAuthor.getDepartment()+"\t"+theAuthor.getEmail()+"\n");
            }
            else
            {
            output.write("Author:"+theAuthor.getid()+"\n");
            }
            for (String theArticleID: theAuthor.getParameters().subList(5, 10))
            {   
                for (Article theArticle: theClassManager.articleArray)
                {
                    if (theArticle.getPaperID().equals(theArticleID))
                    {
                        output.write("+" + theArticle.getPaperID()+"\t"+theArticle.getName()+"\t"+theArticle.getPublisherName()+"\t"+theArticle.getPublisherYear()+"\n");
                        break;
                    }

                    
                }
            
            }
            output.write("\n");
        }
        output.write("----------------------------------------------End----------------------------------------------\n\n");
        output.close();
        }
        catch (IOException ex)
        {
            System.out.println("An error occurred.");
            ex.printStackTrace();
        }
    }
    public static void ReadAuthor(classManager theClassManager, String filename1)
    {
        try {
            FileReader authorfile= new FileReader(filename1);
            BufferedReader brForAuthor = new BufferedReader(authorfile);
            String s= brForAuthor.readLine();
            while (s!=null)
            {   
    
                String [] myList=s.split(" ");
                if (myList.length==11)
                {
                    Author theAuthor= new Author(myList[1],myList[2],myList[3],myList[4],myList[5],myList[6],myList[7],myList[8],myList[9],myList[10]);
                    theAuthor.UpdateParameters();
                    theClassManager.authorArray.add(theAuthor);
                }
                else if (myList.length==10)
                {
                    Author theAuthor= new Author(myList[1],myList[2],myList[3],myList[4],myList[5],myList[6],myList[7],myList[8],myList[9]);
                    theAuthor.UpdateParameters();
                    theClassManager.authorArray.add(theAuthor);
                }
                else if (myList.length==9)
                {
                    Author theAuthor= new Author(myList[1],myList[2],myList[3],myList[4],myList[5],myList[6],myList[7],myList[8]);
                    theAuthor.UpdateParameters();
                    theClassManager.authorArray.add(theAuthor);
                }
                else if (myList.length==8)
                {
                    Author theAuthor= new Author(myList[1],myList[2],myList[3],myList[4],myList[5],myList[6],myList[7]);
                    theAuthor.UpdateParameters();
                    theClassManager.authorArray.add(theAuthor);
                }
                else if (myList.length==7)
                {
                    Author theAuthor= new Author(myList[1],myList[2],myList[3],myList[4],myList[5],myList[6]);
                    theAuthor.UpdateParameters();
                    theClassManager.authorArray.add(theAuthor);
                }
                else if (myList.length==2)
                {
                    Author theAuthor= new Author(myList[1]);
                    theAuthor.UpdateParameters();
                    theClassManager.authorArray.add(theAuthor);
                }
                else 
                {
                    Author theAuthor= new Author(myList[1],myList[2],myList[3],myList[4],myList[5]);
                    theAuthor.UpdateParameters();
                    theClassManager.authorArray.add(theAuthor);
                }
                s=brForAuthor.readLine();
            }
            brForAuthor.close();
        }
        catch (Exception ex)
        {
            return;
        }
    }
    public static void CompleteAll(classManager theClassManager)
    {
        try{
            FileWriter output=new FileWriter("output.txt",true);
            output.write("*************************************completeAll Successful*************************************\n\n");
        
    for (int theAuthorIndex=0; theAuthorIndex<=theClassManager.authorArray.size()-1; theAuthorIndex++)
    {   
        
        
       for (int theArticleIndex=5; theArticleIndex<=theClassManager.authorArray.get(theAuthorIndex).getParameters().size()-1; theArticleIndex++ )
       {    
           

           if (theClassManager.authorArray.get(theAuthorIndex).getParameters().get(theArticleIndex)==null)

           {
               
               for (Article theArticle: theClassManager.articleArray)
               { 
                   
                   String a=theArticle.getPaperID().substring(0,3);
                   String b=theClassManager.authorArray.get(theAuthorIndex).getid();
                    if (a.equals(b)&& ! theClassManager.authorArray.get(theAuthorIndex).getParameters().contains(theArticle.getPaperID()))
                    {
                        
                        //System.out.println(theClassManager.authorArray.get(theAuthorIndex).getid());
                        //System.out.println(theArticle.getPaperID());
                        Author newAuthor= theClassManager.authorArray.get(theAuthorIndex);
                        ArrayList<String> newParameterList= newAuthor.getParameters();
                        newParameterList.set(theArticleIndex,theArticle.getPaperID());
                        newAuthor.setParameters(newParameterList);
                        theClassManager.authorArray.set(theAuthorIndex,newAuthor);
                        break;

                    }
               }
           }
       }
    }
        output.close();
        }
        catch (Exception ex){
            return;

        }
    }
    public static void SortAll(classManager theClassManager)
    {   
        try{
            FileWriter output=new FileWriter("output.txt",true);
            output.write("*************************************SortedAll Successful*************************************\n\n");
        for (int theAuthorIndex=0 ; theAuthorIndex< theClassManager.authorArray.size(); theAuthorIndex++)
        {
            Author newAuthor= theClassManager.authorArray.get(theAuthorIndex);
           ArrayList <String> newParameters= newAuthor.getParameters();
           int nullcount=0;
           for (int theArticleIndex=5; theArticleIndex<newParameters.size(); theArticleIndex++)
           {
             if (newParameters.get(theArticleIndex)==null) 
             {
                 nullcount+=1;
             }  
           }
           List <String> ArticleIDs=newParameters.subList(5, 10-nullcount);
           Collections.sort(ArticleIDs);
           int index=5;
           for (String Article: ArticleIDs)
           {
                newParameters.set(index,Article);
                index+=1;
           }
           newAuthor.setParameters(newParameters);
           theClassManager.authorArray.set(theAuthorIndex,newAuthor);
        }
        output.close();
    }
    catch (Exception ex)
    {
        return;
    }
    }
    public static void ReadArticle(classManager theClassManager,String articleFileName)
    {
        try{
        FileReader articlefile= new FileReader(articleFileName);
        BufferedReader brForArticle= new BufferedReader(articlefile);
        String z= brForArticle.readLine();
        while (z!=null)
            {   
                String [] myList2=z.split(" ");
                Article theArticle=new Article(myList2[1],myList2[2],myList2[3],myList2[4]);
                theClassManager.articleArray.add(theArticle);
                z=brForArticle.readLine();
            }
            brForArticle.close();
        }
        catch (Exception ex)
        {
            return;
        }
    }


    public static void delAuthor (classManager theClassManager, String authorid)
    {
        try{
            FileWriter output=new FileWriter("output.txt",true);
            output.write("*************************************del Successful*************************************\n\n");
        boolean found=false;
        int AuthorIndex=0;
        while (!found)
        {
            if (theClassManager.authorArray.get(AuthorIndex).getParameters().get(0).equals(authorid))
            {
                found=true;
                break;
            }
            AuthorIndex+=1;
        }
        if (found)
        {
            theClassManager.authorArray.remove(AuthorIndex);
        }
        output.close();
    }
    catch (Exception ex)
    {
        return;
    }
        
    }
    public static void readCommand(String filename1, String filename2)
    {
        try{
        FileReader commandfile= new FileReader(filename2);
        BufferedReader brForCommand= new BufferedReader(commandfile);
        String z= brForCommand.readLine();
        classManager myClassManager=new classManager();
        ReadAuthor(myClassManager,filename1);
        while (z!=null)
            {   
                String [] myList=z.split(" ");
                if (myList[0].equals("read"))
                {
                    ReadArticle(myClassManager, myList[1]);
                }
                else if (myList[0].equals("list") )
                {
                    ListAuthors(myClassManager);
                }
                else if (myList[0].equals("completeAll"))
                {
                    CompleteAll(myClassManager);
                }
                else if (myList[0].equals("del"))
                {
                    delAuthor(myClassManager,myList[1]);
                }
                else if (myList[0].equals("sortedAll"))
                {
                    SortAll(myClassManager);
                }
                z=brForCommand.readLine();
            }
            brForCommand.close();
        }
        catch (Exception ex)
        {
            return;
        }
    }

    public static void main(String []args) {
        try{
            FileWriter output=new FileWriter("output.txt");
            output.close();
            }
            catch (Exception ex)
            {
                return;
            }
        readCommand(args[0],args[1]);
    }
    
    
}
