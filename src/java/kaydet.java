/*
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.util.Map;
import javax.faces.context.*;
import javax.servlet.http.HttpServletRequest;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mehme
 */ /*
@ManagedBean
@RequestScoped
public class kaydet {
   
    public String kaydetme(int ID,String name,String surname,String profession,int age,String URL){
        int i=0; 
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/mehmet", "root", "");
            System.out.println("id: "+ID);
            System.out.println("isim: "+name);
            PreparedStatement pss=conn.prepareStatement("UPDATE students SET name=?,surname=?,profession=?,age=?,URL=? WHERE studentID="+ID);
            
                pss.setString(1, name);
                pss.setString(2, surname);
                pss.setString(3,profession);
                pss.setInt(4, age);
                pss.setString(5, URL);
                i=pss.executeUpdate();
          
        } catch (Exception e) {
            System.out.println("Hata oluştu: "+e);
        }
        if(i>0){
            return "basarili?faces-redirect=true";
        }
        else{
            return "basarisiz?faces-redirect=false";
        }
    }
        
        public String IDParametresiAl(FacesContext fc){
            Map<String,String> parametreler=fc.getExternalContext().getRequestParameterMap();
            return parametreler.get("ID");
        }

    /**
     * @return the liste
     */
    

