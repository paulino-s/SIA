/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facturacion;
import Conexion.mysql;
import interfaz.agregar_p;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author zzaa
 */
public class Sentencias_factura 
{
    mysql cc = new mysql();
    Connection cn = cc.conexion();
    public void agregarFactura(String nfactura,int idvend,String cliente,double dinero,double devolucion,double total)
    {
                        try
    {
    String inserta = "Call Insertar_factura('"+nfactura+"',CURRENT_TIMESTAMP,"+idvend+",'"+cliente+"',"+dinero+","+devolucion+","+total+")";
        
    PreparedStatement pstae = cn.prepareStatement(inserta);

    boolean estadof = pstae.execute();
    if(!estadof)
    {
    JOptionPane.showMessageDialog(null, "Seleccione los productos a agregar");
    }else
    {
    JOptionPane.showMessageDialog(null, "ha ocurrido un error inesperado");
    }     
        
    }
    catch(SQLException ex)
    {
        Logger.getLogger(agregar_p.class.getName()).log(Level.SEVERE,null,ex);
    }
    }
    
    public void agregarDetalle(String nfactura,String codigo,int cantidad,double precio)
    {
                try
    {
         
         
        String insert = "Call Insertar_detalle (null,'"+nfactura
                +"','"+codigo+"',"+cantidad+","+precio+")";
        
        PreparedStatement psta = cn.prepareStatement(insert);

       boolean estadof = psta.execute();
    if(!estadof)
    {
    JOptionPane.showMessageDialog(null, "exito");
    }else
    {
    JOptionPane.showMessageDialog(null, "ha ocurrido un error inesperado");
    }     
        
    }
    catch(SQLException ex)
    {
        Logger.getLogger(agregar_p.class.getName()).log(Level.SEVERE,null,ex);
    }
    }
    
public DefaultTableModel cargar(String prueba) throws SQLException{
     DefaultTableModel tablaProducto;
     
     String [] titulos = {"Codigo","Cantidad","Precio"};
     String [] registros = new String[4];
     
     //String sql = "call factura_ver('"+prueba+"')";
     String sql = "SELECT * FROM detalle where CONCAT (Codigo,' ', Idfactura) LIKE '%"+prueba+"%'";
     tablaProducto = new DefaultTableModel(null,titulos);
     
      Statement st = cn.createStatement();
            
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
              
                registros[0] = rs.getString("Codigo");
                //registros[1] = rs.getString("Nombre");
                registros[1] = String.valueOf(rs.getInt("Cantidad"));
                registros[2] = String.valueOf(rs.getDouble("Precio_total"));
                
                tablaProducto.addRow(registros);
            
            }
             
            return tablaProducto;
     
     }

     public int BuscarNfacturas() {

        String SQL = "SELECT COUNT(*) as Nfactura FROM factura WHERE Idfactura = Idfactura";

        try {
            int Nfactura = 0;
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            while (rs.next()) {
                Nfactura = rs.getInt("Nfactura");
            }

            return Nfactura;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return 0;
        }

    }
    
   
  public void actualizarFactura(String nfactura,int idvend,String cliente,double dinero,double devolucion,double total)
    {
                        try
    {
    String inserta = "Call Actualizar_Factura('"+nfactura+"',CURRENT_TIMESTAMP,"+idvend+",'"+cliente+"',"+dinero+","+devolucion+","+total+")";
        
    PreparedStatement pstae = cn.prepareStatement(inserta);

    boolean estadof = pstae.execute();
    if(!estadof)
    {
    JOptionPane.showMessageDialog(null, "Factura procesada correctamente");
    }else
    {
    JOptionPane.showMessageDialog(null, "ha ocurrido un error inesperado");
    }     
        
    }
    catch(SQLException ex)
    {
        Logger.getLogger(agregar_p.class.getName()).log(Level.SEVERE,null,ex);
    }
    }   
}
    
  
    


