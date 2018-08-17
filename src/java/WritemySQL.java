


import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
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
public class WritemySQL {
   PreparedStatement ps=null;
   Connection con=null;
   
   private List<mySQL> liste1=new ArrayList<>();
  
    
   public List<mySQL> getStudensTable(){
       try {
           Class.forName("com.mysql.jdbc.Driver");
           con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mehmet","root","");
           ps=con.prepareStatement("SELECT * FROM students");
           ResultSet rs=ps.executeQuery();
           List<mySQL> liste=new ArrayList<>();
           while(rs.next()){
               mySQL my=new mySQL();
               my.setStudentID(rs.getInt("studentID"));
               my.setName(rs.getString("name"));
               my.setSurname(rs.getString("surname"));
               my.setProfession(rs.getString("profession"));
               my.setAge(rs.getInt("age"));
               my.setURL(rs.getString("URL"));
               liste.add(my);
           }
           return liste;
           
       } catch (ClassNotFoundException ex) {
           System.out.println("Bir hata meydana geldi" +ex);
           return null;
       } catch (SQLException ex) {
           System.out.println("Bir hata meydana geldi" +ex);
           return null;
       }finally{
           try {
               if(con!=null){
                   con.close();
               }
               if(ps!=null){
                   ps.close();
               }
           } catch (Exception e) {
               System.out.println("Bir hata meydana geldi" +e);
           }
       }
       
    
}
    public void getdelete(){
        guncelleme gc=new guncelleme();
        gc.getsil();
    }
 

    /**
     * @return the liste2
     */
   
}
