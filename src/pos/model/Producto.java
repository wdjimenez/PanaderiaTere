package pos.model;

import pos.util.DataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    private int inactivo;

    public int getInactivo() {
        return inactivo;
    }

    public void setInactivo(int inactivo) {
        this.inactivo = inactivo;
    }

    public Producto() {
    }

    public Producto(int id, String nombre, float precio, int stock, int inactivo) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.inactivo = inactivo;
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
    
    public String toString() { 
        return this.getNombre();
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
                producto.setPrecio(rs.getFloat("precio"));
                producto.setStock(rs.getInt("stock"));
                producto.setInactivo(rs.getInt("inactivo"));
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
            ResultSet rs = st.executeQuery("SELECT * FROM productos WHERE inactivo = 0 ORDER BY nombre");
            while (rs.next()) {
                Producto producto = new Producto();
                producto.setId(rs.getInt("id"));
                producto.setNombre(rs.getString("nombre"));
                producto.setPrecio(rs.getFloat("precio"));
                producto.setStock(rs.getInt("stock"));
                producto.setInactivo(rs.getInt("inactivo"));
                productos.add(producto);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return productos;
    }
    
    public static List<Producto> allProd() {
        conn = DataBase.getConnection();
        List<Producto> productos = new ArrayList<>();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM productos ORDER BY nombre");
            while (rs.next()) {
                Producto producto = new Producto();
                producto.setId(rs.getInt("id"));
                producto.setNombre(rs.getString("nombre"));
                producto.setPrecio(rs.getFloat("precio"));
                producto.setStock(rs.getInt("stock"));
                producto.setInactivo(rs.getInt("inactivo"));
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
            conn.setAutoCommit(false);
            
            PreparedStatement ps = conn.prepareStatement("INSERT INTO productos (nombre,precio,stock, inactivo) VALUES (?,?,?, 0)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, getNombre());            
            ps.setFloat(2, getPrecio());
            ps.setInt(3, getStock());
            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            while (generatedKeys.next()) {
                setId((int) generatedKeys.getLong(1));
            }
            
            conn.commit();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);            
        }
        return false;
    }

    public Boolean update() {
        conn = DataBase.getConnection();
        try {
            
            conn.setAutoCommit(false);
            
            PreparedStatement ps = conn.prepareStatement("UPDATE productos SET nombre = ?, precio = ?, stock = ?, inactivo = ? WHERE id = ?");                        
            
            ps.setString(1, getNombre());
            ps.setFloat(2, getPrecio());
            ps.setInt(3, getStock());
            ps.setInt(4, getInactivo());
            ps.setInt(5, getId());
            ps.execute();
            
            conn.commit();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public static boolean delete(int id) {
        conn = DataBase.getConnection();
        try {            
            conn.setAutoCommit(false);
            
            PreparedStatement ps = conn.prepareStatement("DELETE FROM productos WHERE id = ?");
            ps.setInt(1, id);
            ps.execute();
            
            conn.commit();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public static int getRealStock(int idProd){
        int stock = 0;
        
        conn = DataBase.getConnection();
        
        try{
            PreparedStatement ps = conn.prepareStatement("SELECT stock FROM productos WHERE id = ?");
            ps.setInt(1, idProd);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                stock = rs.getInt("stock");         
            }
//            conn.close();
        }catch(SQLException ex){
            Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return stock;
    }
    
    public static boolean addStock(int idProd, int cantidad, String user){
        String sqlProdStock = "INSERT INTO prod_stock(idProd, cantidad, fecha, user) VALUES(?, ?, julianday('now'), ?)";
        String sqlUpdStock = "UPDATE productos SET stock = ? WHERE id = ?";
        int newStock = 0;
        conn = DataBase.getConnection();
        
        try{
            conn.setAutoCommit(false);
            
            PreparedStatement ps = conn.prepareStatement(sqlProdStock);
            ps.setInt(1, idProd);
            ps.setInt(2, cantidad);
            ps.setString(3, user);
            
            ps.execute();
            
            newStock = getRealStock(idProd) + cantidad;
            
            ps = conn.prepareStatement(sqlUpdStock);
            ps.setInt(1, newStock);
            ps.setInt(2, idProd);
            
            ps.execute();
            
            conn.commit();
            
        }catch(SQLException ex){
            Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
                       
        return true;
    }
    
    public static List<Producto> repStock() {
        conn = DataBase.getConnection();
        List<Producto> productos = new ArrayList<>();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM productos WHERE inactivo = 0 ORDER BY nombre");
            while (rs.next()) {
                Producto producto = new Producto();
                producto.setId(rs.getInt("id"));
                producto.setNombre(rs.getString("nombre"));
                producto.setPrecio(rs.getFloat("precio"));
                producto.setStock(rs.getInt("stock"));
                productos.add(producto);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return productos;
    }    
    
    public static List<Producto> bestSelled(){
        conn = DataBase.getConnection();
        
//        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");        
        
        LocalDate today = LocalDate.now();
        LocalDate aMonthAgo = today.minusMonths(1);
                        
        List<Producto> productos = new ArrayList<>();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select productosventa.id_producto, productos.nombre, productos.precio, sum(productosventa.cantidad) FROM " + 
                            " ventas inner join productosventa ON ventas.id = productosventa.id_venta " + 
                            " inner join productos on productosventa.id_producto = productos.id " +
                            "WHERE date(ventas.fecha,'localtime') BETWEEN '" + aMonthAgo.format(formatter) + "' AND '" + today.format(formatter)  + 
                            "' GROUP BY id_producto ORDER BY SUM(cantidad) DESC LIMIT 5" );
            while (rs.next()) {
                Producto producto = new Producto();
                producto.setId(rs.getInt("id_producto"));
                producto.setNombre(rs.getString("nombre"));
                producto.setPrecio(rs.getFloat("precio"));
//                producto.setStock(rs.getInt("stock"));
                productos.add(producto);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return productos;
    }
    
    public static boolean saveLogPrice(int idProd, float precio_old, float precio_new){
        String sqlProdPrecio = "INSERT INTO prod_precio(idProd, fecha, precio_old, precio_new) VALUES(?, julianday('now'), ?, ?)";

        conn = DataBase.getConnection();
        
        try{
            conn.setAutoCommit(false);
            
            PreparedStatement ps = conn.prepareStatement(sqlProdPrecio);
            ps.setInt(1, idProd);
            ps.setFloat(2, precio_old);
            ps.setFloat(3, precio_new);
            
            ps.execute();     
            
            conn.commit();
            
        }catch(SQLException ex){
            Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
                       
        return true;
    }
            
}
