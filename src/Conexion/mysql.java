/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;


import java.sql.*;
import javax.swing.*;

/**
 *
 * @author usuario
 */
public class mysql {
    Connection conect = null;
    
    public Connection conexion(){
    
        try{
        
        Class.forName("com.mysql.jdbc.Driver");
        conect = DriverManager.getConnection("jdbc:mysql://localhost/proyecto","root","");
        
        }catch(Exception e){
        
            JOptionPane.showConfirmDialog(null,"error " + e);
 
        }
          
        return conect;
    }
    
}
