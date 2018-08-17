
import java.awt.BorderLayout;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.jboss.weld.context.http.HttpRequestContext;

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
public class guncelleme {
    
    Connection con;
    PreparedStatement ps;
    static List<mySQL> liste2 = new ArrayList<>();
    int studentID;    
    
    

    public List<mySQL> getListe2() {
        return liste2;
    }

    public void setListe2(List<mySQL> liste2) {
        this.liste2 = liste2;
    }
    
  

    //Refactorıng yap , debug et !!!
    public void getguncelle() {
        liste2.clear();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mehmet", "root", "");
            //HttpRequestContext request =(HttpRequestContext) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            String categoryId = request.getParameter("id");
            ps = con.prepareStatement("SELECT * FROM students WHERE studentID=" + categoryId);
            ResultSet rs = ps.executeQuery();
             
            while (rs.next()) {
                mySQL my1 = new mySQL();
                my1.setStudentID(rs.getInt("studentID"));
                my1.setName(rs.getString("name"));
                my1.setSurname(rs.getString("surname"));
                my1.setProfession(rs.getString("profession"));
                my1.setAge(rs.getInt("age"));
                my1.setURL(rs.getString("URL"));
                liste2.add(my1);
                System.out.println(liste2.get(0).studentID);
            }
            
             
        } catch (Exception e) {
            System.err.println("Hata oluştu: 1" + e);
            
        }
    }

    public String getkaydet() {
        int i = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mehmet", "root", "");
            int degisken=liste2.get(0).studentID;
            PreparedStatement ps = con.prepareStatement("UPDATE students SET name=?,surname=?,profession=?,age=?,URL=? WHERE studentID=" + degisken);
            for (mySQL temp : liste2) {
                ps.setString(1, temp.name);
                ps.setString(2, temp.surname);
                ps.setString(3, temp.profession);
                ps.setInt(4, temp.age);
                ps.setString(5, temp.URL);
                i = ps.executeUpdate();
            }
        } catch (Exception e) {
            System.out.println("Hata oluştu: 2" + e);
        }
        if(i>0){
            return "basarili?faces-redirect=true";
        }
        else{
            return "basarisiz?faces-redirect=false";
        }  
    }
    
    public String getsil(){
        int i=0;
        try {
           Class.forName("com.mysql.jdbc.Driver");
           Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mehmet", "root", "");
           int degisken=liste2.get(0).studentID;
           PreparedStatement ps = con.prepareStatement("DELETE FROM students WHERE studentID=" + degisken);
           i=ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("hata oluştu sil: "+e);
        }
        
       if(i>0){
            return "basarili?faces-redirect=true";
        }
        else{
            return "basarisiz?faces-redirect=false";
        }
    }

    
    
    

}
