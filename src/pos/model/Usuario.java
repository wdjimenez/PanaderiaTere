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

    public Usuario() {
    }

    public Usuario(String user, String pass) {
        this.user = user;
        this.pass = pass;
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
            }
        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuario;
    }

    public Boolean update() {
        conn = DataBase.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("UPDATE usuarios SET pass = ? WHERE user = ?");
            ps.setString(1, getPass());
            ps.setString(2, getUser());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

}
