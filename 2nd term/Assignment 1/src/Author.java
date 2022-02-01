import java.util.*;
public class Author {
    private String id;
    private String name;
    private String university;
    private String department;
    private String email;
    private String article1;
    private String article2;
    private String article3;
    private String article4;
    private String article5;
    private ArrayList<String> parameters= new ArrayList<String>();
    public Author(String ID,String Name, String University, String Department, String Email, String Article1, String Article2, String Article3, String Article4, String Article5){
        id=ID;
        name= Name;
        university= University;
        department= Department;
        email= Email;
        article1=Article1;
        article2=Article2;
        article3=Article3;
        article4=Article4;
        article5=Article5;

    }
    public Author(String ID,String Name, String University, String Department, String Email, String Article1, String Article2, String Article3, String Article4){
        id=ID;
        name= Name;
        university= University;
        department= Department;
        email= Email;
        article1=Article1;
        article2=Article2;
        article3=Article3;
        article4=Article4;

    }
    public Author(String ID,String Name, String University, String Department, String Email, String Article1, String Article2, String Article3){
        id=ID;
        name= Name;
        university= University;
        department= Department;
        email= Email;
        article1=Article1;
        article2=Article2;
        article3=Article3;

    }
    public Author(String ID,String Name, String University, String Department, String Email, String Article1, String Article2){
        id=ID;
        name= Name;
        university= University;
        department= Department;
        email= Email;
        article1=Article1;
        article2=Article2;

    }
    public Author(String ID,String Name, String University, String Department, String Email, String Article1){
        id=ID;
        name= Name;
        university= University;
        department= Department;
        email= Email;
        article1=Article1;


    }
    public Author(String ID,String Name, String University, String Department, String Email){
        id=ID;
        name= Name;
        university= University;
        department= Department;
        email= Email;

    }
    public Author(String ID){
        id=ID;
    }
    public void UpdateParameters()
    {
        parameters.add(id);
        parameters.add(name);
        parameters.add(university);
        parameters.add(department);
        parameters.add(email);
        parameters.add(article1);
        parameters.add(article2);
        parameters.add(article3);
        parameters.add(article4);
        parameters.add(article5);
    }
    public String getid()
    {
        return this.id;
    }
    public String getName()
    {
        return this.name;
    }
    public String getUniversity()
    {
        return this.university;
    }
    public String getDepartment()
    {
        return this.department;
    }
    public String getEmail()
    {
        return this.email;
    }
    public ArrayList<String> getParameters()
    {
        return parameters;
    }
    public String getArticle1()
    {
        return article1;
    }
    public String getArticle2()
    {
        return article2;
    }
    public String getArticle3()
    {
        return article3;
    }
    public String getArticle4()
    {
        return article4;
    }
    public String getArticle5()
    {
        return article5;
    }
    public void setid(String newid)
    {
        id=newid;
    }
    public void setName(String newName)
    {
        name=newName;
    }
    public void setUniversity(String newUniversity)
    {
        university=newUniversity;
    }
    public void setDepartment(String newDepartment)
    {
        department=newDepartment;
    }
    public void setEmail(String newEmail)
    {
        email=newEmail;
    }
    public void setParameters(ArrayList<String> newParameters)
    {
        parameters=newParameters;
    }
    public void setArticle1(String newArticle1)
    {
        article1=newArticle1;
    }
    public void setArticle2(String newArticle2)
    {
        article2=newArticle2;
    }
    public void setArticle3(String newArticle3)
    {
        article3=newArticle3;
    }
    public void setArticle4(String newArticle4)
    {
        article4=newArticle4;
    }
    public void setArticle5(String newArticle5)
    {
        article5=newArticle5;
    }

}
