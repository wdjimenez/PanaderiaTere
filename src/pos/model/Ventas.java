/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pos.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pos.util.DataBase;

/**
 *
 * @author wdjimenez
 */
public class Ventas {
    
    private String fecha;
    private String hora;
    private int idVenta;
    private String nombre;
    private int cantidad;
    private float precio;
    private float importe;
    
    private static Connection conn;

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public void setImporte(float importe) {
        this.importe = importe;
    }
        
    public String getFecha() {
        return fecha;
    }

    public String getHora() {
        return hora;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public float getPrecio() {
        return precio;
    }

    public float getImporte() {
        return importe;
    }
    
    
    
    public static boolean generaVenta(String total, List<ItemVentas> items){
        ResultSet rs = null;
        PreparedStatement pstmt1 = null, pstmt2 = null, pstmt3 = null;
        int ventaId, stock;
        String sqlVenta = "INSERT INTO ventas(fecha, total) VALUES(julianday('now'),?)";
        String sqlItem = "INSERT INTO productosventa(id_venta, id_producto, cantidad, precio, importe, descuento) VALUES(?,?,?,?,?,0.00)";
        String sqlStock = "SELECT stock FROM productos WHERE id = ?";
        String sqlUpdStock = "UPDATE productos SET stock = ? WHERE id = ?";
        
        try{
            conn = DataBase.getConnection();
            if(conn == null)
                return false;
            
            conn.setAutoCommit(false);
            
            //Insertar venta
            pstmt1 = conn.prepareStatement(sqlVenta,
                    Statement.RETURN_GENERATED_KEYS);
 
            pstmt1.setString(1, total);
            
            int rowAffected = pstmt1.executeUpdate();
            
            if (rowAffected != 1) {
                conn.rollback();
            }
            
            rs = pstmt1.getGeneratedKeys();
            ventaId = 0;
            if (rs.next()) {
                ventaId = rs.getInt(1);
            }
            
            //Insertamos las posiciones
            for(ItemVentas item : items){                
                
                //Recuperamos el stock actual del producto
                pstmt3 = conn.prepareStatement(sqlStock);
                pstmt3.setInt(1, item.getProd().getId());
                
                rs  = pstmt3.executeQuery();
                stock = 0;                
                while (rs.next()) {
                    stock = rs.getInt("stock");                    
                }
                
                stock = stock - item.getCantidad();
                if (stock < 0) {
                    conn.rollback();
//                    conn.close();
                    return false;
                }                
                
                //Consumimos el stock
                pstmt3 = conn.prepareStatement(sqlUpdStock);
                pstmt3.setInt(1, stock);
                pstmt3.setInt(2, item.getProd().getId());
                pstmt3.executeUpdate();
                
                
                pstmt2 = conn.prepareStatement(sqlItem);
                pstmt2.setInt(rowAffected, rowAffected);
                
                pstmt2.setInt(1, ventaId);
                pstmt2.setInt(2, item.getProd().getId());
                pstmt2.setInt(3, item.getCantidad());
                pstmt2.setDouble(4, item.getPrecio());
                pstmt2.setDouble(5, item.getImporte());
                // 
                rowAffected = pstmt2.executeUpdate();
                
                if(rowAffected != 1){//No se agrego el producto
                    conn.rollback();
                    return false;
                }
                    
            }
            
            conn.commit();
            
            
        }catch (SQLException e1){
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException e2) {
                System.out.println(e2.getMessage());
            }
            System.out.println(e1.getMessage());
            
            return false;
        }finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt1 != null) {
                    pstmt1.close();
                }

//                if (conn != null) {
//                    conn.close();
//                }
                
            } catch (SQLException e3) {
                System.out.println(e3.getMessage());
                return false;
            }
        }
        
        return true;
        
    }
    
    public static List<Ventas> repVentas(String fecha){
        conn = DataBase.getConnection();
        List<Ventas> ventas = new ArrayList<>();
        String consulta = "SELECT date(ventas.fecha,'localtime') as fecha, time(ventas.fecha, 'localtime') as hora, " +
"	   ventas.id as venta, productos.nombre as nombre, productosventa.cantidad, " +
"	   productosventa.precio, productosventa.importe " +
"	   FROM ventas INNER JOIN productosventa " +
"						ON productosventa.id_venta = ventas.id " +
"				   INNER JOIN productos " +
"						ON productos.id = productosventa.id_producto " +
"	   WHERE date(ventas.fecha,'localtime') = \"" + fecha + "\" " +
"	ORDER BY date(ventas.fecha,'localtime'), time(ventas.fecha, 'localtime')";
        
        System.out.println(consulta);
        try{
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(consulta);
            while (rs.next()) {
                Ventas v = new Ventas();
                v.setIdVenta(rs.getInt("venta"));                
                v.setCantidad(rs.getInt("cantidad"));
                v.setFecha(rs.getString("fecha"));
                v.setHora(rs.getString("hora"));
                v.setImporte(rs.getFloat("importe"));
                v.setNombre(rs.getString("nombre"));
                v.setPrecio(rs.getFloat("precio"));
                ventas.add(v);
            }
        }catch (SQLException ex) {
            Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return ventas;
        
    }    
}
