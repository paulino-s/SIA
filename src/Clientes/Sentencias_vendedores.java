/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clientes;

import interfaz.agregar_p;
import Conexion.mysql; 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author zzaa
 */
public class Sentencias_vendedores {
    mysql cc = new mysql();
    Connection cn = cc.conexion();    

     public void insertarE(String nombre, String apellido,String direccion,String usuario, String pass,String edad,String sexo) throws SQLException{
     
         
           
         String sql = "call insertar_vendedor('"+nombre+"','"+apellido+"','"+direccion+"','"+usuario+"','"+pass+"','"+edad+"','"+sexo+"')";
     
         
         
           
         Statement insertar = cn.prepareStatement(sql);
         insertar.executeQuery(sql);
         
        JOptionPane.showMessageDialog(null,"Datos incertados");       
         
     }
    
}
