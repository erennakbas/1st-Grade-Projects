import java.io.*;
import java.util.*;
public class Main {
    //Method for reading people.txt file
    public static void readPeople(ArrayList<Person> persons, ArrayList<User> users, String peopleTxt)
    {
        try {
            FileReader peoplefile= new FileReader(peopleTxt);
            BufferedReader brForPeople = new BufferedReader(peoplefile);
            String s= brForPeople.readLine();
            while (s!=null)
            {    
                //instantiating objects and adding them to related arraylist.
                String classType=s.split(":")[0];
                String[] parameters=s.split(":")[1].trim().split("\t");
                if (classType.equals("Actor"))
                {
                    Actor newActor= new Actor(parameters[0],parameters[1],parameters[2],parameters[3],parameters[4]);
                    persons.add(newActor);
                }
                else if (classType.equals("ChildActor"))
                {
                    ChildActor newChildActor=new ChildActor(parameters[0],parameters[1],parameters[2],parameters[3],parameters[4]);
                    persons.add(newChildActor);
                }
                else if (classType.equals("Director"))
                {
                    Director newDirector=new Director(parameters[0],parameters[1],parameters[2],parameters[3],parameters[4]);
                    persons.add(newDirector);
                }
                else if (classType.equals("Writer"))
                {
                    Writer newWriter=new Writer(parameters[0],parameters[1],parameters[2],parameters[3],parameters[4]);
                    persons.add(newWriter);
                }
                else if (classType.equals("StuntPerformer"))
                
                {
                    
                    String[] realIDs= parameters[5].split(",");
                    StuntPerformer newStuntPerformer=new StuntPerformer(parameters[0],parameters[1],parameters[2],parameters[3],parameters[4], realIDs);
                    persons.add(newStuntPerformer);
                }
                else if (classType.equals("User"))
                {
                    User newUser= new User(parameters[0],parameters[1],parameters[2],parameters[3]);
                    users.add(newUser);
                }
                s=brForPeople.readLine();
            }
            brForPeople.close();
        }
         catch (Exception ex)
         {
            System.out.println("An error occurred.");
            ex.printStackTrace();
         }
        }
        //Method for reading film.txt file
        public static void readFilms(ArrayList<Film> films, String filmsTxt)
        {
        try {
            FileReader filmsfile= new FileReader(filmsTxt);
            BufferedReader brForFilms = new BufferedReader(filmsfile);
            String s= brForFilms.readLine();
            while (s!=null)
            {
                 //instantiating objects and adding them to related arraylist.
                String classType=s.split(":")[0];
                String rightSide=s.split(":")[1].trim();
                String[] parameters=rightSide.split("\t");
                if (classType.equals("FeatureFilm"))
                {
                    FeatureFilm newFeatureFilm= new FeatureFilm(parameters[0], parameters[1], parameters[2], parameters[3].split(","), parameters[4], parameters[5], parameters[6].split(","), parameters[7].split(","), parameters[8], parameters[9].split(","),parameters[10]);
                    films.add(newFeatureFilm);
                }
                else if (classType.equals("ShortFilm"))
                {
                    ShortFilm newShortFilm= new ShortFilm(parameters[0], parameters[1], parameters[2], parameters[3].split(","), parameters[4], parameters[5], parameters[6].split(","), parameters[7].split(","), parameters[8], parameters[9].split(","));
                    films.add(newShortFilm);
                }
                else if (classType.equals("Documentary"))
                {
                    Documentary newDocumentary= new Documentary(parameters[0], parameters[1], parameters[2], parameters[3].split(","), parameters[4], parameters[5], parameters[6].split(","), parameters[7]);
                    films.add(newDocumentary);
                }
                else if (classType.equals("TVSeries"))
                {
                    TVseries newTVSeries= new TVseries(parameters[0],parameters[1], parameters[2], parameters[3].split(","), parameters[4], parameters[5], parameters[6].split(","),parameters[7].split(","), parameters[8].split(","), parameters[9], parameters[10], parameters[11], parameters[12]);
                    films.add(newTVSeries);
                }
                s=brForFilms.readLine();
            }
            brForFilms.close();
        }
        catch (Exception ex)
        {
            System.out.println("An error occurred.");
            ex.printStackTrace();
        }
    }
    public static void readCommand(ArrayList<Person> persons, ArrayList<User> users, ArrayList<Film> films, String peopleTxt, String filmTxt, String commandsTxt, String outputTxt)
    {
        try {
            FileWriter output=new FileWriter(outputTxt,false);
            readPeople(persons, users,peopleTxt);
            readFilms(films,filmTxt);
            FileReader commandsfile= new FileReader(commandsTxt);
            BufferedReader brForCommands = new BufferedReader(commandsfile);
            String s= brForCommands.readLine();
            while (s!=null)
            {   //Reading commands
                String[] parameters= s.split("\t");
                String commandType=s.split("\t")[0];
                //Rate command
                if (commandType.equals("RATE"))
                {   
                    output.write(s+"\n\n");
                    int userFoundIndex=0;
                    int filmFoundIndex=0;
                    boolean filmFound=false;
                    boolean userFound=false;
                    boolean personFound=false;
                    for (Person p:persons)
                    {   
                        if (p.uniqueID.equals(parameters[1]))
                        {
                            personFound=true;
                            break;
                        }
                    }
                    if (personFound==false)
                    {
                        for (User u:users)
                    {
                        if (u.uniqueID.equals(parameters[1]))
                        {
                            userFound=true;
                            break;
                        }
                        userFoundIndex++;
                    }
                    }
                    for (Film f: films)
                    {
                        if(f.uniqueFilmID.equals(parameters[2]))
                        {

                            filmFound=true;
                            break;
                        }
                        filmFoundIndex++;
                    }
                    // if film and userfound then do the necessary operations.
                    if (filmFound&&userFound==true)
                    {   
                        Film tempFilm= films.get(filmFoundIndex);
                        double rate= Double.parseDouble(parameters[3]);
                        //if film doesn't contain related user id.
                        if (!tempFilm.id_Rate.containsKey(parameters[1]))
                        {
                        User tempUser=users.get(userFoundIndex);
                        tempUser.ratedFilms.put(tempFilm.filmTitle, rate);
                        users.set(userFoundIndex, tempUser);
                        tempFilm.Rate(rate, parameters[1]);
                        tempFilm.id_Rate.put(tempUser.uniqueID,rate);
                        films.set(filmFoundIndex,tempFilm);
                        output.write("Film rated successfully\n");
                        output.write("Film type: " + tempFilm.ShowFilmType()+ "\n");
                        output.write("Film title: "+ tempFilm.filmTitle + "\n\n");
                        }
                        else
                        {
                            //if film contains related user id.
                            output.write("This film was earlier rated\n\n");
                        }
                    }
                    //if there is problem with conditions then print command failed.
                    else if (filmFound&&personFound==true)
                    {
                        output.write("Command Failed \n");
                        output.write("User ID: " + parameters[1]+"\n");
                        output.write("Film ID: " + parameters[2]+"\n\n");
                    }
                    else if ((filmFound&&personFound) || (filmFound&&userFound))
                    {
                        output.write("Command Failed \n");
                        output.write("User ID: " + parameters[1]+"\n");
                        output.write("Film ID: " + parameters[2]+"\n\n");
                        
                    }
                    else
                    {
                        output.write("This film was earlier rated\n\n");
                    }
                    output.write("----------------------------------------------------------------------------------------------------- \n");   
                }
                //Viewfilm command
                else if (commandType.equals("VIEWFILM"))
                {
                    output.write(s+"\n\n");
                    boolean filmFound=false;
                    for (Film f:films)
                    {   
                        //if there is a film with related film id.
                        if (f.uniqueFilmID.equals(parameters[1]))
                        {   
                            //print the output according to their film type.
                            filmFound=true;
                            if (f instanceof TVseries)
                            {
                                TVseries theFilm=(TVseries)f;
                                String x=String.format("%s (%s-%s)\n%s seasons, %s episodes\n",theFilm.filmTitle, theFilm.startDate.split("\\.")[2], theFilm.endDate.split("\\.")[2], theFilm.seasons, theFilm.episodes);
                                output.write(x);
                               String y= String.join(", ", theFilm.genres);
                               output.write(y+"\n");
                               String z= "";
                               for (String id: theFilm.writers)
                               {
                                   for (Person p: persons)
                                   {
                                       if (id.equals(p.uniqueID))
                                       {
                                           if (!id.equals(theFilm.writers[theFilm.writers.length-1]))
                                           {
                                           z+=p.name+" "+ p.surname+", ";
                                           break;
                                           }
                                           else
                                           {
                                               z+=p.name+" "+ p.surname;
                                               break;
                                           }
                                       }
                                   }
                               }
                               output.write("Writers: "+z+"\n");
                               String t="";
                               for (String id: theFilm.directors)
                               {
                                for (Person p: persons)
                                {
                                    if (id.equals(p.uniqueID))
                                    {
                                 if (!id.equals(theFilm.directors[theFilm.directors.length-1]))
                                 {
                                 t+=p.name+" "+ p.surname+", ";
                                 break;
                                 }
                                 else
                                 {
                                     t+=p.name+" "+ p.surname;
                                     break;
                                 }
                                }
                                }
                               }
                               output.write("Directors: "+ t+ "\n");
                               String h="";
                               for (String id: theFilm.performers)
                               {
                                   for (Person p: persons)
                                   {    
                                    if (id.equals(p.uniqueID))
                                    {
                                        if (!id.equals(theFilm.performers[theFilm.performers.length-1]))
                                        {
                                        h+=p.name+" "+ p.surname+", ";
                                        break;
                                        }
                                        else
                                        {
                                            h+=p.name+" "+ p.surname;
                                            break;
                                        }
                                    }
                                   }
                               }
                               output.write("Stars: " +h+ "\n");
                               if (theFilm.id_Rate.size()>0)
                               {   //if it is rated before
                                   if (Double.toString(f.filmsRate).charAt(2)=="0".charAt(0))
                                   {
                                    output.write("Ratings: "+Double.toString(theFilm.filmsRate).charAt(0)+"/10 from "+theFilm.id_Rate.size()+" users"+"\n\n");
                                   }
                                   else
                                   {
                                   output.write("Ratings: "+Double.toString(Math.floor(Math.round(f.filmsRate* 10.0))/10.0).replace('.',',')+"/10 from "+theFilm.id_Rate.size()+" users"+"\n\n");
                                   }
                               }
                               else
                               {    //if it's never voted before.
                                   output.write("Awaiting for votes\n\n");
                               }

                            }
                            else if (f instanceof Documentary)
                            {
                                Documentary theFilm = (Documentary) f;
                                String x=String.format("%s (%s)\n",theFilm.filmTitle, theFilm.releaseDate.split("\\.")[2]);
                                output.write(x);
                                String t="";
                                for (String id: theFilm.directors)
                                {
                                    for (Person p: persons)
                                    {
                                        if (id.equals(p.uniqueID))
                                        {
                                     if (!id.equals(theFilm.directors[theFilm.directors.length-1]))
                                     {
                                     t+=p.name+" "+ p.surname+", ";
                                     break;
                                     }
                                     else
                                     {
                                         t+=p.name+" "+ p.surname;
                                         break;
                                     }
                                    }
                                    }
                                }
                               output.write("Directors: "+ t+ "\n");
                               String h="";
                               for (String id: theFilm.performers)
                               {
                                   for (Person p: persons)
                                   {    
                                    if (id.equals(p.uniqueID))
                                    {
                                        if (!id.equals(theFilm.performers[theFilm.performers.length-1]))
                                        {
                                        h+=p.name+" "+ p.surname+", ";
                                        break;
                                        }
                                        else
                                        {
                                            h+=p.name+" "+ p.surname;
                                            break;
                                        }
                                    }
                                   }
                               }
                               output.write("Stars: " +h+ "\n");
                               if (theFilm.id_Rate.size()>0)
                               { //if it is rated before
                                if (Double.toString(f.filmsRate).charAt(2)=="0".charAt(0))
                                {
                                 output.write("Ratings: "+Double.toString(theFilm.filmsRate).charAt(0)+"/10 from "+theFilm.id_Rate.size()+" users"+"\n\n");
                                }
                                else
                                {
                                output.write("Ratings: "+Double.toString(Math.floor(Math.round(f.filmsRate* 10.0))/10.0).replace('.',',')+"/10 from "+theFilm.id_Rate.size()+" users"+"\n\n");
                                }
                               }
                               else
                               { //if it's never  been voted.
                                   output.write("Awaiting for votes\n\n");
                               }
                            }
                            else if (f instanceof ShortFilm)
                            {
                                ShortFilm theFilm = (ShortFilm) f;
                                String x=String.format("%s (%s)\n",theFilm.filmTitle, theFilm.releaseDate.split("\\.")[2]); 
                               output.write(x);
                               String y= String.join(", ", theFilm.genre);
                               output.write(y+"\n");
                               String z= "";
                               for (String id: theFilm.writers)
                               {
                                   for (Person p: persons)
                                   {
                                       if (id.equals(p.uniqueID))
                                       {
                                           if (!id.equals(theFilm.writers[theFilm.writers.length-1]))
                                           {
                                           z+=p.name+" "+ p.surname+", ";
                                           break;
                                           }
                                           else
                                           {
                                               z+=p.name+" "+ p.surname;
                                               break;
                                           }
                                       }
                                   }
                               }
                               output.write("Writers: "+z+"\n");
                               String t="";
                                for (String id: theFilm.directors)
                                {
                                    for (Person p: persons)
                                    {
                                        if (id.equals(p.uniqueID))
                                        {
                                     if (!id.equals(theFilm.directors[theFilm.directors.length-1]))
                                     {
                                     t+=p.name+" "+ p.surname+", ";
                                     break;
                                     }
                                     else
                                     {
                                         t+=p.name+" "+ p.surname;
                                         break;
                                     }
                                    }
                                    }
                                }
                               output.write("Directors: "+ t+ "\n");
                               String h="";
                               for (String id: theFilm.performers)
                               {
                                   for (Person p: persons)
                                   {    
                                    if (id.equals(p.uniqueID))
                                    {
                                        if (!id.equals(theFilm.performers[theFilm.performers.length-1]))
                                        {
                                        h+=p.name+" "+ p.surname+", ";
                                        break;
                                        }
                                        else
                                        {
                                            h+=p.name+" "+ p.surname;
                                            break;
                                        }
                                    }
                                   }
                               }
                               output.write("Stars: " +h+ "\n");
                               if (theFilm.id_Rate.size()>0)
                               { //if it is rated before
                                if (Double.toString(f.filmsRate).charAt(2)=="0".charAt(0))
                                {
                                 output.write("Ratings: "+Double.toString(theFilm.filmsRate).charAt(0)+"/10 from "+theFilm.id_Rate.size()+" users"+"\n\n");
                                }
                                else
                                {
                                output.write("Ratings: "+Double.toString(Math.floor(Math.round(f.filmsRate* 10.0))/10.0).replace('.',',')+"/10 from "+theFilm.id_Rate.size()+" users"+"\n\n");
                                }                               
                            }
                               else
                               {    
                                   //if it's never  been voted.
                                   output.write("Awaiting for votes\n\n");
                               }
                            }
                            else if (f instanceof FeatureFilm)
                            {   
                                FeatureFilm theFilm= (FeatureFilm) f;
                                String x=String.format("%s (%s)\n",theFilm.filmTitle, theFilm.releaseDate.split("\\.")[2]);
                               output.write(x);
                               String y= String.join(", ", theFilm.genre);
                               output.write(y+"\n");
                               String z= "";
                               for (String id: theFilm.writers)
                               {
                                   for (Person p: persons)
                                   {
                                       if (id.equals(p.uniqueID))
                                       {
                                           if (!id.equals(theFilm.writers[theFilm.writers.length-1]))
                                           {
                                           z+=p.name+" "+ p.surname+", ";
                                           break;
                                           }
                                           else
                                           {
                                               z+=p.name+" "+ p.surname;
                                               break;
                                           }
                                       }
                                   }
                               }
                               output.write("Writers: "+z+"\n");
                               String t="";
                                for (String id: theFilm.directors)
                                {
                                    for (Person p: persons)
                                    {
                                        if (id.equals(p.uniqueID))
                                        {
                                     if (!id.equals(theFilm.directors[theFilm.directors.length-1]))
                                     {
                                     t+=p.name+" "+ p.surname+", ";
                                     break;
                                     }
                                     else
                                     {
                                         t+=p.name+" "+ p.surname;
                                         break;
                                     }
                                    }
                                    }
                                }
                               output.write("Directors: "+ t+ "\n");
                               String h="";
                               for (String id: theFilm.performers)
                               {
                                   for (Person p: persons)
                                   {    
                                    if (id.equals(p.uniqueID))
                                    {
                                        if (!id.equals(theFilm.performers[theFilm.performers.length-1]))
                                        {
                                        h+=p.name+" "+ p.surname+", ";
                                        break;
                                        }
                                        else
                                        {
                                            h+=p.name+" "+ p.surname;
                                            break;
                                        }
                                    }
                                   }
                               }
                               output.write("Stars: " +h+ "\n");
                               if (theFilm.id_Rate.size()>0)
                               { //if it is rated before
                                if (Double.toString(f.filmsRate).charAt(2)=="0".charAt(0))
                                {
                                 output.write("Ratings: "+Double.toString(theFilm.filmsRate).charAt(0)+"/10 from "+theFilm.id_Rate.size()+" users"+"\n\n");
                                }
                                else
                                {
                                output.write("Ratings: "+Double.toString(Math.floor(Math.round(f.filmsRate* 10.0))/10.0).replace('.',',')+"/10 from "+theFilm.id_Rate.size()+" users"+"\n\n");
                                }                               
                            }
                               else
                               { //if it's never voted before
                                   output.write("Awaiting for votes\n\n");
                               }
                            }
                        }
                    }
                    if (filmFound==false)
                    { // if there is no film with related film id then print command failed.
                        output.write("Command Failed\n Film ID: " +parameters[1]+"\n\n");
                    }
                    output.write("-----------------------------------------------------------------------------------------------------\n");
                }
                //add command
                else if (commandType.equals("ADD"))
                {
                    boolean filmFound=false;
                    FeatureFilm f= new FeatureFilm(parameters[2],parameters[3], parameters[4], parameters[5].split(","),parameters[6],parameters[7], parameters[8].split(","), parameters[9].split(","),parameters[10],parameters[11].split(","),parameters[12]);
                    //searching the film
                    for (Film theFilm: films)
                    {
                        if (theFilm.uniqueFilmID.equals(f.uniqueFilmID))
                        {
                            filmFound=true;
                            break;
                        }
                    }
                    String[] allPersonIDs=new String[parameters[5].split(",").length+ parameters[8].split(",").length+parameters[11].split(",").length];
                    System.arraycopy(parameters[5].split(","), 0, allPersonIDs, 0, parameters[5].split(",").length);
                    System.arraycopy(parameters[8].split(","), 0, allPersonIDs, parameters[5].split(",").length, parameters[8].split(",").length);
                    System.arraycopy(parameters[11].split(","), 0, allPersonIDs, parameters[5].split(",").length+parameters[8].split(",").length, parameters[11].split(",").length);
                    boolean outwardIDFound=true;
                    // if there is a person that is not in the system
                    for (String id : allPersonIDs)
                    {
                        boolean idFound=false;
                        for (Person p: persons)
                        {
                            if (id.equals(p.uniqueID))
                            {
                                idFound=true;
                                break;
                            }
                        }
                        if (idFound==false)
                        {
                            outwardIDFound=false;
                            break;
                        }
                    }
                    output.write(s+"\n\n");
                    // if there is a film that's in the system or there is an id that's not in the system then print command failed.
                    if (filmFound==true || outwardIDFound==false)
                    {
                        output.write("Command Failed \n");
                        output.write("Film ID: " + parameters[2]+"\n");
                        output.write("Film Title: "+ parameters[3]+"\n\n");
                    }
                    else
                    {
                        output.write(f.ShowFilmType()+" added successfully\n");
                        output.write("Film ID: " + parameters[2]+"\n");
                        output.write("Film Title: "+ parameters[3]+"\n\n");
                        films.add(f);
                    }
                    output.write("-----------------------------------------------------------------------------------------------------\n");
                }
                //edit command
                else if (commandType.equals("EDIT"))
                {
                    output.write(s+"\n\n");
                    int userFoundIndex=0;
                    int filmFoundIndex=0;
                    boolean filmfound=false;
                    boolean userfound=false;
                    boolean personfound=false;
                    for (Person p:persons)
                    {   //search id
                        if (p.uniqueID.equals(parameters[2]))
                        {
                            personfound=true;
                            break;
                        }
                    }
                    if (personfound==false)
                    {
                        for (User u:users)
                    {
                        if (u.uniqueID.equals(parameters[2]))
                        {
                            userfound=true;
                            break;
                        }
                        userFoundIndex++;
                    }
                    }
                    for (Film f: films)
                    {
                        if(f.uniqueFilmID.equals(parameters[3]))
                        {

                            filmfound=true;
                            break;
                        }
                        filmFoundIndex++;
                    }
                    //change rates for that film and change the film rate for user's hashmap attribute.
                    if (filmfound&&userfound==true)
                    {   
                        Film tempFilm= films.get(filmFoundIndex);
                        double rate= Double.parseDouble(parameters[4]);
                        if (tempFilm.id_Rate.containsKey(parameters[2]))
                        {
                        User tempUser=users.get(userFoundIndex);
                        tempUser.ratedFilms.remove(tempFilm.filmTitle);
                        tempUser.ratedFilms.put(tempFilm.filmTitle, rate);
                        tempFilm.EditRate(rate, parameters[2]);
                        films.set(filmFoundIndex,tempFilm);
                        users.set(userFoundIndex, tempUser);
                        output.write("New ratings done successfully\n");
                        output.write("Film title: "+ tempFilm.filmTitle + "\n");
                        output.write("Your rating: "+ (int) rate+"\n\n");
                        }
                        else
                        { //command failed if it doesn't match with the condition
                            output.write("Command Failed \n");
                            output.write("User ID: " + parameters[2]+"\n");
                            output.write("Film ID: " + parameters[3]+"\n\n");
                        }
                    }
                    //command failed if it doesn't match with the condition
                    else if (filmfound&&personfound==true)
                    {   
                        output.write("Command Failed \n");
                        output.write("User ID: " + parameters[2]+"\n");
                        output.write("Film ID: " + parameters[3]+"\n\n");
                    }
                    //command failed if it doesn't match with the condition
                    else if ((filmfound&&personfound) || (filmfound&&userfound))
                    {
                        output.write("Command Failed \n");
                        output.write("User ID: " + parameters[2]+"\n");
                        output.write("Film ID: " + parameters[3]+"\n\n");
                        
                    }
                    else
                    {
                        output.write("Command Failed \n");
                        output.write("User ID: " + parameters[2]+"\n");
                        output.write("Film ID: " + parameters[3]+"\n\n");
                    }
                    output.write("----------------------------------------------------------------------------------------------------- \n");
                }
                //remove command
                else if (commandType.equals("REMOVE"))
                {
                    output.write(s+"\n\n");
                    int userFoundIndex=0;
                    int filmFoundIndex=0;
                    boolean filmfound=false;
                    boolean userfound=false;
                    boolean personfound=false;
                    //search for film, person, user
                    for (Person p:persons)
                    {   
                        if (p.uniqueID.equals(parameters[2]))
                        {
                            personfound=true;
                            break;
                        }
                    }
                    if (personfound==false)
                    {
                        for (User u:users)
                    {
                        if (u.uniqueID.equals(parameters[2]))
                        {
                            userfound=true;
                            break;
                        }
                        userFoundIndex++;
                    }
                    }
                    for (Film f: films)
                    {
                        if(f.uniqueFilmID.equals(parameters[3]))
                        {

                            filmfound=true;
                            break;
                        }
                        filmFoundIndex++;
                    }
                    if (filmfound&&userfound==true)
                    {   
                        //remove rates for user and film
                        Film tempFilm= films.get(filmFoundIndex);
                        if (tempFilm.id_Rate.containsKey(parameters[2]))
                        {
                        User tempUser=users.get(userFoundIndex);
                        tempUser.ratedFilms.remove(tempFilm.filmTitle);
                        tempFilm.rating-= tempFilm.id_Rate.get(parameters[2]);
                        tempFilm.filmsRate=tempFilm.rating/tempFilm.id_Rate.size();
                        tempFilm.id_Rate.remove(parameters[2]);
                        films.set(filmFoundIndex,tempFilm);
                        users.set(userFoundIndex, tempUser);
                        output.write("Your film rating was removed successfully\n");
                        output.write("Film title: "+ tempFilm.filmTitle + "\n\n");
                        }
                        else
                        {
                            output.write("Command Failed \n");
                            output.write("User ID: " + parameters[2]+"\n");
                            output.write("Film ID: " + parameters[3]+"\n\n");
                        }
                    }
                    else if (filmfound&&personfound==true)
                    {   
                        output.write("Command Failed \n");
                        output.write("User ID: " + parameters[2]+"\n");
                        output.write("Film ID: " + parameters[3]+"\n\n");
                    }
                    else if ((filmfound&&personfound) || (filmfound&&userfound))
                    {
                        output.write("Command Failed \n");
                        output.write("User ID: " + parameters[2]+"\n");
                        output.write("Film ID: " + parameters[3]+"\n\n");
                        
                    }
                    else
                    {
                        output.write("Command Failed \n");
                        output.write("User ID: " + parameters[2]+"\n");
                        output.write("Film ID: " + parameters[3]+"\n\n");
                    }
                    output.write("----------------------------------------------------------------------------------------------------- \n");
                }
                //list tvseries command
                else if (commandType.equals("LIST")&&parameters[2].equals("SERIES"))
                {   boolean filmFound=false;
                    output.write(s+"\n\n");
                    for (Film f: films)
                    {
                        if (f instanceof TVseries)
                        {   // if film is an object of tvseries print output.
                            filmFound=true;
                            TVseries tv= (TVseries) f;
                            output.write(tv.filmTitle + " ("+tv.startDate.split("\\.")[2]+"-"+tv.endDate.split("\\.")[2]+")"+"\n");
                            output.write(tv.seasons+" seasons "+ "and "+tv.episodes+" episodes\n\n");

                        }

                    }
                    if (filmFound==false)
                    {
                        //if there is no film at all from tvseries.
                        output.write("No result \n\n");
                    }
                    output.write("-----------------------------------------------------------------------------------------------------\n");
                }
                //list films according to their countries.
                else if (commandType.equals("LIST")&&parameters[3].equals("COUNTRY"))
                {   output.write(s+"\n\n");
                    boolean countryFound=false;
                    for (Film f: films)
                    {   //if there is a film that matches with the given country parameter.
                        if (f.country.equals(parameters[4]))
                        {
                            output.write("Film title: "+f.filmTitle+"\n");
                            output.write(f.runtime+" min\n");
                            output.write("Language: "+ f.language+"\n\n");
                            countryFound=true;

                        }
                    }
                    //if there is no film that matches with the given country parameterr.
                    if (countryFound==false)
                    {
                        output.write("No result \n\n");

                    }
                    output.write("-----------------------------------------------------------------------------------------------------\n");
                }
                //listing feature films command
                else if (commandType.equals("LIST")&&parameters[1].equals("FEATUREFILMS"))
                {   output.write(s+"\n\n");
                    
                    boolean after=false;
                    boolean filmFound=false;
                    //if it's after
                    if (parameters[2].equals("AFTER"))
                    {
                        after=true;
                    }
                    for (Film f : films)
                    {
                        if (f instanceof FeatureFilm)
                        {   //print the film if it's released after the given year.
                            FeatureFilm featureF= (FeatureFilm) f;
                            if (after==true && Integer.parseInt(featureF.releaseDate.split("\\.")[2])>=(Integer.parseInt(parameters[3])))
                            {
                            filmFound=true;
                            output.write("Film title: "+f.filmTitle+" ("+ featureF.releaseDate.split("\\.")[2]+")"+"\n");
                            output.write(f.runtime+" min\n");
                            output.write("Language: "+ f.language+"\n\n");
                            }
                            //print the film if it's released before the given year.
                            else if (after==false && Integer.parseInt(featureF.releaseDate.split("\\.")[2])<(Integer.parseInt(parameters[3])))
                            {
                                filmFound=true;
                                output.write("Film title: "+f.filmTitle+" ("+ featureF.releaseDate.split("\\.")[2]+")"+"\n");
                                output.write(f.runtime+" min\n");
                                output.write("Language: "+ f.language+"\n\n");
                            }
                        }
                    }
                    // if there is no film that matches with country parameter.
                    if (filmFound==false)
                    {
                        output.write("No result \n\n");
                    }
                    output.write("-----------------------------------------------------------------------------------------------------\n");
                }
                //sorting the films according to their rate.
                else if (commandType.equals("LIST")&&parameters[3].equals("RATE"))
                {   
                    output.write(s+"\n\n");
                    String featureFilms="FeatureFilm:\n";
                    String shortFilms= "ShortFilm:\n";
                    String documentaries="Documentary:\n";
                    String tvseries="TVSeries:\n";
                    boolean tvSeriesFound=false;
                    boolean featureFilmFound=false;
                    boolean shortFilmFound=false;
                    boolean documentaryFound=false;
                    //comparator
                    Comparator<Film> byRate = Comparator.comparing(Film::showRate);
                    Film[] filmArray= new Film[films.size()];
                    int a=0;
                    for (Film f: films)
                    {
                        filmArray[a]=f;
                        a++;
                    }
                    Arrays.sort(filmArray,byRate.reversed());
                    //setting the strings according to their classes.
                    for (int i=0; i<filmArray.length; i++)
                    {   
                        if (filmArray[i] instanceof FeatureFilm)
                        {   
                            featureFilmFound=true;
                            FeatureFilm f= (FeatureFilm) filmArray[i];
                            if (Double.toString(f.filmsRate).charAt(2)=="0".charAt(0))
                            {
                                String newString= String.format("%s (%s) Ratings: %s/10 from %d users\n",f.filmTitle, f.releaseDate.split("\\.")[2], Double.toString(f.filmsRate).charAt(0), f.id_Rate.size());
                                featureFilms+= newString;
                            }
                            else
                            {
                            String newString= String.format("%s (%s) Ratings: %s/10 from %d users\n",f.filmTitle, f.releaseDate.split("\\.")[2],  Double.toString(Math.floor(Math.round(f.filmsRate* 10.0))/10.0).replace('.',','), f.id_Rate.size());
                            featureFilms+= newString;
                            }
                        }
                        else if (filmArray[i] instanceof ShortFilm)
                        {   shortFilmFound=true;
                            ShortFilm f= (ShortFilm) filmArray[i];
                            if (Double.toString(f.filmsRate).charAt(2)=="0".charAt(0))
                            {
                                String newString= String.format("%s (%s) Ratings: %s/10 from %d users\n",f.filmTitle, f.releaseDate.split("\\.")[2], Double.toString(f.filmsRate).charAt(0), f.id_Rate.size());
                                shortFilms+= newString;
                            }
                            else
                            {
                            String newString= String.format("%s (%s) Ratings: %s/10 from %d users\n",f.filmTitle, f.releaseDate.split("\\.")[2],  Double.toString(Math.floor(Math.round(f.filmsRate* 10.0))/10.0).replace('.',','), f.id_Rate.size());
                            shortFilms+= newString;
                            }
                        }
                        else if (filmArray[i] instanceof Documentary)
                        {   documentaryFound=true;
                            Documentary f= (Documentary) filmArray[i];
                            if (Double.toString(f.filmsRate).charAt(2)=="0".charAt(0))
                            {
                                String newString= String.format("%s (%s) Ratings: %s/10 from %d users\n",f.filmTitle, f.releaseDate.split("\\.")[2], Double.toString(f.filmsRate).charAt(0), f.id_Rate.size());
                                documentaries+= newString;
                            }
                            else
                            {
                            String newString= String.format("%s (%s) Ratings: %s/10 from %d users\n",f.filmTitle, f.releaseDate.split("\\.")[2],  Double.toString(Math.floor(Math.round(f.filmsRate* 10.0))/10.0).replace('.',','), f.id_Rate.size());
                            documentaries+= newString;
                            }
                        }
                        else if (filmArray[i] instanceof TVseries)
                        {   
                            tvSeriesFound=true;
                            TVseries f= (TVseries) filmArray[i];
                            if (Double.toString(f.filmsRate).charAt(2)=="0".charAt(0))
                            {
                                String newString=String.format("%s (%s-%s) Ratings: %s/10 from %d users\n",f.filmTitle, f.startDate.split("\\.")[2],f.endDate.split("\\.")[2], Double.toString(f.filmsRate).charAt(0), f.id_Rate.size());
                                tvseries+= newString;
                            }
                            else
                            {
                                String newString= String.format("%s (%s-%s) Ratings: %s/10 from %d users\n",f.filmTitle, f.startDate.split("\\.")[2],f.endDate.split("\\.")[2], Double.toString(Math.floor(Math.round(f.filmsRate* 10.0))/10.0).replace('.',','), f.id_Rate.size());
                                tvseries+= newString;
                            }      
                        } 
                    }
                    // if there is not a film for related part.
                    if (featureFilmFound==false)
                    {
                        featureFilms+="No result\n\n";
                    }
                    if (shortFilmFound==false)
                    {
                        shortFilms+="No result\n\n";
                    }
                    if (documentaryFound==false)
                    {
                        documentaries+="No result\n\n";
                    }
                    if (tvSeriesFound==false)
                    {
                        tvseries+="No result\n\n";
                    }
                    featureFilms+="\n";
                    shortFilms+="\n";
                    documentaries+="\n";
                    tvseries+="\n";

                    output.write(featureFilms+shortFilms+documentaries+tvseries+"-----------------------------------------------------------------------------------------------------\n");
                }
                //Listing artists according to their countries.
                else if (commandType.equals("LIST")&&parameters[1].equals("ARTISTS"))
                {   
                    output.write(s+"\n\n");
                    String directors="Directors:\n";
                    String writers="Writers:\n";
                    String actors="Actors:\n";
                    String childActors="ChildActors:\n";
                    String stuntPerformers="StuntPerformers:\n";
                    boolean directorFound=false;
                    boolean writerFound=false;
                    boolean actorFound=false;
                    boolean childActorFound=false;
                    boolean stuntPerformerFound=false;
                    for (Person p: persons)
                    {   // if person's country parameter matches with the given parameter
                        if (p.country.equals(parameters[3]))
                        { 
                            //setting the strings according to their classes.
                        if (p instanceof Director)
                        {
                            directorFound=true;
                            Director d= (Director) p;
                            directors+=d.name+" "+d.surname+ " "+ d.agent+"\n";
                        }
                        else if (p instanceof Writer)
                        {
                            Writer w= (Writer) p;
                            writers+=w.name+ " "+ w.surname + " " + w.writingStyle+"\n";
                            writerFound=true;
                        }
                        else if (p instanceof Actor)
                        {   
                            Actor a = (Actor) p;
                            actors+= a.name+" "+ a.surname+ " "+ a.height+ " cm\n";
                            actorFound=true;
                        }
                        else if (p instanceof ChildActor)
                        {   
                            ChildActor c = (ChildActor) p;
                            childActors+= c.name+" "+c.surname+" "+ c.age+"\n";
                            childActorFound=true;
                        }
                        else if (p instanceof StuntPerformer)
                        {   
                            StuntPerformer sPerformer= (StuntPerformer) p;
                            stuntPerformers+=sPerformer.name+" "+ sPerformer.surname+" "+ sPerformer.height+" cm\n";
                            stuntPerformerFound=true;
                        }
                    }
                    }
                    //// if there is not a person for related part.
                    if (directorFound==false)
                    {
                        directors+="No result\n";
                    }
                    if (writerFound==false)
                    {
                        writers+="No result\n";
                    }
                    if (actorFound==false)
                    {
                        actors+="No result\n";
                    }
                    if (childActorFound==false)
                    {
                        childActors+="No result\n";
                    }
                    if (stuntPerformerFound==false)
                    {
                        stuntPerformers+="No result\n";
                    }
                    directors+="\n";
                    writers+="\n";
                    actors+="\n";
                    childActors+="\n";
                    stuntPerformers+="\n";
                    output.write(directors+writers+actors+childActors+stuntPerformers+"-----------------------------------------------------------------------------------------------------\n");
                }
                //Listing films for wanted user.
                else if (commandType.equals("LIST")&& parameters[1].equals("USER"))
                {
                    boolean userFound=false;
                    output.write(s+"\n\n");
                    for (User u: users)
                    {   //if the given id matches with the system's users.
                        if (u.uniqueID.equals(parameters[2])){   
                            //if user rated a film.
                            if (u.ratedFilms.values().size()>0)
                            {
                            for (String x: u.ratedFilms.keySet()){
                                output.write(x+": "+u.ratedFilms.get(x).intValue()+"\n");
                            }
                            userFound=true;
                            break;
                        }
                        else
                        {   // if user hasn't rated any film yet.
                            userFound=true;
                            output.write("There is not any ratings so far\n");
                            break;
                        }
                        }
                    }
                    // if there is not related user in the system
                    if (userFound==false){
                        output.write("Command Failed\n"+"User ID: "+parameters[2]+"\n");
                    }
                    output.write("\n-----------------------------------------------------------------------------------------------------\n");
                }
                s=brForCommands.readLine();
            }
            brForCommands.close();
            output.close();
        }
        catch (Exception ex)
        {
            System.out.println("An error occurred.");
            ex.printStackTrace();
        }
    }
    public static void main(String[] args)
    {
        ArrayList<Person> persons= new ArrayList<Person>();
        ArrayList<User> users= new ArrayList<User>();
        ArrayList<Film> films= new ArrayList<Film>();
        readCommand(persons, users, films, args[0], args[1], args[2], args[3]);
        }
}
