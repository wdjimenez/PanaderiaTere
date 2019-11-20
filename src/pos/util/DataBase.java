/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pos.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Darío Jiménez
 */
public class DataBase {

    private static Connection connection = null;

    public static void init(String path) {
        try {
            Class.forName("org.sqlite.JDBC");

            try {
                setConnection(path);
                Statement statement = connection.createStatement();
                statement.setQueryTimeout(30);

                statement.executeUpdate("CREATE TABLE IF NOT EXISTS usuarios(user TEXT NOT NULL UNIQUE, pass TEXT NOT NULL, salt TEXT NOT NULL, nombre TEXT, apellido TEXT, admin INTEGER, PRIMARY KEY(user));");
                statement.executeUpdate("CREATE TABLE IF NOT EXISTS productos(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, nombre TEXT NOT NULL, precio REAL, stock INTEGER, inactivo INTEGER);");
                statement.executeUpdate("CREATE TABLE IF NOT EXISTS ventas(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, fecha REAL NOT NULL, total REAL NOT NULL, descuento REAL NOT NULL, user TEXT, FOREIGN KEY(user) REFERENCES usuarios(user));");
                statement.executeUpdate("CREATE TABLE IF NOT EXISTS productosventa(id_venta INTEGER, id_producto INTEGER, cantidad REAL, precio REAL, importe REAL, FOREIGN KEY(id_venta) REFERENCES ventas(id), FOREIGN KEY(id_producto) REFERENCES productos(id));");
                statement.executeUpdate("CREATE TABLE IF NOT EXISTS prod_stock(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, idProd INTEGER NOT NULL, fecha REAL NOT NULL, cantidad INTEGER NOT NULL, user Text, FOREIGN KEY(user) REFERENCES usuarios(user), FOREIGN KEY(idProd) REFERENCES productos(id));"); 
                statement.executeUpdate("CREATE TABLE IF NOT EXISTS prod_precio(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, idProd INTEGER NOT NULL, fecha REAL NOT NULL, precio_old REAL NOT NULL, precio_new REAL NOT NULL, FOREIGN KEY(idProd) REFERENCES productos(id));"); 
                
                statement.executeUpdate("INSERT INTO usuarios (user,pass,salt,nombre, apellido, admin) VALUES ('admin','efY8UkOG2VOl7Px7WUIyDZm+O9kxFDGAIHYPUCzkFb4=', '4MdnVf2CXSTlsR7bdy6GZ9ePN0qBKi', 'Teresa', 'Gomez', 1);"); //default password: password

                statement.executeUpdate("INSERT INTO productos (nombre,precio,stock, inactivo) VALUES ('Cazuela',10.00,0,0);");
                statement.executeUpdate("INSERT INTO productos (nombre,precio,stock, inactivo) VALUES ('Capricho',8.50,0,0);");
                statement.executeUpdate("INSERT INTO productos (nombre,precio,stock, inactivo) VALUES ('Conchita',7.00,0,0);");
                statement.executeUpdate("INSERT INTO productos (nombre,precio,stock, inactivo) VALUES ('Conchotas',12.50,0,0);");
                statement.executeUpdate("INSERT INTO productos (nombre,precio,stock, inactivo) VALUES ('Ajonjoli',9.00,0,0);");
                statement.executeUpdate("INSERT INTO productos (nombre,precio,stock, inactivo) VALUES ('Chonguitos',8.50,0,0);");
                statement.executeUpdate("INSERT INTO productos (nombre,precio,stock, inactivo) VALUES ('Cazuelitas',21.00,0,0);");
                statement.executeUpdate("INSERT INTO productos (nombre,precio,stock, inactivo) VALUES ('Pelonas',6.00,0,0);");
                statement.executeUpdate("INSERT INTO productos (nombre,precio,stock, inactivo) VALUES ('Panque',9.50,0,0);");
                statement.executeUpdate("INSERT INTO productos (nombre,precio,stock, inactivo) VALUES ('Polka',8.00,0,0);");
                statement.executeUpdate("INSERT INTO productos (nombre,precio,stock, inactivo) VALUES ('Marquezote',8.00,0,0);");
            } catch (SQLException e) {
                // JOptionPane.showMessageDialog(null, e.toString(), "Error con el archivo", JOptionPane.ERROR_MESSAGE);
                System.err.println("Error con el archivo");
                System.err.println(e.toString());
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            // JOptionPane.showMessageDialog(null, ex.toString(), "Error con el driver.", JOptionPane.ERROR_MESSAGE);
            System.err.println("Error con el driver");
            System.err.println(ex.toString());
        }

    }

    public static void setConnection(String path) {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:" + path);
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        DataBase.connection = conn;
    }

    public static Connection getConnection() {
        Config c = new Config();
        if (DataBase.connection == null) {
            DataBase.setConnection(c.getDatabaseFile());
        }

        return connection;
    }
}
