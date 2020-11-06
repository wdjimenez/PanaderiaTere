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
public class ProdInactive {
    private static Connection conn;

    private int id;
    private String nombre;
    private int inactivo;
    
    public ProdInactive(){
        
    }
    
    public ProdInactive(int id, String nombre, int inactivo) {
        this.id = id;
        this.nombre = nombre;
        this.inactivo = inactivo;
    }
    
    public ProdInactive(int id, String nombre, boolean inactivo) {
        this.id = id;
        this.nombre = nombre;
        
        if (inactivo) 
            this.inactivo = 1;          
        else
            this.inactivo = 0;
        
    }

    public static List<ProdInactive> all() {
        conn = DataBase.getConnection();
        List<ProdInactive> productos = new ArrayList<>();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT id, nombre, inactivo FROM productos ORDER BY nombre");
            while (rs.next()) {
                ProdInactive producto = new ProdInactive();
                producto.setId(rs.getInt("id"));
                producto.setNombre(rs.getString("nombre"));
                if (rs.getInt("inactivo") == 0) {
                    producto.setInactivo(false);
                }else{
                    producto.setInactivo(true);
                }
                
                productos.add(producto);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return productos;
    }
    
    public boolean update(){
        conn = DataBase.getConnection();
        try {
            
            conn.setAutoCommit(false);
            
            PreparedStatement ps = conn.prepareStatement("UPDATE productos SET inactivo = ? WHERE id = ?");                        
            
            ps.setInt(1, this.inactivo);
            ps.setInt(2, getId());

            ps.execute();
            
            conn.commit();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean getInactivo() {
        if (this.inactivo == 1) {
            return true;
        }else{
            return false;
        }

    }

    public void setInactivo(boolean inactivo) {
        if (inactivo) {
            this.inactivo = 1;       
        }else{
            this.inactivo = 0;       
        }
        
    }
}
