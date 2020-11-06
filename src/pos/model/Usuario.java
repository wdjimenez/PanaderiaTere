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
public class Usuario {

    private static Connection conn;

    private String user;
    private String pass;
    private String salt;
    private String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    private String apellido;
    private int admin;

    public int getAdmin() {
        return admin;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }

    public String getSalt() {
        return salt;
    }

    public Usuario() {
    }

    public Usuario(String user, String pass, String salt, String nombre, String apellido, int admin) {
        this.user = user;
        this.pass = pass;
        this.salt = salt;
        this.nombre = nombre;
        this.apellido = apellido;
        this.admin = admin;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
    
    public Boolean isAdmin(){
        if (this.getAdmin() == 1)
            return true;
        return false;
    }
    
    public static Boolean createUser(String usuario, String pass, String salt, String nombre, String ape, int admin){
        conn = DataBase.getConnection();
        try {
            conn.setAutoCommit(false);
            
            PreparedStatement ps = conn.prepareStatement("INSERT INTO usuarios (user,pass,salt,nombre,apellido,admin) VALUES (?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, usuario);            
            ps.setString(2, pass);
            ps.setString(3, salt);
            ps.setString(4, nombre);
            ps.setString(5, ape);
            ps.setInt(6, admin);
            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            while (generatedKeys.next()) {
                conn.commit();
                return true;
            }                        
        } catch (SQLException ex) {
            Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);            
        }
        return false;
    }

    public static Usuario find(String user) {
        conn = DataBase.getConnection();
        Usuario usuario = null;
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM usuarios WHERE user = ?");
            ps.setString(1, user);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                usuario = new Usuario();
                usuario.setUser(rs.getString("user"));
                usuario.setPass(rs.getString("pass"));
                usuario.setSalt(rs.getString("salt"));
                usuario.setAdmin(rs.getInt("admin"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setApellido(rs.getString("apellido"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuario;
    }

    public static Boolean updatePass(String user, String newpass, String salt) {
        conn = DataBase.getConnection();
        try {
            conn.setAutoCommit(true);
            
            PreparedStatement ps = conn.prepareStatement("UPDATE usuarios SET pass = ?, salt = ? WHERE user = ?");
            ps.setString(1, newpass);
            ps.setString(2, salt);
            ps.setString(3, user);
            ps.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public static List<Usuario> allUsers() {
        conn = DataBase.getConnection();
        List<Usuario> usuarios = new ArrayList<>();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM usuarios WHERE user != 'admin' ORDER BY user");
            while (rs.next()) {
                Usuario user = new Usuario(rs.getString("user"),rs.getString("pass"),rs.getString("salt"),rs.getString("nombre"), rs.getString("apellido"), rs.getInt("admin"));
                //Usuario(String user, String pass, String salt, String nombre, String apellido, int admin)
                
//                producto.setId(rs.getInt("id"));
//                producto.setNombre(rs.getString("nombre"));
//                producto.setPrecio(rs.getFloat("precio"));
//                producto.setStock(rs.getInt("stock"));
//                producto.setInactivo(rs.getInt("inactivo"));
                usuarios.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuarios;
    }

}
