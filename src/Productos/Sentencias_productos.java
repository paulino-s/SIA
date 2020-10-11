/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Productos;
import Conexion.mysql;
import interfaz.agregar_p;
import java.awt.Image;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author zzaa
 */
public class Sentencias_productos 
{
    
    mysql cc = new mysql();
    Connection cn = cc.conexion();    

    
public void agregar_producto(String codigo, String nombre, String categoria,int cantidad, double precio,double precioven)
{
        try
    {
         
         
        String insert ="Call inpro('"+ codigo +"','"+nombre+"','"+categoria
                +"',"+cantidad
                +"," +precio + ","+precioven+")";
        PreparedStatement psta = cn.prepareStatement(insert);

       boolean estadof = psta.execute();
    if(!estadof)
    {
    JOptionPane.showMessageDialog(null, "exito");
    }
        
       
       
        
        
    }
    catch(SQLException ex)
    {
        Logger.getLogger(agregar_p.class.getName()).log(Level.SEVERE,null,ex);
    }

}    

     
     public DefaultTableModel cargar(String prueba) throws SQLException{
     DefaultTableModel tablaProducto;
     
     String [] titulos = {"Codigo","Nombre","Categoria","Cantidad","Precio Compra","Precio Venta"};
     String [] registros = new String[6];
     
     String sql = "SELECT * FROM Productos where CONCAT (Codigo,' ', Nombre) LIKE '%"+prueba+"%'";
     
     tablaProducto = new DefaultTableModel(null,titulos);
     
      Statement st = cn.createStatement();
            
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
              
                registros[0] = rs.getString("Codigo");
                registros[1] = rs.getString("Nombre");
                registros[2] = rs.getString("Categoria");
                
                registros[3] = String.valueOf(rs.getInt("Cantidad"));

                registros[4] = String.valueOf(rs.getDouble("Precio"));
                registros[5] = String.valueOf(rs.getDouble("Precio_venta"));
                
                tablaProducto.addRow(registros);
            
            }
             
            return tablaProducto;
            //tabla.setModel(tablaProducto);
     
     }


public void actualizar(String codigo, String nombre, String categoria,int cantidad, double precio,double precioven)
{
            String sql="call actualizar_producto('"+nombre+"','"+categoria+"',"+cantidad+","+precio+","+precioven+",'"+codigo+"')";
            Statement st;
        try {
            st = cn.createStatement();
            st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null,"Datos actualizados correctamente");
        } catch (SQLException ex) {
            Logger.getLogger(Sentencias_productos.class.getName()).log(Level.SEVERE, null, ex);
        }

}
    
}

