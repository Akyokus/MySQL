
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.UploadedFile;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mehme
 */
@ManagedBean
@RequestScoped
public class mySQL {
    public String name;
    public String surname;
    public String profession;
    public int age;
    public String URL;
    int studentID;
    int i=0;
    public boolean gorunurluk=true;

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * @param surname the surname to set
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

   
    /**
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * @return the profession
     */
    public String getProfession() {
        return profession;
    }

    /**
     * @param profession the profession to set
     */
    public void setProfession(String profession) {
        this.profession = profession;
    }
    
    public String SendToDB(){
        
        PreparedStatement ps=null;
        Connection con=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mehmet","root","");
            ps=con.prepareStatement("INSERT INTO students(name,surname,profession,age,URL) VALUES(?,?,?,?,?)");
            ps.setString(1, name);
            ps.setString(2, surname);
            ps.setString(3, profession);
            ps.setInt(4, age);
            ps.setString(5, URL);
            i=ps.executeUpdate();
            
        } catch (ClassNotFoundException | SQLException exception) {
            System.out.println(exception);
            
        }
        finally{
            try {
                if(con!=null){
                    con.close();
                }if(ps!=null){
                    ps.close();
                }
            } catch (Exception e) {
                System.err.println("HATA");
            }  
        }
        
        if(i>0){
            return "basarili?faces-redirect=true";
        }
        else{
            return "basarisiz?faces-redirect=false";
        }      
    }
    

    /**
     * @return the gorunurluk
     */
    public boolean isGorunurluk() {
        return gorunurluk;
    }

    /**
     * @param gorunurluk the gorunurluk to set
     */
    public void setGorunurluk(boolean gorunurluk) {
        this.gorunurluk = gorunurluk;
    }

    
   

    
    

   
    
}
