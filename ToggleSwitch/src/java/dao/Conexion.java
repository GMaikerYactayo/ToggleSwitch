package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private Connection cn;
    
    public void Conexion(){
        try {
            if (cn == null) {
                Class.forName("oracle.jdbc.driver.OracleDriver");
                cn = DriverManager.getConnection("jdbc:oracle:thin:@localhost", "Demo", "maiker");
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error: " + e);
        }
    }
    
    public void cerrar() throws SQLException {
        if (cn != null) {
            if (cn.isClosed()==false) {
                cn.close();
                cn = null;
            }
            
        }
    }

    public Connection getCn() {
        return cn;
    }

    public void setCn(Connection cn) {
        this.cn = cn;
    }
    
    public static void main(String[] args) {
        Conexion dao = new Conexion();
        dao.Conexion();
        if (dao.getCn()!=null) {
            System.out.println("Conectado");
        } else {
            System.out.println("No hay conexion");
        }
    }

}
