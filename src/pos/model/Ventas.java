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
import java.util.List;
import pos.util.DataBase;

/**
 *
 * @author wdjimenez
 */
public class Ventas {
    
    private static Connection conn;
    
    public static boolean generaVenta(String total, List<ItemVentas> items){
        ResultSet rs = null;
        PreparedStatement pstmt1 = null, pstmt2 = null;
        int ventaId;
        String sqlVenta = "INSERT INTO ventas(total) VALUES(?)";
        String sqlItem = "INSERT INTO productosventa(id_venta, id_producto, cantidad, precio, importe, descuento) VALUES(?,?,?,?,?,0.00)";
        
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
                    conn.close();
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

                if (conn != null) {
                    conn.close();
                }
                
            } catch (SQLException e3) {
                System.out.println(e3.getMessage());
                return false;
            }
        }
        
        return true;
        
    }
}
