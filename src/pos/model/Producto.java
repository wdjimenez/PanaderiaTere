package pos.model;

import pos.util.DataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Darío Jiménez
 */
public class Producto {

    private static Connection conn;

    private int id;
    private String nombre;
    private float precio;
    private int stock;

    public Producto() {
    }

    public Producto(int id, String nombre, float precio, int stock) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
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

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public static Producto find(int id) {
        conn = DataBase.getConnection();
        Producto producto = null;
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM productos WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                producto = new Producto();
                producto.setId(rs.getInt("id"));
                producto.setNombre(rs.getString("nombre"));
                producto.setPrecio(rs.getInt("precio"));
                producto.setStock(rs.getInt("stock"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return producto;
    }

    public static List<Producto> all() {
        conn = DataBase.getConnection();
        List<Producto> productos = new ArrayList<>();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM productos");
            while (rs.next()) {
                Producto producto = new Producto();
                producto.setId(rs.getInt("id"));
                producto.setNombre(rs.getString("nombre"));
                producto.setPrecio(rs.getInt("precio"));
                producto.setStock(rs.getInt("stock"));
                productos.add(producto);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return productos;
    }

    public Boolean create() {
        conn = DataBase.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO productos (nombre,precio,stock) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, getNombre());
            ps.setFloat(2, getPrecio());
            ps.setInt(3, getStock());
            ps.execute();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            while (generatedKeys.next()) {
                setId((int) generatedKeys.getLong(1));
            }
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public Boolean update() {
        conn = DataBase.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("UPDATE productos SET nombre = ?, precio = ?, stock = ? WHERE id = ?");
            ps.setString(1, getNombre());
            ps.setFloat(2, getPrecio());
            ps.setInt(3, getStock());
            ps.setInt(4, getId());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public static boolean delete(int id) {
        conn = DataBase.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM productos WHERE id = ?");
            ps.setInt(1, id);
            ps.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

}
